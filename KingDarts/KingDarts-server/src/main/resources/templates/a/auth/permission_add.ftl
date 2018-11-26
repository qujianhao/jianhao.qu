<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/permission/permission_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="module_id">所属模块</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="module_id" name="module_id">
                                        <#list moduleList as module>
                                            <option value="${module.id}">${module.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="permission_code">权限列表</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="permission_code" id="permission_code" value="${entity.permission_code}" placeholder="请输入permission_code" title="请输入permission_code" >
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_PERMISSION:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">新建</button></@wtAuth>
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
                WT.wt_ajax_form('${base}/a/permission/permission_add','layerForm',function(data){
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
