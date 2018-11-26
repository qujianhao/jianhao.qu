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
                        <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="false">导出excel</a></li>
                        <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="false">导出word</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何使用</h4>

                                <div>
                                    前台使用js方法:
                                </div>
<pre>
<code class="language-javascript">
    WT.wt_download('xxx/xxx_download', {id: xxx});
</code>
</pre>
                                <div>
                                    后台对应方法:
                                </div>
<pre>
<code class="language-java">
@RequestMapping("/xxx_download_excel")
public void xxx_download_excel() {
    List<Map> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
    Map map = new HashMap();
        map.put("username", "测试人" + (i + 1));
        map.put("phone", "186" + RandomUtils.nextInt(1000, 9999) + RandomUtils.nextInt(1000, 9999));
        map.put("email", "test" + (i + 1) + "@test.com");
        map.put("balance", RandomUtils.nextInt(0, 1000));
        map.put("follow_time", new Date().toString());
        list.add(map);
    }
    String[] header = {"用户名称", "电话", "邮箱", "余额", "关注时间"};
    String[] columns = {"username", "phone", "email", "balance", "follow_time"};
    ExcelRenderUtil renderUtil = ExcelRenderUtil.me(request, response, list).fileName("测试列表.xls").headers(header).sheetName("信息表").columns(columns);
    renderUtil.render();
}
</code>
</pre>

                            </section>

                            <br>
                            <section class="form-horizontal">
                                <h4 class="page-header">示例</h4>
                                <div>
                                    <button type="button" class="btn btn-sm btn-info wt-export-excel">导出excel</button>
                                </div>
                            </section>
                        </div>
                        <div class="tab-pane " id="tab_2">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何使用</h4>
                                <div>
                                    开发中...
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
    <#include "./a/plugins/plugins_prism.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });

        //  导出excel
        $(".wt-export-excel").click(function () {
            WT.wt_download('${base}/a/plugins/plugins_demo_download_excel', {id: 1});
        });

    });
</script>
</body>
</#escape>
</html>
