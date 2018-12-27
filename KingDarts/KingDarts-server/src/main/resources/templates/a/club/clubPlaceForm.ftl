<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/clubInfo/clubInfo_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <div class="row">
                    	<input type="hidden" id="id"  name="id" value="${entity.id}"/>
                    	<input type="hidden" id="tempid"  name="tempid" value="${entity.tempid}"/>
                    	<input type="hidden" id="longitude"  name="longitude" value="${entity.longitude}"/>
                    	<input type="hidden" id="latitude"  name="latitude" value="${entity.latitude}"/>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="place_name"><span style="color:red">*</span>场所名称：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="place_name" id="place_name" value="${entity.place_name}" placeholder="请输入场所名称" title="请输入场所名称" required>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="consdes">描述信息：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="consdes" id="consdes" value="${entity.consdes}" placeholder="请输入描述信息" title="请输入描述信息" >
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="businesshour"><span style="color:red">*</span>营业时间：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="businesshour" id="businesshour" value="${entity.businesshour}" placeholder="请输入营业时间" title="请输入营业时间" required>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="country"><span style="color:red">*</span>国家：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="country" id="country" value="中国" placeholder="请选择国家" title="请选择国家" disabled>
                            </div>
                        </div>
                        <div id="distpicker"  data-toggle="distpicker">
                            <div class="col-sm-6 form-group">
	                            <label class="col-sm-3 control-label" for="province"><span style="color:red">*</span>省：</label>
	                            <div class="col-sm-9">
	                            	<select class="form-control" data-province="" id="province" name="province" value="${entity.province}" placeholder="请选择省" title="请选择省"  required></select>
	                            </div>
	                        </div>
	                        <div class="col-sm-6 form-group">
	                            <label class="col-sm-3 control-label" for="city"><span style="color:red">*</span>市：</label>
	                            <div class="col-sm-9">
	                            	<select class="form-control"   data-city="" id="city" name="city" value="${entity.city}" placeholder="请选择市" title="请选择市" required ></select>
	                            </div>
	                        </div>
	                        <div class="col-sm-6 form-group">
	                            <label class="col-sm-3 control-label" for="areas"><span style="color:red">*</span>区：</label>
	                            <div class="col-sm-9">
	                            	<select class="form-control"  data-district="" id="areas" name="areas" value="${entity.areas}" placeholder="请选择区" title="请选择区" required ></select>
	                            </div>
	                        </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="address"><span style="color:red">*</span>详细地址：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="address" id="address" value="${entity.address}" title="请定位详细地址" required/>
                            </div>
                            <div class="col-sm-3">
                                <input value="定位" id="add" class="btn btn-white btn-sm" type="button"/>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="pcaptain">联系人：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="pcaptain" id="pcaptain" value="${entity.pcaptain}" placeholder="请输入联系人" title="请输入联系人" >
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="pmobile">手机号：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="pmobile" id="pmobile" value="${entity.pmobile}" isPhone placeholder="请输入手机号" title="请输入手机号" >
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="ptelno">固话：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="ptelno" id="ptelno" value="${entity.ptelno}" placeholder="请输入固话" title="请输入固话">
                            </div>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label class="col-sm-12 control-label" for="details">详情：</label>
                            <div class="col-sm-12" style="position:absolute">
                            	<script id="details" name="details" type="text/plain">
								</script>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
	    <div class="col-sm-12 col-sm-offset-5 wt-layer-footer-content">
	    	
	        <button type="button" class="btn btn-sm btn-primary wt-confirm">确认</button>
	        <button type="button" class="btn btn-sm btn-primary wt-close">关闭</button>
	    </div>
	</div>
</form>
<#include "./a/commons/bottom.ftl" />
<script src="${base}/assets/plugins/ueditor/ueditor.config.js"></script>
<script src="${base}/assets/plugins/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
	
	//var content = ue.getContent();
	//$("#layerForm").val(content);
	if('${entity.isShow}'){
		$("input,select,textarea ").prop("disabled",true);
		var ue = UE.getEditor('details',{initialFrameWidth:"100%",readonly:true});
	}else{
		var ue = UE.getEditor('details',{initialFrameWidth:"100%"});
	}
	
	//富文本回显
	var str = decodeURIComponent("${entity.details}");
	var html = $('<span>').html(str).html();
	ue.ready(function(){
		ue.execCommand('insertHtml', html);
	});
	
	
    $("document").ready(function () {
    
	    //省市区初始化回显
		$("#distpicker").distpicker('destroy');
		$("#distpicker").distpicker({
	
	    autoSelect: true,
	    placeholder: false
	    });
    
	     var provinceSelect= $('[name=province]').attr("value");
	    var citySelect= $('[name=city]').attr("value");
	    
	    var areasSelect= $('[name=areas]').attr("value");
    
  	    $("#province").val(provinceSelect);
        $("#province").trigger("change");
        $("#city").val(citySelect);
        $("#city").trigger("change");
        $("#areas").val(areasSelect);
        $("#areas").trigger("change");

    	//console.log('${entity.details}');
        $(".wt-close").click(function(){
     	   WT.wt_close();
        })
        $(".wt-confirm").click(function(){
        	if(!$("#layerForm").valid())
                return false;
        	
        	var data = WT.wt_serializeJSONObject("layerForm");
        	window.parent.addTr(data);
        	/* var data = $("#layerForm").serialize();
     	   	window.parent.addTro(
     	   		$('input[type=text][name="place_name"]').val(),
     	   		$('input[type=text][name="consdes"]').val(),
     	   		$('input[type=text][name="businesshour"]').val(),
     	   		$('input[type=text][name="country"]').val(),
     	   		$('input[type=text][name="province"]').val(),
     	   		$('input[type=text][name="city"]').val(),
     	   		$('input[type=text][name="areas"]').val(),
     	   		$('input[type=text][name="address"]').val(),
     	   		$('input[type=text][name="pcaptain"]').val(),
     	   		$('input[type=text][name="pmobile"]').val(),
     	   		$('input[type=text][name="ptelno"]').val(),
     	   		$('input[type=hidden][name="longitude"]').val(),
     	   		$('input[type=hidden][name="latitude"]').val(),
     	   		$('[name="details"]').val()
     	   	); */
     	   WT.wt_close();
        })
        
        $("#layerForm").validate({
	    	rules:{
	    		  place_name:{
	    		  	maxlength: 30
	    		  },
	    		  consdes:{
	    		  	maxlength: 50
	    		  },
	    		  businesshour:{
	    		  	maxlength: 30
	    		  },
	    		  pcaptain:{
	    		  	maxlength: 50
	    		  },
		          ptelno: {    //验证邮箱
			            maxlength: 30
			        },
		           pmobile: {
		           		digits:true,
		           		minlength: 11,
	            		maxlength: 11
	                }
		     },
		       
		     messages:{
		     	  place_name:{
		     	  	maxlength:"最多可输入30字",
		     	  },
		     	  consdes:{
		     	  	maxlength:"最多可输入50字",
		     	  },
		     	  businesshour:{
		     	  	maxlength:"最多可输入30字",
		     	  },
		     	  pcaptain:{
		     	  	maxlength:"最多可输入50字",
		     	  },
		          pmobile:{
		          		digits:"手机号格式不正确",
		          		minlength:"手机号格式不正确",
		          		maxlength:"手机号格式不正确"
		          },
		          ptelno:{
		               maxlength: "格式不正确",
		           }                              
		     }
	    });
    });
    
    
    
    function addTr(longitude,latitude,address){
		$('[name=address]').val(address);
		$('[name=longitude]').val(longitude);
		$('[name=latitude]').val(latitude);
	}
    
    $('#add').click(function (event) {
		event.stopPropagation();
		var province = $('#province').val();
		var city = $('#city').val();
		var areas = $('#areas').val();
		var address = $('#address').val();
		var url = "province="+province+"&city="+city+"&areas="+areas+"&address="+address
		url = encodeURI(encodeURI(url));
   	    WT.wt_open_layer('${base}/a/clubplace/showMap?' + url);
		return false;
	})
	
	
	
</script>
</body>
</#escape>
</html>
