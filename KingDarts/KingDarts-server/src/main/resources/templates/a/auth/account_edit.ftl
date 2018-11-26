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
                    <label for="name">账号名</label>
                    <input type="text" class="form-control" name="username" id="username" value="${accountEntity.username}"
                           placeholder="请输入账号名" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label for="name">用户名称</label>
                    <input type="text" class="form-control" name="realname" id="realname" value="${accountEntity.realname}"
                           placeholder="请输入用户名称" required>
                </div>
                <div class="col-sm-6 form-group">
                    <label for="isPublish">是否发布</label>
                    <select class="form-control" name="is_publish" id="is_publish"
                            data-wt-auto="${accountEntity.is_publish}" required>
                        <option value="">请选择</option>
                        <option value="1">已发布</option>
                        <option value="0">未发布</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="password">用户密码</label>
                    <input type="password" class="form-control" name="password" id="password" value=""
                           placeholder="留空表示不修改密码" >
                </div>
                <div class="col-sm-6 form-group">
                    <label for="password_confirm">重复用户密码</label>
                    <input type="password" class="form-control" name="password_confirm" id="password_confirm" value=""
                           placeholder="留空表示不修改密码" equalTo="#password">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="remark">备注</label>
                    <textarea class="form-control" name="remark" id="remark" maxlength="250">${accountEntity.remark}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 form-group">
                    <label for="isSpec">用户权限类型</label>
                    <select class="form-control" name="is_spec" id="is_spec" data-wt-auto="0" required readonly>
                        <option value="0">角色用户</option>
                    </select>
                </div>
                <div class="col-sm-6 form-group wt-spec-role">
                    <label for="roleId">用户角色</label>
                    <select class="form-control" name="role_id" id="role_id" data-wt-auto="${accountEntity.role_id}">
                        <option value="">请选择</option>
                        <#list Request['roleEntityList'] as roleEntity>
                            <option value="${roleEntity.id}">${roleEntity.name}</option>
                        </#list>
                    </select>
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
