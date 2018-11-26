<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
<style>
.checkbox label::before {
    content: "";
    display: inline-block;
    position: absolute;
    width: 20px;
    height: 20px;
    left: 0;
    margin-left: -20px;
    border: 1px solid #cccccc;<!--没选中时的边框颜色-->
    border-radius: 3px;
    background-color: #fff;<!--没选中时的颜色-->
    -webkit-transition: border 0.15s ease-in-out, color 0.15s ease-in-out;
    -o-transition: border 0.15s ease-in-out, color 0.15s ease-in-out;
    transition: border 0.15s ease-in-out, color 0.15s ease-in-out;

}

.checkbox label::after {
    display: inline-block;
    position: absolute;
    width: 16px;
    height: 16px;
    left: 0;
    top: 0;
    margin-left: -19px;<!--可以改变“√”的位置-->
    padding-left: 3px;<!--可以改变“√”的位置-->
    padding-top: 1px;<!--可以改变“√”的位置-->
    font-size: 13px;<!--选中后的中间打钩的字体大小（字体越大中间的勾越大越明显）-->
    color: #FAD500;<!--选中后的中间打钩的颜色-->
}

.checkbox input[type="checkbox"],
.checkbox input[type="radio"] {
    opacity: 0;
    z-index: 1;
}

.checkbox input[type="checkbox"]:focus + label::before,
.checkbox input[type="radio"]:focus + label::before {
    outline: thin dotted;
    outline: 5px auto -webkit-focus-ring-color;
    outline-offset: -2px;
    background-color: black;
    border-color: black;
}

.checkbox input[type="checkbox"]:checked + label::before,
.checkbox input[type="radio"]:checked + label::before {
    background-color: black;<!--选中后的背景颜色-->
    border-color: black;<!--选中后的边框颜色-->
}

.checkbox input[type="checkbox"]:checked + label::after,
.checkbox input[type="radio"]:checked + label::after {
    font-family: "FontAwesome";
    content: "\f00c";
}

.checkbox input[type="checkbox"]:disabled + label,
.checkbox input[type="radio"]:disabled + label {
    opacity: 0.65;
}

.checkbox input[type="checkbox"]:disabled + label::before,
.checkbox input[type="radio"]:disabled + label::before {
    background-color: #eeeeee;
    cursor: not-allowed;
}

.checkbox.checkbox-circle label::before {
    border-radius: 50%;
}

.font-bolder{
	width: 100px;
}
</style>
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/equLine/equLine_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="col-md-10 col-lg-6 form-inline" style="margin-left: 50px;">
			        <div class="checkbox checkbox-circle">
			            <input id="radio1" class="styled" type="radio" name="stype" value="">
			            <label for="radio1" class="font-bolder"> 全部 </label>
			        </div>
			        <div class="checkbox checkbox-circle">
			            <input id="radio2" class="styled" type="radio" name="stype" value="1">
			            <label for="radio2" class="font-bolder"> 在线 </label>
			        </div>
			        <div class="checkbox checkbox-circle">
			            <input id="radio3" class="styled" type="radio" name="stype" value="2">
			            <label for="radio3" class="font-bolder"> 离线 </label>
			        </div>
			        <div class="checkbox checkbox-circle">
			            <input id="radio4" class="styled" type="radio" name="stype" value="3">
			            <label for="radio4" class="font-bolder"> 离线12小时 </label>
			        </div>
			        <div class="checkbox checkbox-circle">
			            <input id="radio5" class="styled" type="radio" name="stype" value="4">
			            <label for="radio5" class="font-bolder" style="width: 130px;"> 离线24小时以上 </label>
			        </div>
			        <div class="checkbox checkbox-circle input-group">
			            <input id="radio6" class="styled" type="radio" name="stype" value="5">
			            <label for="radio6" class="font-bolder" style="width: 0px;"> </label>
			        </div>
			        <label for="radio6" class="font-bolder"><input type="text" style="width: 50px;" name="day" id="day">天以上</label>
                </div>
                <div class="col-sm-2 form-group">
                	<label for="equno">设备编号</label>
                     <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" placeholder="请输入设备编码">
                 </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="button" class="btn btn-sm btn-default wt-search">搜索</button>
                    <button type="button" class="btn btn-sm btn-default wt-reset">重置</button>
                </div>
            </div>
        </div>
    </form>
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <button type="button" class="btn btn-sm btn-default wt-sendOffSms" >推送短信</button>
        </div>
    </div>
    <div class="box box-default">
        <div class="box-body">
            <div class="jqGrid_wrapper ">
                <table id="wt_table_list" class="wt-table-resize"></table>
                <div id="wt_pager_list"></div>
            </div>
        </div>
    </div>

</section>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        $(".wt-reset").click(function(){
        	$("#radio1").click();
        	$("#day").val("");
        })
        $(".wt-search").click(function(){
        	var postData = WT.wt_serializeJSONObject("frameForm");
        	if(postData.stype == 5 ){
        		if (isNaN(postData.day)){ 
        			WT.wt_alert("请输入数字");
        			return false;
        		}
        		if(postData<=0){
        			WT.wt_alert("天数需要大于0");
        			return false;
        		}
        	}
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : postData,  
    		      page:1
    		}).trigger("reloadGrid");
    	})
    	
        WT.wt_jqtable_multi('${base}/a/equInfo/line/equInfo_search',
        		[ '','', '设备编号', '机器型号', '所属俱乐部', '负责人', '机器状态','操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'mobile', index: 'mobile', width: 5 , sortable: false,hidden:true},
                    {name: 'equno', index: 'equno', width: 5 , sortable: false},
                    {name: 'models', index: 'models', width: 5 , sortable: false},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'captain', index: 'captain', width: 5 , sortable: false},
                    {name: 'diftime', index: 'diftime', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                    	if(rowObject.isline == 1) return '在线'
                    	var h = cellvalue;
                    	if(cellvalue - 24 >0){
                    		h = parseInt(cellvalue/24) +'天'+  (cellvalue%24); 
                    	}
                        return '关机'+h+'小时';
                    }},
                    {name: 'btns', index: 'btns', sortable: false, width: 5, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        if(rowObject.isline == 1){
                        	text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-offline'>下线</a>" ;
                        }
                        return text;
                    }}
                ]);
        
        
        $(".wt-sendOffSms").click(function(){
        	var ids = $('#wt_table_list').jqGrid('getGridParam', 'selarrrow');
            if(ids == null || ids.length === 0){
            	WT.wt_alert('没有需要通知的设备。');
            	return false;
            }
            var paramArr = new Array();
            for(var k=0; k<ids.length; k++) {
            	var rowData = jQuery("#wt_table_list").jqGrid('getRowData', ids[k]);//获取指定id所在行的所有数据.
            	paramArr.push(rowData.mobile+";"+rowData.equno+";"+rowData.diftime);
            }
            var params = {mobiles:paramArr.join(",")};
        	WT.wt_confirm('是否发送短信?', function () {
                WT.wt_ajax_jsondata('${base}/a/equInfo/sendOffSms',params,function(data){
                	WT.wt_alert("发送成功")
                });
            });
        })
        
        $(".jqGrid_wrapper").on('click', '.wt-offline', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {equno : rowData.equno};
            WT.wt_confirm('是否强制下线?', function () {
                WT.wt_ajax_jsondata('${base}/a/equInfo/offline', params, function (data) {
                    WT.wt_alert('操作成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });
</script>
</body>
</#escape>
</html>
