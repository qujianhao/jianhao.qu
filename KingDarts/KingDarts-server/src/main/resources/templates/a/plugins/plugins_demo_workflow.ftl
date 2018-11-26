<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body wt-demo-content">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/plugins/plugins_edit">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="false">下载文件</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">使用示例</h4>


                                <div>
                                    <button type="button" class="btn btn-sm btn-default" id="btn-process-create">创建工作流</button>
                                    <button type="button" class="btn btn-sm btn-default" id="btn-process-query">查询当前工作流实例</button>
                                    <button type="button" class="btn btn-sm btn-default" id="btn-process-clean">清除工作流示例</button>
                                </div>
                                <br/>

                                <div>
                                    <button type="button" class="btn btn-sm btn-default" id="btn-task-query">查询当前任务列表</button>
                                </div>

                                <br/>
                                <div >
                                    <input type="text" name="taskId" id="taskId"></input>
                                    <button type="button" class="btn btn-sm btn-default" id="btn-task-commit">完成任务</button>
                                </div>

                                <br/>
                                <div >
                                    <button type="button" class="btn btn-sm btn-default" id="btn-test">测试</button>
                                </div>

                            </section>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>

    <br/>

    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
    <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });

        $("#btn-process-create").click(function () {
            WT.wt_confirm('是否查询?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/create', {}, function (data) {
                    WT.wt_alert('请求成功');
                });
            });
        });
        $("#btn-process-query").click(function () {
            WT.wt_confirm('是否查询?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/clean', {}, function (data) {
                    WT.wt_alert('请求成功');
                });
            });
        });
        $("#btn-process-clean").click(function () {
            WT.wt_confirm('是否查询?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/clean', {}, function (data) {
                    WT.wt_alert('请求成功');
                });
            });
        });


        $("#btn-task-query").click(function () {
            WT.wt_confirm('是否查询?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/taskList', {}, function (data) {
                    WT.wt_alert('查询成功');
                });
            });
        });
        $("#btn-task-commit").click(function () {
            WT.wt_confirm('是否提交?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/taskCommit', {taskId: $("#taskId").val()}, function (data) {
                    WT.wt_alert('提交成功');
                });
            });
        });

        $("#btn-test").click(function () {
            WT.wt_confirm('是否测试?', function () {
                WT.wt_ajax_jsondata('${base}/a/plugins/plugins_demo_flow/test', {}, function (data) {
                    WT.wt_alert('测试成功');
                });
            });
        });

    });
</script>
</body>
</#escape>
</html>
