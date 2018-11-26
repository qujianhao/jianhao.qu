<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#--内容开始-->
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/org/org_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <div class="row">
                    <div class="col-sm-3 form-group">
                        <label for="name">机构名称</label>
                        <input type="text" class="form-control" name="name" id="name" value="${orgEntity.name}"
                               placeholder="请输入机构名称" required>
                    </div>
                    <div class="col-sm-3 form-group">
                        <label for="isPublish">是否发布</label>
                        <select class="form-control" name="isPublish" id="isPublish"
                                data-wt-auto="${orgEntity.isPublish}" required>
                            <option value="">请选择</option>
                            <option value="1">已发布</option>
                            <option value="0">未发布</option>
                        </select>
                    </div>
                    <div class="col-sm-3 form-group">
                        <label for="name">排序</label>
                        <input type="number" class="form-control" name="orderNum" id="orderNum" value="${orgEntity.orderNum!'0'}"
                               placeholder="请输入排序" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6 form-group">
                        <label for="summary">备注</label>
                        <textarea class="form-control" name="summary" id="summary" maxlength="250">${orgEntity.summary}</textarea>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-center">
            <input type="hidden" name="id" value="${orgEntity.id}">
            <input type="hidden" name="parent" value="${parent}">
            <#if orgEntity??>
                <button type="button" class="btn btn-sm btn-info wt-edit">保存</button>
            <#else>
                <button type="button" class="btn btn-sm btn-info wt-save">保存</button>
            </#if>
            <button type="button" class="btn btn-sm btn-info wt-close">关闭</button>
        </div>
    </div>
</form>
<#--内容结束-->

    <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  新建
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/org/org_add','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        if (parent != null && parent != undefined && parent.wt_done){
                            parent.wt_done();
                        }
                        WT.wt_close();
                    });
                });
            });
        });
        //  更新
        $(".wt-edit").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/org/org_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        if (parent != null && parent != undefined && parent.wt_done){
                            parent.wt_done();
                        }
                        WT.wt_close();
                    });
                });
            });
        });
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
