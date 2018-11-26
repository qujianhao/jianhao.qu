<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<section class="content">

    <div class="page-header">
        <h3>
            文件插件
            <#if filePluginStub?exists>
                <small>(当前生效插件&nbsp;&nbsp;${filePluginStub.stubName})</small>
            </#if>

        </h3>
    </div>
    <div class="row">
        <#list filePluginsKeySet as key>
            <div class="col-sm-3">
                <div class="info-box">
                    <span class="info-box-icon <#if allPluginStubMap["${key}"].stubStateValue == 2 >bg-green<#else>bg-gray-light</#if>"><i
                            class="fa fa-upload"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-number">${allPluginStubMap["${key}"].stubName}</span>
                        <span class="info-box-text">多节点修改配置后需要重启服务</span>
                        <div class="info-box-text" style="margin-top: 8px;">
                            <div class="btn-group pull-right">
                                <button type="button" data-mapType="file" data-mapKey="${key}" class="btn btn-xs btn-default wt-config">配置</button>
                                <#if allPluginStubMap["${key}"].stubStateValue == 2 >
                                    <button type="button" data-mapType="file" data-mapKey="${key}" data-state="0"
                                            class="btn btn-xs btn-warning wt-state">停止
                                    </button>
                                <#else>
                                    <button type="button" data-mapType="file" data-mapKey="${key}" data-state="1"
                                            class="btn btn-xs btn-default wt-state">激活
                                    </button>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    <h2 class="page-header">支付插件</h2>

    <div class="row">
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-credit-card"></i></span>

                <div class="info-box-content">
                    <span class="info-box-number">Alipay</span>
                    <span class="info-box-text">支付插件</span>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-credit-card"></i></span>

                <div class="info-box-content">
                    <span class="info-box-number">Wechat pay</span>
                    <span class="info-box-text">支付插件</span>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-credit-card"></i></span>

                <div class="info-box-content">
                    <span class="info-box-number">易宝支付</span>
                    <span class="info-box-text">支付插件</span>
                </div>
            </div>
        </div>
    </div>
    <h2 class="page-header">代码示例</h2>

    <div class="row">
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">文件上传</span>
                    <span class="info-box-text">wtuploader.js</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="file" class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">下载文件</span>
                    <span class="info-box-text">download</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="code" data-mapKey="download"  class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">导出文件</span>
                    <span class="info-box-text">export</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="code" data-mapKey="export"  class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">详情页面</span>
                    <span class="info-box-text">module_edit.ftl</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="code" data-mapKey="detail"  class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">选择框</span>
                    <span class="info-box-text">wtselect.ftl</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="code" data-mapKey="wtselect"  class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="info-box">
                <span class="info-box-icon bg-gray-light"><i class="fa fa-file-code-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-number">可变表格</span>
                    <span class="info-box-text">wtchecktable.ftl</span>
                    <div class="info-box-text" style="margin-top: 8px;">
                        <div class="btn-group pull-right">
                            <button type="button" data-mapType="code" data-mapKey="wtchecktable"  class="btn btn-xs btn-default wt-demo">查看示例</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
    <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        //  查看示例
        $(".content").on('click', '.wt-demo', function () {
            var mapKey = $(this).attr('data-mapKey');
            var mapType = $(this).attr('data-mapType')
            WT.wt_open_layer('${base}/a/plugins/plugins_demo', {mapKey: mapKey, mapType: mapType}, '示例');
        });

        //  配置
        $(".content").on('click', '.wt-config', function () {
            var mapKey = $(this).attr('data-mapKey');
            var mapType = $(this).attr('data-mapType')
            WT.wt_open_layer('${base}/a/plugins/plugins_edit', {mapKey: mapKey, mapType: mapType}, '配置插件');
        });

        //  激活
        $(".content").on('click', '.wt-state', function () {
            var mapKey = $(this).attr('data-mapKey');
            var mapType = $(this).attr('data-mapType');
            var state = $(this).attr('data-state');
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_formdata('${base}/a/plugins/plugins_state', {
                    mapKey: mapKey,
                    mapType: mapType,
                    state: state
                }, function (data) {
                    WT.wt_alert('操作成功', function () {
                        location.reload();
                    });
                });
            });
        });

    });
</script>
</body>
</#escape>
</html>
