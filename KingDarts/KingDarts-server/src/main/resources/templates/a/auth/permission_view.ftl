<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/permission/permission_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="module_id">module_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="module_id" id="module_id" value="${entity.module_id}" placeholder="请输入module_id" title="请输入module_id" required readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="permission_code">permission_code</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="permission_code" id="permission_code" value="${entity.permission_code}" placeholder="请输入permission_code" title="请输入permission_code"  readonly >
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
