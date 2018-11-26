<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/merchantWdcash/merchantWdcash_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="merno">商户编号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="merno" id="merno" value="${entity.merno}" readonly >
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="meraccount">商户名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="merName" id="merName" value="${merName}" readonly>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="captain">负责人</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="captain" id="captain" value="${captain}" readonly>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="mobile">联系方式</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mobile" id="mobile" value="${mobile}" readonly>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="nickname">收款人昵称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="nickname" id="nickname" value="${nickname}" readonly>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="userMobile">收款人手机</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="userMobile" id="userMobile" value="${userMobile}" readonly>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="cash_nums">提现金额</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="cash_nums" id="cash_nums" value="${entity.cash_nums}" readonly>
                                </div>
                            </div>
                           
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="id" id="id" value="${entity.id}"/>
             <button type="button" class="btn btn-sm btn-info wt-pass">审核通过</button>
             <button type="button" class="btn btn-sm btn-info wt-unpass">审核不通过</button>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('审核');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        //  保存
        $(".wt-pass").click(function () {
        	 var postdata = {id:$("#id").val(),type:"pass"};
            WT.wt_confirm('是否通过审核?', function () {
                WT.wt_ajax_formdata_silent('${base}/a/merchant/merchantWdcash_transfers',postdata,function(data){
                	WT.wt_alert('审核成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                },function(data){
                	var msg = data.msg || '审核失败'
                	WT.wt_alert(msg,function(){
                        WT.wt_close();
                    });
                });
            });
        });
        $(".wt-unpass").click(function () {
            var postdata = {id:$("#id").val(),type:"unpass"};
            WT.wt_confirm('是否不通过审核?', function () {
                WT.wt_ajax_formdata_silent('${base}/a/merchant/merchantWdcash_transfers',postdata,function(data){
                    WT.wt_alert('操作成功',function(){
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
