<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">

<#--内容开始-->
    <#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/role/role_edit">
    <section class="content wt-layer-content">
        <div class="box box-default">
            <div class="box-body">
                <div class="row">
                    <div class="col-sm-3 form-group">
                        <label for="name">角色名</label>
                        <input type="text" class="form-control" name="name" id="name" value="${roleEntity.name}"
                               placeholder="请输入角色名" required>
                    </div>
                    <div class="col-sm-3 form-group">
                        <label for="is_publish">是否发布</label>
                        <select class="form-control" name="isPublish" id="isPublish"
                                data-wt-auto="${roleEntity.isPublish}" required>
                            <option value="">请选择</option>
                            <option value="1">已发布</option>
                            <option value="0">未发布</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <label for="name">权限列表</label>

                        <div class="wt-list">
                            <#list Request['permList']['0'] as module>
                                <div class="wt-list-title">${module.name}</div>
                                <ul>
                                    <#list Request['permList']['${module.id}'] as nodePerm>
                                        <li class="wt-list-row wt-bottom-border">
                                            <div style="display: inline-block;min-width: 140px;">${nodePerm.name}</div>
                                            <div style="display: inline-block;margin-left: 32px;">
                                            </div>
                                            <div class="wt-list-row-opts" style="display: none;">
                                                <a href="javascript:void(0);" data-id="${nodePerm.id}"
                                                   class="ck_checkall">全选</a>
                                                <a href="javascript:void(0);" data-id="${nodePerm.id}"
                                                   class="ck_uncheckall">反选</a>
                                            </div>
                                        </li>
                                        <ul>
                                            <#list Request['permList']['${nodePerm.id}'] as perm>
                                                <li class="wt-list-row wt-bottom-border">
                                                    <div style="display: inline-block;min-width: 100px;">${perm.name}</div>
                                                    <div style="display: inline-block;margin-left: 32px;">
                                                        <input type="checkbox" id="${perm.id}_id" name="subperm_codes"
                                                               value="${module.id},${nodePerm.id},${perm.id}" ${rolePermArray?seq_contains('${perm.id}')?string("checked", "")}
                                                               class="ck_${nodePerm.id} ck_${perm.id}">
                                                        <label for="${perm.id}_id">模块可见</label>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="checkbox" id="${perm.id}_view" name="perm_codes"
                                                               value="${perm.code}:VIEW" ${roleCodeArray?seq_contains('${perm.code}:VIEW')?string("checked", "")}
                                                               class="ck_${nodePerm.id} ck_${perm.id}">
                                                        <label for="${perm.id}_view">查看</label>
                                                        <input type="checkbox" id="${perm.id}_add" name="perm_codes"
                                                               value="${perm.code}:ADD" ${roleCodeArray?seq_contains('${perm.code}:ADD')?string("checked", "")}
                                                               class="ck_${nodePerm.id} ck_${perm.id}">
                                                        <label for="${perm.id}_add">增加</label>
                                                        <input type="checkbox" id="${perm.id}_edit" name="perm_codes"
                                                               value="${perm.code}:EDIT" ${roleCodeArray?seq_contains('${perm.code}:EDIT')?string("checked", "")}
                                                               class="ck_${nodePerm.id} ck_${perm.id}">
                                                        <label for="${perm.id}_edit">编辑</label>
                                                        <input type="checkbox" id="${perm.id}_delete" name="perm_codes"
                                                               value="${perm.code}:DELETE" ${roleCodeArray?seq_contains('${perm.code}:DELETE')?string("checked", "")}
                                                               class="ck_${nodePerm.id} ck_${perm.id}">
                                                        <label for="${perm.id}_delete">删除</label>
                                                    </div>
                                                    <div class="wt-list-row-opts" style="display: none;">
                                                        <a href="javascript:void(0);" data-id="${perm.id}"
                                                           class="ck_checkall">全选</a>
                                                        <a href="javascript:void(0);" data-id="${perm.id}"
                                                           class="ck_uncheckall">反选</a>
                                                    </div>
                                                </li>
                                            </#list>
                                        </ul>
                                    </#list>
                                </ul>
                            </#list>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-center">
            <input type="hidden" id="roleId" name="roleId" value="${roleEntity.id}">
            <button type="button" class="btn btn-sm btn-info wt-save">保存</button>
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
        //  保存
        $(".wt-save").click(function () {
            if (!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/role/role_edit', 'layerForm', function (data) {
                    WT.wt_alert('保存成功', function () {
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
        //  全选
        $(".ck_checkall").click(function () {
            $(".ck_" + $(this).attr('data-id')).prop('checked', true);
        });
        //  反选
        $(".ck_uncheckall").click(function () {
            $(".ck_" + $(this).attr('data-id')).prop('checked', false);
        });
        //  全选、反选框鼠标悬浮显示
        $(".wt-list-row").mouseover(function () {
            $(this).children(".wt-list-row-opts").show();
        });
        $(".wt-list-row").mouseout(function () {
            $(this).children(".wt-list-row-opts").hide();
        });


    });

</script>
</body>
</#escape>
</html>
