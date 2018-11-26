<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/card/card_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="card_id">card_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_id" id="card_id" value="${entity.card_id}" placeholder="请输入card_id" title="请输入card_id" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="card_no">card_no</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_no" id="card_no" value="${entity.card_no}" placeholder="请输入card_no" title="请输入card_no" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="card_id_hex">card_id_hex</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_id_hex" id="card_id_hex" value="${entity.card_id_hex}" placeholder="请输入card_id_hex" title="请输入card_id_hex" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="denomination">denomination</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="denomination" id="denomination" value="${entity.denomination}" placeholder="请输入denomination" title="请输入denomination" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="balance">balance</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="balance" id="balance" value="${entity.balance}" placeholder="请输入balance" title="请输入balance" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="card_type">card_type</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_type" id="card_type" value="${entity.card_type}" placeholder="请输入card_type" title="请输入card_type" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="cno">cno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="cno" id="cno" value="${entity.cno}" placeholder="请输入cno" title="请输入cno" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="user_id">user_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="user_id" id="user_id" value="${entity.user_id}" placeholder="请输入user_id" title="请输入user_id" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >bind_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="bind_time" id="bind_time" placeholder="请输入bind_time" class="form-control wt-datepicker" value="${entity.bind_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >add_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="add_time" id="add_time" placeholder="请输入add_time" class="form-control wt-datepicker" value="${entity.add_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="activation_equno">activation_equno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="activation_equno" id="activation_equno" value="${entity.activation_equno}" placeholder="请输入activation_equno" title="请输入activation_equno" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >activation_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="activation_time" id="activation_time" placeholder="请输入activation_time" class="form-control wt-datepicker" value="${entity.activation_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >change_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="change_time" id="change_time" placeholder="请输入change_time" class="form-control wt-datepicker" value="${entity.change_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="isvalid">isvalid</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="isvalid" id="isvalid" value="${entity.isvalid}" placeholder="请输入isvalid" title="请输入isvalid" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="give_balance">give_balance</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="give_balance" id="give_balance" value="${entity.give_balance}" placeholder="请输入give_balance" title="请输入give_balance" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="isdefault">isdefault</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="isdefault" id="isdefault" value="${entity.isdefault}" placeholder="请输入isdefault" title="请输入isdefault" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="coupon_balance">coupon_balance</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="coupon_balance" id="coupon_balance" value="${entity.coupon_balance}" placeholder="请输入coupon_balance" title="请输入coupon_balance" >
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
             <@wtAuth hasPrem="_CARD:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
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
                WT.wt_ajax_form('${base}/a/card/card_edit','layerForm',function(data){
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
