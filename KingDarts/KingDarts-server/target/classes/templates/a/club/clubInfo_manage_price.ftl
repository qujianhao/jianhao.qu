<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/agentInfo/agentInfo_add">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                	<input type="hidden" name="id" value="${paramMap.entity.id}" required>
                    <h4 class="page-header">游戏定价</h4>
                    <div class="row">
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="price_start_time"><span style="color:red">*</span>起始日期：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control sale_date" name="price_start_time" id="price_start_time" value="<#if (paramMap.entity.price_start_time)??>${paramMap.entity.price_start_time?string('yyyy-MM-dd HH:mm')}</#if>" placeholder="请选择起始日期" title="请选择起始日期" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="price_end_time"><span style="color:red">*</span>结束日期：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control sale_date" name="price_end_time" id="price_end_time" value="<#if (paramMap.entity.price_end_time)??>${paramMap.entity.price_end_time?string('yyyy-MM-dd HH:mm')}</#if>" placeholder="请选择结束日期" title="请选择结束日期" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="manage_game_price"><span style="color:red">*</span>游戏定价：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="manage_game_price" id="manage_game_price" value="${paramMap.entity.manage_game_price}"  placeholder="请输入游戏定价" title="请输入游戏定价" required>
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_AGENTINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
             <@wtAuth hasPrem="_AGENTINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-close">关闭</button></@wtAuth>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
    	
        //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        function plusint(value){
    		var reg = /^[1-9]\d*$/;;
    		console.log("==== "+reg.test(value))
    		return reg.test(value); 
    	}
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            if(!plusint($("#manage_game_price").val())){
            	WT.wt_alert("游戏定价需要为正整数");
            	return false;
            }
            
            var str=$("#price_start_time").val();
            var val = Date.parse(str);
			var startTime=new Date(val);
			str=$("#price_end_time").val();
			val = Date.parse(str);
			var endTime=new Date(val);
			if(startTime >= endTime){
				WT.wt_alert("起始日期必须小于结束日期");
    			return false;
			}
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/clubInfo/clubInfo_manage_price','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
        
        $(".sale_date").datetimepicker({
		  autoclose: true,
		  //dateFormat: "yyyy-mm-dd",  
		  //timeFormat: 'hh:ii',
		  format: "yyyy-mm-dd hh:00",
		  forceParse: false,
		  language: 'zh-CN',
		  startView: 1,
		  minView:1,
		  todayBtn: true
		});
    });
    
    
</script>
</body>
</#escape>
</html>
