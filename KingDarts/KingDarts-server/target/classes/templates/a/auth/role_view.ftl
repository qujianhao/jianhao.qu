<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/role/role_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="name">name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="name" id="name" value="${entity.name}" placeholder="请输入name" title="请输入name" required readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="code_content">code_content</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="code_content" id="code_content" value="${entity.code_content}" placeholder="请输入code_content" title="请输入code_content"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="prem_content">prem_content</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="prem_content" id="prem_content" value="${entity.prem_content}" placeholder="请输入prem_content" title="请输入prem_content"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="order_num">order_num</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="order_num" id="order_num" value="${entity.order_num}" placeholder="请输入order_num" title="请输入order_num" required readonly >
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
