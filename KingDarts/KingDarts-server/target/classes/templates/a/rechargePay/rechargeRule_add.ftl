<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/rechargeRule/rechargeRule_add">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                    	<#list 0..8 as t>
                    	<div class="col-sm-4 form-group" >
                    		<div style="width:210px;height:85px; border-style: solid; border-width: 1px; border-color:black; margin: 0 auto; text-align: center;">
                    			<div class="col-sm-12" style="margin-top: 5px;">
                    				<label class="col-sm-4 control-label">￥</label>
                    				<div class="col-sm-8">
	                    				<input type="text" class="form-control" name="rechargeRulelist[${t}].money" id="rechargeRulelist[${t}].money" value="${map.rechargeRulelist[t].money}" decimalNum="2" positiveNum="0" min="0" max="10000" required>
	                    				<input type="hidden" name="rechargeRulelist[${t}].game_balance" id="rechargeRulelist[${t }].game_balance" value="${map.rechargeRulelist[t].game_balance}">
	                    				<input type="hidden" name="rechargeRulelist[${t}].sort" id="rechargeRulelist[${t }].sort" value="0">
	                    				<input type="hidden" name="rechargeRulelist[${t}].id" id="rechargeRulelist[${t }].id" value="${map.rechargeRulelist[t].id}">
                    				</div>
                    			</div>
                    			<div class="col-sm-12">
                    				<label class="col-sm-4 control-label">赠送</label>
                    				<div class="col-sm-8">
                    					<input type="text" class="col-sm-4 form-control" name="rechargeRulelist[${t }].give_game_balance" id="rechargeRulelist[${t }].give_game_balance" value="${map.rechargeRulelist[t].give_game_balance}" decimalNum="2" min="0" max="10000" required >
                    				</div>
                    			</div>
                    		</div>
                    	</div>
						</#list>
                    	<div class="col-sm-4 form-group" style="display: none;">
                    		<label class="col-sm-4 control-label" for="cname"><span style="color:red">*</span>游戏定价：1点=</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="price_proportion" id="price_proportion" value="${price}" placeholder="请输入兑换金额" title="请输入兑换金额" >
                            </div>
                            <label class="col-sm-4" style="margin: 0 auto; text-align: center;">元</label>
                    	</div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_EQUINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <#-- <button type="button" class="btn btn-sm btn-default wt-close">关闭</button> -->
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
        
        //  保存
        $(".wt-save").click(function () {
        	var price = $("#price_proportion").val();
        	price = parseFloat(price);
        	var i0 = $("input[name='rechargeRulelist[0].money']").val();
        	var i1 = $("input[name='rechargeRulelist[1].money']").val();
        	var i2 = $("input[name='rechargeRulelist[2].money']").val();
        	var i3 = $("input[name='rechargeRulelist[3].money']").val();
        	var i4 = $("input[name='rechargeRulelist[4].money']").val();
        	var i5 = $("input[name='rechargeRulelist[5].money']").val();
        	var i6 = $("input[name='rechargeRulelist[6].money']").val();
        	var i7 = $("input[name='rechargeRulelist[7].money']").val();
        	var i8 = $("input[name='rechargeRulelist[8].money']").val();
        	i0 = parseFloat(i0);
        	i1 = parseFloat(i1);
        	i2 = parseFloat(i2);
        	i3 = parseFloat(i3);
        	i4 = parseFloat(i4);
        	i5 = parseFloat(i5);
        	i6 = parseFloat(i6);
        	i7 = parseFloat(i7);
        	i8 = parseFloat(i8);
        	var game_balance0 = i0/price;
        	var game_balance1 = i1/price;
        	var game_balance2 = i2/price;
        	var game_balance3 = i3/price;
        	var game_balance4 = i4/price;
        	var game_balance5 = i5/price;
        	var game_balance6 = i6/price;
        	var game_balance7 = i7/price;
        	var game_balance8 = i8/price;
        	$("input[name='rechargeRulelist[0].game_balance']").val(game_balance0);
        	$("input[name='rechargeRulelist[1].game_balance']").val(game_balance1);
        	$("input[name='rechargeRulelist[2].game_balance']").val(game_balance2);
        	$("input[name='rechargeRulelist[3].game_balance']").val(game_balance3);
        	$("input[name='rechargeRulelist[4].game_balance']").val(game_balance4);
        	$("input[name='rechargeRulelist[5].game_balance']").val(game_balance5);
        	$("input[name='rechargeRulelist[6].game_balance']").val(game_balance6);
        	$("input[name='rechargeRulelist[7].game_balance']").val(game_balance7);
        	$("input[name='rechargeRulelist[8].game_balance']").val(game_balance8);
        	
        	
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
            	$("#equname").prop("disabled",false);
                WT.wt_ajax_form('${base}/a/rechargeRule/rechargeRule_save','layerForm',function(data){
                    WT.wt_alert('保存成功',function(index){
                    	WT.wt_reload_jqtable(parent);
                    	layer.close(index);
                    });
                });
            });
        });
    });
    
    function addOperation(){
		var operationIndex = 1;
		var operation_html='<div id="show">\
	                            <div class="col-sm-4 form-group">\
	                                <label class="col-sm-3 control-label" for="equno"><span style="color:red">*</span>充值金额</label>\
	                                <div class="col-sm-9">\
	                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入充值金额" title="请输入充值金额" required>\
	                                </div>\
	                            </div>\
	                            <div class="col-sm-4 form-group">\
	                                <label class="col-sm-3 control-label" for="equno"><span style="color:red">*</span>游戏点数</label>\
	                                <div class="col-sm-9">\
	                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入游戏点数" title="请输入游戏点数" required>\
	                                </div>\
	                            </div>\
	                            <div class="col-sm-4 form-group">\
	                                <label class="col-sm-3 control-label" for="equno"><span style="color:red">*</span>赠送游戏点数</label>\
	                                <div class="col-sm-9">\
	                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入赠送游戏点数" title="请输入赠送游戏点数" required>\
	                                </div>\
	                            </div>\
	                            <div class="col-sm-4 form-group">\
	                                <label class="col-sm-3 control-label" for="equno"><span style="color:red">*</span>排序</label>\
	                                <div class="col-sm-9">\
	                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请排序" title="请排序" required>\
	                                </div>\
	                            </div>\
	                        </div>';
		/*  var target=$("#btn").parentNode.next(); */
		/* var div=$(document).createElement(""); */
		$("#show1").append(operation_html);
		operationIndex++;
	};
    
</script>
</body>
</#escape>
</html>
