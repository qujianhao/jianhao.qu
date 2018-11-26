<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/plugins/plugins_edit">
    <section class="content">
        <div class="box box-default form-horizontal">
            <div class="box-body">
                <div class="row">
                    <div class="col-sm-6 form-group">
                        <label class="col-sm-3 control-label" for="stubName">插件名称</label>
                        <div class="col-sm-9"><input type="text" class="form-control" name="stubName" id="stubName" value="${entity.stubName}" readonly></div>
                    </div>
                    <#list entity.attrMap?keys as attrKey>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="${entity.attrMap["${attrKey}"].key}">${entity.attrMap["${attrKey}"].name}</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="${entity.attrMap["${attrKey}"].key}" id="${entity.attrMap["${attrKey}"].key}" value="${entity.attrMap["${attrKey}"].value}" placeholder="${entity.attrMap["${attrKey}"].summary}">
                            </div>
                        </div>
                    </#list>
                </div>

            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="mapKey" id="mapKey" value="${mapKey}"/>
            <@wtAuth hasPrem="PLUGINS:EDIT" >
                <button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
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
            if (!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/plugins/plugins_edit', 'layerForm', function (data) {
                    WT.wt_alert('保存成功', function () {
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
