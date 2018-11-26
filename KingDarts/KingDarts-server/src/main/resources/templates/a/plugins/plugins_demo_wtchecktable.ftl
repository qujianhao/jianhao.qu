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
                        <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="true">表格</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何引入</h4>
                                <div>
                                    <dd>1.引入<pre><code class="language-java">&lt;script src=&quot;${base}/assets/plugins/wtchecktable/wtchecktable.js&quot;&gt;&lt;/script&gt;</code></pre></dd>
                                    <dd>2.加入标签 <pre><code class="language-java">&lt;div class=&quot;col-sm-12 form-group plan-1&quot; id=&quot;plan1&quot;  data-wt-name=&quot;mileage&quot; data-wt-url=&quot;${base}/a/vehicle/vehicleMaintenancePlan/vehicleMaintenancePlan_plan_table?type=1&quot;&gt; &lt;/div&gt;</code></pre> </dd>
                                    <dd>3.使用<pre><code class="language-java">$(".plan-1").wtchecktable();</code></pre></dd>
                                </div>
                                <br/>
                                <h4 class="page-header">参数配置</h4>
                                <div>
                                    <dt>页面元素配置：</dt>
                                    <dd><code>data-wt-name</code>可为空，表示当前表格提交到后台的name名称，默认为wt_checktable，提交到后台的数据分为两部分， 如默认配置下：
                                        一部分为定义的name名，后台可以通过request.getParameterValues("wt_checktable")获取列的key值 String[] keys；
                                        一部分为实际的数据内容，后台可以通过request.getParameterValues("wt_checktable_" + key[n])来获取String[]；
                                    </dd>
                                    <dd><code>data-wt-readonly</code>：可为空，表示是否只读，默认为可编辑状态，配置为 <code>data-wt-readonly="true"</code>表示只读 </dd>
                                </div>
                            </section>
                        </div>

                        <div class="tab-pane " id="tab_2">
                            <section class="form-horizontal">
                                <h4 class="page-header">上部控件</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test1">基本信息1</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="test1" id="test1" value="" placeholder="请输入基本信息1">
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test2">基本信息2</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="test2" id="test2" value="" placeholder="请输入基本信息2">
                                        </div>
                                    </div>
                                    <div class="col-sm-12 form-group item-plan" id="plan5" data-wt-name="itemPlan"
                                         data-wt-url="${base}/a/plugins/plugins_demo_wtchecktable?type=2"
                                         data-wt-reversal="true" > </div>
                                </div>
                            </section>
                            <section class="form-horizontal">
                                <h4 class="page-header">保养计划</h4>
                                <div class="row ">
                                    <div class="col-sm-12">
                                        <div class="nav-tabs-custom">
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#tab_10" data-toggle="tab" aria-expanded="true">时间保养计划</a></li>
                                                <li class=""><a href="#tab_11" data-toggle="tab" aria-expanded="true">里程保养计划</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="tab_10">
                                                    <div class="col-sm-12 form-group plan" id="plan3" data-wt-name="timePlan"
                                                         data-wt-url="${base}/a/plugins/plugins_demo_wtchecktable?type=1" > </div>
                                                </div>
                                                <div class="tab-pane" id="tab_11">
                                                    <div class="col-sm-12 form-group plan" id="plan4" data-wt-name="milePlan"
                                                         data-wt-url="${base}/a/plugins/plugins_demo_wtchecktable?type=1&modelId=1" data-wt-readonly="true"> </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <section class="form-horizontal">
                                <h4 class="page-header">下部控件</h4>
                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test3">基本信息3</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="test3" id="test3" value="" placeholder="请输入基本信息3">
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test4">基本信息4</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="test4" id="test4" value="" placeholder="请输入基本信息4">
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
            <button type="button" class="btn btn-sm btn-default wt-refreh-0">刷新上部列表</button>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
    <#include "./a/commons/bottom.ftl" />
    <#include "./a/plugins/plugins_prism.ftl" />
<script src="${base}/assets/plugins/wtchecktable/wtchecktable.js"></script>

<script type="text/javascript">
    $("document").ready(function () {

        //  初始化
        $(".item-plan").wtchecktable();
        $(".plan").wtchecktable();

        $(".wt-refreh-0").click(function () {
            $("#plan5").wtchecktable('refresh');
        });

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
