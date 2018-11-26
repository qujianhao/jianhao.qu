<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#--内容开始-->
<#include "./a/commons/nav.ftl" />
<form name="layerForm" id="layerForm" action="">
<section class="content">
    <div class="box box-default">
        <div class="box-body">
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="name">账号名称</label>
                    <input type="text" class="form-control" name="username" id="username" value="${accountEntity.username}" readonly="readonly">
                </div>
                <div class="col-sm-6 form-group">
                    <label for="name">用户名称</label>
                    <input type="text" class="form-control" name="realname" id="realname" value="${accountEntity.realname}" readonly="readonly">
                </div>
                <div class="col-sm-6 form-group">
                    <label for="isPublish">是否发布</label>
                    <select class="form-control" name="isPublish" id="isPublish"
                            data-wt-auto="${accountEntity.isPublish}" disabled="disabled">
                        <option value="">请选择</option>
                        <option value="1">已发布</option>
                        <option value="0">未发布</option>
                    </select>
                </div>
                <div class="col-sm-6 form-group">
                    <label for="password">用户密码</label>
                    <input type="password" class="form-control" name="password" id="password" value="******"
                           readonly="readonly">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="remark">备注</label>
                    <textarea class="form-control" name="remark" id="remark" maxlength="250" readonly="readonly">${accountEntity.remark}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="isSpec">用户权限类型</label>
                    <select class="form-control" name="isSpec" id="isSpec" data-wt-auto="${accountEntity.isSpec}" disabled="disabled" >
                        <option value="">请选择</option>
                        <option value="0">角色用户</option>
                        <option value="1">独立权限用户</option>
                    </select>
                </div>
                <div class="col-sm-6 form-group wt-spec-role" style="display: none;">
                    <label for="roleId">用户角色</label>
                    <select class="form-control" name="roleId" id="roleId" data-wt-auto="${accountEntity.roleId}" disabled="disabled" >
                        <option value="">请选择</option>
                        <#list Request['roleEntityList'] as roleEntity>
                            <option value="${roleEntity.id}">${roleEntity.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="row wt-spec-notrole" style="display: none;">
                <div class="col-sm-12 form-group">
                    <label for="name">权限列表</label>
                    <div class="wt-list">
                        <#list Request['permList']['0'] as module>
                            <div class="wt-list-title">${module.name}</div>
                            <ul>
                                <#list Request['permList']['${module.id}'] as nodePerm>
                                    <ul>
                                        <#list Request['permList']['${nodePerm.id}'] as perm>
                                            <li class="wt-list-row wt-bottom-border">
                                                <div style="display: inline-block;min-width: 100px;">${perm.name}</div>
                                                <div style="display: inline-block;margin-left: 32px;">
                                                    <input type="checkbox" id="${perm.id}_id" name="subperm_codes"
                                                           value="${module.id},${nodePerm.id},${perm.id}" ${rolePermArray?seq_contains('${perm.id}')?string("checked", "")}
                                                           class="ck_${nodePerm.id} ck_${perm.id}" disabled="disabled">
                                                    <label for="${perm.id}_id">模块可见</label>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="checkbox" id="${perm.id}_view" name="perm_codes"
                                                           value="${perm.code}:VIEW" ${roleCodeArray?seq_contains('${perm.code}:VIEW')?string("checked", "")}
                                                           class="ck_${nodePerm.id} ck_${perm.id}" disabled="disabled">
                                                    <label for="${perm.id}_view">查看</label>
                                                    <input type="checkbox" id="${perm.id}_add" name="perm_codes"
                                                           value="${perm.code}:ADD" ${roleCodeArray?seq_contains('${perm.code}:ADD')?string("checked", "")}
                                                           class="ck_${nodePerm.id} ck_${perm.id}" disabled="disabled">
                                                    <label for="${perm.id}_add">增加</label>
                                                    <input type="checkbox" id="${perm.id}_edit" name="perm_codes"
                                                           value="${perm.code}:EDIT" ${roleCodeArray?seq_contains('${perm.code}:EDIT')?string("checked", "")}
                                                           class="ck_${nodePerm.id} ck_${perm.id}" disabled="disabled">
                                                    <label for="${perm.id}_edit">编辑</label>
                                                    <input type="checkbox" id="${perm.id}_delete" name="perm_codes"
                                                           value="${perm.code}:DELETE" ${roleCodeArray?seq_contains('${perm.code}:DELETE')?string("checked", "")}
                                                           class="ck_${nodePerm.id} ck_${perm.id}" disabled="disabled">
                                                    <label for="${perm.id}_delete">删除</label>
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
            if(!$("#layerForm").valid())
                return false;
            if ($("#isSpec").val() === "0" && $("#roleId").val() == ""){
                WT.wt_alert('请选择角色');
                return;
            }
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/account/account_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
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

        //  切换用户类型
        $("#isSpec").change(function () {
            $(".wt-spec-role").hide();
            $(".wt-spec-notrole").hide();
            if ($(this).val() === "0"){
                $(".wt-spec-role").show();
            }else if ($(this).val() === "1"){
                $(".wt-spec-notrole").show();
            }
        });
        $("#isSpec").change();
    });
</script>
</body>
</#escape>
</html>
