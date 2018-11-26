<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/plugins/plugins_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-4 form-group">
                            <label for="id">ID</label>
                            <input type="text" class="form-control" name="id" id="id" value="${entity.id}" placeholder="请输入ID" required readonly >
                        </div>
                        <div class="col-sm-4 form-group">
                            <label for="type">类型</label>
                            <input type="text" class="form-control" name="type" id="type" value="${entity.type}" placeholder="请输入类型" required readonly >
                        </div>
                        <div class="col-sm-4 form-group">
                            <label for="title">标题</label>
                            <input type="text" class="form-control" name="title" id="title" value="${entity.title}" placeholder="请输入标题"  readonly >
                        </div>
                        <div class="col-sm-12 form-group">
                            <label for="summary">详情</label>
                            <textarea class="form-control" name="summary" id="summary"  readonly >${entity.summary}</textarea>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label for="app_id">App ID</label>
                            <textarea class="form-control" name="app_id" id="app_id"  readonly >${entity.app_id}</textarea>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label for="key">钥匙</label>
                            <textarea class="form-control" name="key" id="key"  readonly >${entity.key}</textarea>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label for="secret">秘密</label>
                            <textarea class="form-control" name="secret" id="secret"  readonly >${entity.secret}</textarea>
                        </div>
                        <div class="col-sm-4 form-group">
                            <label for="attrs">属性</label>
                            <input type="text" class="form-control" name="attrs" id="attrs" value="${entity.attrs}" placeholder="请输入属性"  readonly >
                        </div>
                        <div class="col-sm-4 form-group">
                            <label for="order_num">阶数</label>
                            <input type="text" class="form-control" name="order_num" id="order_num" value="${entity.order_num}" placeholder="请输入阶数" required readonly >
                        </div>
                </div>
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
