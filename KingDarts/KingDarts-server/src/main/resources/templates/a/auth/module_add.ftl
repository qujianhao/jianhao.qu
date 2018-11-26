<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
    <#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/module/module_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>

                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label class="col-sm-3 control-label" for="name"><span class="wt-required">*</span>模块名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name" value="${entity.name}"
                                       placeholder="请输入模块名称" title="请输入模块名称" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label class="col-sm-3 control-label" for="icon">模块图标</label>

                            <div class="col-sm-9">
                                <#if (entity.icon)?default("")?trim?length lte 0>
                                    <i class="fa fa-fw fa-circle-o" id="icon_display"></i>
                                <#else >
                                    <i class="fa fa-fw ${entity.icon}" id="icon_display"></i>
                                </#if>
                                &nbsp;&nbsp;
                                <button type="button" class="btn btn-sm btn-default wt-icon-picker">选择图标</button>
                                <input type="hidden" name="icon" id="icon" value="${entity.icon}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label class="col-sm-3 control-label" for="url"><span class="wt-required">*</span>模块访问地址</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="url" id="url" value="${entity.url}"
                                       placeholder="请输入访问地址" title="请输入访问地址" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label class="col-sm-3 control-label" for="code"><span class="wt-required">*</span>模块编码</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="code" id="code" value="${entity.code}"
                                       placeholder="请输入模块编码" title="请输入模块编码" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 form-group">
                            <label class="col-sm-3 control-label" for="order_num"><span class="wt-required">*</span>模块排序</label>

                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="order_num" id="order_num"
                                       value="${entity.order_num}" placeholder="请输入排序" title="请输入排序" required>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="parent" id="parent" value="${parent}">
            <@wtAuth hasPrem="_MODULE:ADD" >
                <button type="button" class="btn btn-sm btn-info wt-save">新建</button></@wtAuth>
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
                WT.wt_ajax_form('${base}/a/module/module_add', 'layerForm', function (data) {
                    WT.wt_alert('保存成功', function () {
                        if (parent != null && parent != undefined && parent.wt_done) {
                            parent.wt_done();
                        }
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
        //  选择图标
        $(".wt-icon-picker").click(function () {
            WT.wt_open_fullscreen('${base}/a/module/module_icon_list');
        });
    });
</script>
</body>
</#escape>
</html>
