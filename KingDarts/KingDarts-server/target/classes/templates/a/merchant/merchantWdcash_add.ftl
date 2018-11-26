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
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="merno">merno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="merno" id="merno" value="${entity.merno}" placeholder="请输入merno" title="请输入merno" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="meraccount">meraccount</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="meraccount" id="meraccount" value="${entity.meraccount}" placeholder="请输入meraccount" title="请输入meraccount" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="mer_type">mer_type</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mer_type" id="mer_type" value="${entity.mer_type}" placeholder="请输入mer_type" title="请输入mer_type" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="me_rmobile">me_rmobile</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="me_rmobile" id="me_rmobile" value="${entity.me_rmobile}" placeholder="请输入me_rmobile" title="请输入me_rmobile" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="cash_nums">cash_nums</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="cash_nums" id="cash_nums" value="${entity.cash_nums}" placeholder="请输入cash_nums" title="请输入cash_nums" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label">apply_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="apply_time" id="apply_time" placeholder="请输入apply_time" class="form-control wt-datepicker" value="${entity.apply_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="wd_memos">wd_memos</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="wd_memos" id="wd_memos" value="${entity.wd_memos}" placeholder="请输入wd_memos" title="请输入wd_memos" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="wd_status">wd_status</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="wd_status" id="wd_status" value="${entity.wd_status}" placeholder="请输入wd_status" title="请输入wd_status" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="pay_types">pay_types</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="pay_types" id="pay_types" value="${entity.pay_types}" placeholder="请输入pay_types" title="请输入pay_types" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label">paysuces_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="paysuces_time" id="paysuces_time" placeholder="请输入paysuces_time" class="form-control wt-datepicker" value="${entity.paysuces_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="account_name">account_name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="account_name" id="account_name" value="${entity.account_name}" placeholder="请输入account_name" title="请输入account_name" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="acouunt_no">acouunt_no</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="acouunt_no" id="acouunt_no" value="${entity.acouunt_no}" placeholder="请输入acouunt_no" title="请输入acouunt_no" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="bank_name">bank_name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="bank_name" id="bank_name" value="${entity.bank_name}" placeholder="请输入bank_name" title="请输入bank_name" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="pay_order_no">pay_order_no</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="pay_order_no" id="pay_order_no" value="${entity.pay_order_no}" placeholder="请输入pay_order_no" title="请输入pay_order_no" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="fail_reseaon">fail_reseaon</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="fail_reseaon" id="fail_reseaon" value="${entity.fail_reseaon}" placeholder="请输入fail_reseaon" title="请输入fail_reseaon" >
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_MERCHANTWDCASH:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">新建</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
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
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/merchantWdcash/merchantWdcash_add','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
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
