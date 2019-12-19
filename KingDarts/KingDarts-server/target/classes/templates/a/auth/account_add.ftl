<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#--内容开始-->
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/account/account_edit">
<section class="content">
    <div class="box box-default">
        <div class="box-body">
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="name"><span style="color:red">*</span>账号名</label>
                    <input type="text" class="form-control" name="username" id="username" value="${accountEntity.username}"
                           placeholder="请输入账号名" required maxlength="30">
                </div>
                <div class="col-sm-6 form-group">
                    <label for="name"><span style="color:red">*</span>姓名</label>
                    <input type="text" class="form-control" name="realname" id="realname" value="${accountEntity.realname}"
                           placeholder="请输入姓名" required maxlength="30">
                </div>
                <div class="col-sm-6 form-group">
                    <label for="isPublish"><span style="color:red">*</span>是否启用</label>
                    <select class="form-control" name="is_publish" id="is_publish"
                            data-wt-auto="${accountEntity.is_publish}" required>
                        <option value="">请选择</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                </div>
                <div class="col-sm-6 form-group">
                    <label for="password"><span style="color:red">*</span>用户密码</label>
                    <input type="password" class="form-control" name="password" id="password" value=""
                           placeholder="留空表示不修改密码" required maxlength="30">
                </div>
                <div class="col-sm-6 form-group">
                    <label for="password_confirm"><span style="color:red">*</span>重复用户密码</label>
                    <input type="password" class="form-control" name="password_confirm" id="password_confirm" value=""
                           placeholder="留空表示不修改密码" equalTo="#password" required maxlength="30">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="remark">备注</label>
                    <textarea class="form-control" name="remark" id="remark" maxlength="250">${accountEntity.remark}</textarea>
                </div>
            </div>
            <div class="row" style="display: none;">
                <div class="col-sm-6 form-group">
                    <label for="isSpec">用户权限类型</label>
                    <input type="text" name="is_spec" id="is_spec" value="0">
                    <!-- <select class="form-control" name="is_spec" id="is_spec" data-wt-auto="${accountEntity.is_spec}" required>
                        <option value="">请选择</option>
                        <option value="0">角色用户</option>
                        <option value="1">独立权限用户</option>
                    </select> -->
                </div>
                <div class="col-sm-6 form-group wt-spec-role" style="display: none;">
                    <label for="roleId">用户角色</label>
                    <input type="text" name="role_id" id="role_id" value="1">
                    <!-- <select class="form-control" name="role_id" id="role_id" data-wt-auto="${accountEntity.role_id}">
                        <option value="">请选择</option>
                        <#list Request['roleEntityList'] as roleEntity>
                            <option value="${roleEntity.id}">${roleEntity.name}</option>
                        </#list>
                    </select>-->
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
                                    <li class="wt-list-row wt-bottom-border">
                                        <div style="display: inline-block;min-width: 100px;">${nodePerm.name}</div>
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
        <input type="hidden" id="id" name="id" value="${accountEntity.id}">
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
            if(!$("#layerForm").valid())
                return false;
            if ($("#isSpec").val() === "0" && $("#roleId").val() == ""){
                WT.wt_alert('请选择角色');
                return;
            }
            var url = '${base}/a/account/account_add';
            if($("#id").val()){
            	url = '${base}/a/account/account_edit';
            }
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form(url,'layerForm',function(data){
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
        //  全选、反选框鼠标悬浮显示
        $(".wt-list-row").mouseover(function () {
            $(this).children(".wt-list-row-opts").show();
        });
        $(".wt-list-row").mouseout(function () {
            $(this).children(".wt-list-row-opts").hide();
        });

        //  切换用户类型
        $("#is_spec").change(function () {
            $(".wt-spec-role").hide();
            $(".wt-spec-notrole").hide();
            if ($(this).val() === "0"){
                $(".wt-spec-role").show();
            }else if ($(this).val() === "1"){
                $(".wt-spec-notrole").show();
            }
        });
        $("#is_spec").change();
    });
</script>
</body>
</#escape>
</html>
