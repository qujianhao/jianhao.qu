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
                        <div class="tab-pane" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何引入</h4>

                                <div>
                                    <dd>1.引入<code>&lt;script src=&quot;${base}/assets/plugins/wtchecktable/wtchecktable.js&quot;&gt;&lt;/script&gt;</code>
                                    </dd>
                                    <dd>2.加入标签 <code>&lt;div class=&quot;col-sm-12 form-group plan-1&quot; id=&quot;plan1&quot;
                                        data-wt-name=&quot;mileage&quot; data-wt-url=&quot;${base}/a/vehicle/vehicleMaintenancePlan/vehicleMaintenancePlan_plan_table?type=1&quot;&gt;
                                        &lt;/div&gt;</code></dd>
                                    <dd>3.使用<code>$(".plan-1").wtchecktable();</code></dd>
                                </div>
                                <br/>
                                <h4 class="page-header">参数配置</h4>

                                <div>
                                    <dt>页面元素配置：</dt>
                                    <dd><code>data-wt-name</code>可为空，表示当前表格提交到后台的name名称，默认为wt_checktable，提交到后台的数据分为两部分，
                                        如默认配置下：
                                        一部分为定义的name名，后台可以通过request.getParameterValues("wt_checktable")获取列的key值 String[]
                                        keys；
                                        一部分为实际的数据内容，后台可以通过request.getParameterValues("wt_checktable_" +
                                        key[n])来获取String[]；
                                    </dd>
                                    <dd><code>data-wt-readonly</code>：可为空，表示是否只读，默认为可编辑状态，配置为 <code>data-wt-readonly="true"</code>表示只读
                                    </dd>
                                </div>
                            </section>
                        </div>

                        <div class="tab-pane active" id="tab_2">
                            <section class="form-horizontal">
                                <h4 class="page-header">上部控件</h4>

                                <div class="row ">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test1">机构选择</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control org-picker" name="test1" id="test1"
                                                   value="" placeholder="请选择机构"
                                                   data-wt-url="${base}/a/common/fcOrganization/organization_picker"
                                                   readonly>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test2">机构选择</label>

                                        <div class="col-sm-9">
                                            <div class="form-control"></div>
                                            <input type="hidden" class="form-control" name="test2" id="test2" value=""
                                                   placeholder="请选择机构" readonly>
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
<script src="${base}/assets/plugins/wtorgpicker/wtorgpicker.js"></script>

<script type="text/javascript">
    $("document").ready(function () {

        //  初始化
        $(".org-picker").wtorgpicker();

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
