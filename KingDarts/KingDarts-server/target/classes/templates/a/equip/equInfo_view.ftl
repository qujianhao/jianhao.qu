<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/equInfo/equInfo_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equno">设备编号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入设备编号" title="请输入设备编号" readonly>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="models">设备型号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="models" id="models" value="${entity.models}" placeholder="请输入型号" title="请输入型号" readonly>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equname">设备名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equname" id="equname" value="${entity.equname}" placeholder="请输入设备名称" title="请输入设备名称" readonly>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="prod_batch">生产批次</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="prod_batch" id="prod_batch" value="${entity.prod_batch}" placeholder="请输入生产批次" title="请输入生产批次" readonly>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equ_version">使用版本</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equ_version" id="equ_version" value="${entity.equ_version}" placeholder="请输入使用版本" title="请输入使用版本" readonly>
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
