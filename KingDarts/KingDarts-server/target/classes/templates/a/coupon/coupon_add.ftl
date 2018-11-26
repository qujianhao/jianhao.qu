<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/coupon/coupon_add">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">新增优惠券</h4>
                    <div class="row">
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="game_balance"><span style="color:red">*</span>游戏点：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="game_balance" id="game_balance" value="${paramMap.game_balance}"  placeholder="请输入游戏点" title="请输入游戏点" required>
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
            WT.wt_confirm('是否生成优惠券?', function () {
                WT.wt_ajax_form('${base}/a/coupon/coupon_save','layerForm',function(data){
                    WT.wt_alert('生成成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
    });
    
    
</script>
</body>
</#escape>
</html>
