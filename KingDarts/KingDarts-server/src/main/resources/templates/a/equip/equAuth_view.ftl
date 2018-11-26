<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/equAuth/equAuth_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equno">equno</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入equno" title="请输入equno" required readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="auth_user">auth_user</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="auth_user" id="auth_user" value="${entity.auth_user}" placeholder="请输入auth_user" title="请输入auth_user"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="auth_type">auth_type</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="auth_type" id="auth_type" value="${entity.auth_type}" placeholder="请输入auth_type" title="请输入auth_type"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="merchant">merchant</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="merchant" id="merchant" value="${entity.merchant}" placeholder="请输入merchant" title="请输入merchant"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="auth_no">auth_no</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="auth_no" id="auth_no" value="${entity.auth_no}" placeholder="请输入auth_no" title="请输入auth_no"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="auth_name">auth_name</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="auth_name" id="auth_name" value="${entity.auth_name}" placeholder="请输入auth_name" title="请输入auth_name"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" >auth_time</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type="text" name="auth_time" id="auth_time" placeholder="请输入auth_time" class="form-control wt-datepicker" value="${entity.auth_time}"   readonly >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="placeno">placeno</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="placeno" id="placeno" value="${entity.placeno}" placeholder="请输入placeno" title="请输入placeno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="acc_type">acc_type</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="acc_type" id="acc_type" value="${entity.acc_type}" placeholder="请输入acc_type" title="请输入acc_type"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" >add_time</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type="text" name="add_time" id="add_time" placeholder="请输入add_time" class="form-control wt-datepicker" value="${entity.add_time}"   readonly >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
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
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        WT.wt_set_navtitle('详情');   //  设置页面标题
        $(".wt-close").click(function () {  //  关闭
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
