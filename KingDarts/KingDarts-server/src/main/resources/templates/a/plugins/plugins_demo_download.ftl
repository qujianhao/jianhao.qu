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
@RequestMapping("/xxx_download")
public ResponseEntity&lt;org.springframework.core.io.Resource&gt; xxx_download() {
    String fileName = "测试文件.png";
    String fileUri = "group1/M00/00/5C/eSqQlFnrSryAHekJAACZhjgQjtc966.png";
    Long fileLength = null; // fileService.findFileLengthByFileUrl(fileUri);
    return renderFile(fileName, fileUri, fileLength);
}
</code>
</pre>



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

    });
</script>
</body>
</#escape>
</html>
