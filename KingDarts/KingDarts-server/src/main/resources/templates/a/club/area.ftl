<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
<#include "./a/commons/bottom.ftl" />
<style type="text/css"> 
	html{height:100%} 
	body{height:100%;margin:0px;padding:0px;font-size: 13px;}
	#container{height:100%}
	#container_tool{
		width:265px;
		height:25px;
		overflow:hidden;
		vertical-align:middle;   
		display:table-cell;
		border-top-color:#f2f3f5;
		border-right-color:#f2f3f5;
		border-bottom-color:#f2f3f5;
		border-left-color:#f2f3f5;
		border-top-width:5px;
		border-right-width:5px;
		border-bottom-width:5px;
		border-left-width:5px;
		border-top-style:solid;
		border-right-style:solid;
		border-bottom-style:solid;
		border-left-style:solid;
		background-origin:padding-box;
		background-clip:border-box;
		background-color:rgb(242, 243, 245);
	} 
	#f_container{
		left:60px;
		top:5px;
		display:table;
		border-top-color:#999999;
		border-right-color:#999999;
		border-bottom-color:#999999;
		border-left-color:#999999;
		border-top-width:1px;
		border-right-width:1px;
		border-bottom-width:1px;
		border-left-width:1px;
		border-top-style:solid;
		border-right-style:solid;
		border-bottom-style:solid;
		border-left-style:solid;
		position:absolute;
		z-index:1000;
	}
</style>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=dd4cc1cb829680ec5e6e993e962431fd"></script>
</head>
<#escape x as x?html>
<body>
	<input type="hidden" id="lng" name="lng"/>
	<input type="hidden" id="lat" name="lat"/>
	<input type="hidden" id="addressDetails" name="addressDetails"/>
	<div id="f_container">
		<div id="container_tool" align="center">
		<input type="text" id="address" name="address" style="width: 200px;" placeholder="输入地址进行搜索"/>
		<button  class="btn btn-white btn-sm" onclick="searchMap()" >搜索</button>
		</div>
	</div>
	
	<div id="container"></div> 
	
	<div class="wt-layer-footer">
	    <div class="col-sm-12 col-sm-offset-5 wt-layer-footer-content">
	    	
	        <button type="button" class="btn btn-sm btn-primary wt-confirm">确认</button>
	        <button type="button" class="btn btn-sm btn-primary wt-close">关闭</button>
	    </div>
	</div>
	<script type="text/javascript"> 
	//创建地图对象
	var map = new AMap.Map('container', {
		resizeEnable: true,
		zoom: 11,
		center: [116.39,39.9]
	});

	var marker = new AMap.Marker({
	    map:map,
	    bubble:true
	});
	

	AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView'],
		function(){
			map.addControl(new AMap.ToolBar());
		    map.addControl(new AMap.Scale());
		    map.addControl(new AMap.OverView({isOpen:true}));
	});

	AMap.service('AMap.Geocoder',function() {
	    //实例化Geocoder
	    geocoder = new AMap.Geocoder({
	        /* city: "010"//城市，默认：“全国” */
	    });
	});

	var _onClick = function(e) {
		//根据经纬度获取地址
		geocoder.getAddress(e.lnglat, function(status, result) {
		    if (status === 'complete' && result.info === 'OK') {
		    	//标注地点
		    	marker.setPosition(e.lnglat);	
		    	map.setCenter(marker.getPosition());
		    	document.getElementById('addressDetails').value = result.regeocode.formattedAddress;
		    	document.getElementById('lng').value = e.lnglat.getLng();
		    	document.getElementById('lat').value = e.lnglat.getLat();
		    	window.parent.addTr(e.lnglat.getLng(),e.lnglat.getLat(),result.regeocode.formattedAddress);
		    }
		    else{
		       //获取地址失败
		       alert("获取地址失败");
		    }
		});
	}

	var clickListener = AMap.event.addListener(map, "click", _onClick); //绑定事件，返回监听对象


	$(document).ready(function() {
		$(".wt-close").click(function(){
     	   WT.wt_close();
        })
        
        $(".wt-confirm").click(function(){
     	   	//window.parent.addTr(document.getElementById('lng').value,document.getElementById('lat').value,document.getElementById('addressDetails').value);
     	   WT.wt_close();
        })
	
        var area = '${clubPlace.province}${clubPlace.city}'
		document.getElementById("address").value = '${clubPlace.address}';
		var address = area+ '${clubPlace.address}';
		if (address != null && address != '') {
			geocoder.getLocation(address, function(status, result) {
			    if (status === 'complete' && result.info === 'OK') {
			    	marker.setPosition(result.geocodes[0].location);
			    	map.setCenter(marker.getPosition());
			    	marker.setMap(map);
			    }else{
			        //获取经纬度失败
			        alert("获取经纬度失败");
			    }
			}); 
		}
	});

	function searchMap() {
		var address = '${clubPlace.address}' + document.getElementById("address").value;
		if (address == "") {
			alert("请输入地址进行搜索!");
		} else {
			//地理编码
			geocoder.getLocation(address, function(status, result) {
			    if (status === 'complete' && result.info === 'OK') {
			    	marker.setPosition(result.geocodes[0].location);
			    	map.setCenter(marker.getPosition());
			    	marker.setMap(map);
			    	//根据经纬度获取地址
			    	geocoder.getAddress(marker.getPosition(), function(status, result) {
			    	    if (status === 'complete' && result.info === 'OK') {  
			    	    	document.getElementById('addressDetails').value = result.regeocode.formattedAddress;
			    	    	document.getElementById('lng').value = marker.getPosition().getLng();
			    	    	document.getElementById('lat').value = marker.getPosition().getLat();
			    	    	window.parent.addTr(marker.getPosition().getLng(),marker.getPosition().getLat(),result.regeocode.formattedAddress);
     	   					//WT.wt_close();
			    	    }
			    	    else{
			    	       //获取地址失败
			    	       alert("获取地址失败");
			    	    }
			    	});
			    }else{
			        //获取经纬度失败
			        alert("获取经纬度失败");
			    }
			}); 
		}
	}
</script>
</body>
</#escape>
</html>
