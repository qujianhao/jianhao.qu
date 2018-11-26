<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/newuser/newusergivebalance_add">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">新增新用户奖励规则</h4>
                    <div class="row">
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="game_balance"><span style="color:red">*</span>奖励点数：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="give_game_balance" id="give_game_balance" value="${paramMap.give_game_balance}"  placeholder="请输入奖励点数" title="请输入奖励点数" required>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="start_time"><span style="color:red">*</span>起始日期：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control sale_date" name="start_time" id="start_time" value="<#if (paramMap.entity.start_time)??>${paramMap.entity.start_time?string('yyyy-MM-dd')}</#if>" placeholder="请选择起始日期" title="请选择起始日期" required>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="end_time"><span style="color:red">*</span>结束日期：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control sale_date" name="end_time" id="end_time" value="<#if (paramMap.entity.end_time)??>${paramMap.entity.end_time?string('yyyy-MM-dd')}</#if>" placeholder="请选择结束日期" title="请选择结束日期" required>
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
            if($("#give_game_balance").val()==""||$("#give_game_balance").val()==0){
            	WT.wt_alert("奖励点数必须大于0");
    			return false;
            }
            var str=$("#start_time").val();
            var val = Date.parse(str);
			var startTime=new Date(val);
			str=$("#end_time").val();
			val = Date.parse(str);
			var endTime=new Date(val);
			if(startTime >= endTime){
				WT.wt_alert("起始日期必须小于结束日期");
    			return false;
			}
			
            WT.wt_confirm('是否生成新用户奖励规则?', function () {
                WT.wt_ajax_form('${base}/a/newuser/newusergivebalance_save','layerForm',function(data){
                    WT.wt_alert('生成成功',function(){
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
		  format: "yyyy-mm-dd",
		  forceParse: false,
		  language: 'zh-CN',
		  startView: 4,
		  minView:2,
		  todayBtn: true
		});
    });
    
    
</script>
</body>
</#escape>
</html>
