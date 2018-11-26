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
                        <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="true">配置说明</a></li>
                        <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="true">单独选择</a></li>
                        <li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="true">级联选择</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何引入</h4>
                                <div>
                                    <dd>使用jquery方法将一个select控件设置为ajax组件</dd>
                                    <dd><code>$("#test1").wtselect({});</code></dd>
                                    <dd><code>$("#test1").wtselect({paramsProvider: function(element, options){return 'id=1'}});</code></dd>
                                </div>
                                <br/>
                                <h4 class="page-header">参数配置</h4>
                                <div>
                                    <dt>页面元素配置：</dt>
                                    <dd><code>data-wt-url</code>：表示当前select加载使用的url请求，需要提供对应后台的接口，接口返回ApiResult格式，详情见PluginsController的plugins_demo_wtselect方法</dd>
                                    <dd><code>data-wt-name</code>：生成的option的内容，支持使用{name}进行参数格式化</dd>
                                    <dd><code>data-wt-value</code>：生成的option的value，支持使用{name}进行参数格式化</dd>
                                    <dd><code>data-wt-cacheable</code>：可为空，默认为true,如果指定属性为false表示不使用缓存，每次父组件切换都会触发网络请求</dd>
                                    <dd><code>data-wt-lazyload</code>：可为空，默认为false,如果指定属性为true表示元素内容会先使用界面的内容，只有当父元素改变时才会重新请求</dd>
                                    <dd><code>data-wt-params</code>：可为空，当url请求需要附带参数时可以设置这个属性</dd>
                                    <dd><code>data-wt-paramSelector</code>：可为空，动态参数支持，当需要动态传递参数时可以设置这个属性，如 <code>data-wt-params="type=1&id={data-wt-select-value}" data-wt-paramSelector="#test20"</code>表示当前select加载时请求的url的参数为type=1&id=[从$('#test20').attr('data-wt-select-value')获取到的值] </dd>
                                    <dd><code>data-wt-parentSelector</code>：可为空，相关联的父select，当父元素值发生变化时，当前元素会重新请求url并刷新</dd>
                                </div>



                            </section>
                        </div>

                        <div class="tab-pane" id="tab_2">
                            <section class="form-horizontal">
                                <h4 class="page-header">单独选择</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test1">下拉列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" name="test1" id="test1"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-params="type=0"
                                                    data-wt-name="第一个列表 - {name}" data-wt-value="{value}"
                                            ></select>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>

                        <div class="tab-pane " id="tab_3">

                            <section class="form-horizontal">
                                <h4 class="page-header">级联选择-延迟加载</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test30">第一个列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-1" name="test30" id="test30" data-wt-lazyload="true" >
                                                <option value="">请选择</option>
                                                <option value="1">选项1</option>
                                                <option value="2">选项2</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test31">第二个列表</label>
                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-1" name="test31" id="test31"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-name="第二个列表 - {name}" data-wt-value="{value}"
                                                    data-wt-lazyload="true"
                                                    data-wt-paramSelector="#test30" data-wt-params="type=1&id={data-wt-select-value}"
                                                    data-wt-parentSelector="#test30"
                                                    >
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </section>


                            <section class="form-horizontal">
                                <h4 class="page-header">级联选择</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test20">第一个列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-2" name="test20" id="test20"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-params="type=0" data-wt-cacheable="false"
                                                    data-wt-name="第一个列表 - {name}" data-wt-value="{value}"
                                            ></select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test21">第二个列表</label>
                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-2" name="test21" id="test21"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-name="第二个列表 - {name}" data-wt-value="{value}"
                                                    data-wt-paramSelector="#test20" data-wt-params="type=1&id={data-wt-select-value}"
                                                    data-wt-parentSelector="#test20"
                                            ></select>
                                        </div>
                                    </div>
                                </div>
                            </section>

                            <section class="form-horizontal">
                                <h4 class="page-header">级联选择</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test10">第一个列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-3" name="test10" id="test10"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-params="type=0"
                                                    data-wt-name="第一个列表 - {name}" data-wt-value="{value}"
                                            ></select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test11">第二个列表</label>
                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-3" name="test11" id="test11"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-name="第二个列表 - {name}" data-wt-value="{value}"
                                                    data-wt-paramSelector="#test10" data-wt-params="type=1&id={data-wt-select-value}"
                                                    data-wt-parentSelector="#test10"
                                            ></select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test12">第三个列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control cascade-select-3" name="test12" id="test12"
                                                    data-wt-url="${base}/a/plugins/plugins_demo_wtselect"
                                                    data-wt-name="第三个列表 - {name}" data-wt-value="{value}"
                                                    data-wt-paramSelector="#test11" data-wt-params="type=2&id={data-wt-select-value}"
                                                    data-wt-parentSelector="#test11"
                                            ></select>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
    <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        //  单独选择
        $("#test1").wtselect({
            paramsProvider: function (element, options) {
                var params = "id=1&pid=1";
                return params;
            }
        });

        //  级联选择
        $(".cascade-select-1").wtselect();
        $(".cascade-select-2").wtselect();
        $(".cascade-select-3").wtselect();

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
