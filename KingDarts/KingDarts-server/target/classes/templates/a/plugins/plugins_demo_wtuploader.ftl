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
                        <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="true">图片选择器</a></li>
                        <li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="false">自定义选择器(文件选择器)</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">
                            <section class="form-horizontal">
                                <h4 class="page-header">如何引入</h4>
                                <p>1. 手动在需要使用的页面引入javascript文件 <pre><code class="language-javascript">${r'<#include "./a/commons/upload.ftl" />'}</code></pre></p>
                                <p>2. 增加上传组件 <pre><code class="language-javascript">${r'<div class="wt-uploader" id="user_avator" data-wt-upload-name="user_avator" data-wt-upload-preview="xxxx.jpg,xxxx.png" data-wt-upload-preview-names="测试1.jpg,测试2.jpg" />'}</code></pre></p>
                                <p>3. 使用jquery初始化 <pre><code class="language-javascript">${r'$("#user_files").WTUploader({});'}</code></pre></p>
                            </section>

                            <br>
                            <section class="form-horizontal">
                                <h4 class="page-header">dom方式配置说明</h4>
                                <p class="dd"><code>data-wt-upload-name</code>表示添加的图片传递后台的参数名称，<code>建议必填</code>,
                                    如果没有配置，则input name统一初始化为'file', 如<code>data-wt-upload-name="user_avator"</code>配置后，后台可以通过request.getParameters("user_avator")获取到上传后文件服务器的文件地址，通过request.getParameters("user_avator_names")获取到对应的文件原名
                                </p>
                                <p class="dd"><code>data-wt-upload-preview</code>表示默认加载的文件列表，文件之间使用逗号分隔，如上图配置后，初始化完成将会加载xxxx.jpg和xxxx.png</p>
                                <p class="dd"><code>data-wt-upload-preview-names</code>标示指定文件显示的名称，data-wt-upload-preview-names="xxxx.jpg,xxxx.png"字段分隔</p>
                                <p class="dd"><code>data-wt-readonly</code>可为空，默认为false，可以通过设置为data-wt-readonly="true"隐藏删除和上传按钮</p>
                            </section>

                            <br>
                            <section class="form-horizontal">
                                <h4 class="page-header">javascript方式配置说明</h4>
                                <p class="dt">默认配置</p>
                                <p class="dd"><pre><code class="language-javascript">$('.wt-uploader').WTUploader({});</code></pre></p>
                                <p class="dt">高级配置</p>
                                <p class="dd"><pre><code class="language-javascript">$('.wt-uploader').WTUploader({
    accept: {
        extensions: 'jpg,jpeg,png,gif,bmp',
        mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
    },
    minItems: 1,
    'maxItems': 20,
    'onFileDeleteHandler': function(imgSrc) {},
    'uploadFinishedHandler': function(imgSrc) {},
    'onLoadFinishedHandler': function() {},
});
</code></pre>
                                </p>
                                <p class="dt">配置项说明：
                                <p></p>
                                <p class="dd"> accept：控件可接收的文件类型，不设置标示接收图片类型</p>
                                <p class="dd"> minItems：控件可上传的文件数量下限，默认为1</p>
                                <p class="dd"> maxItems： 控件可上传的文件数量上限，默认为20，超过20个元素后，上传按钮隐藏</p>
                                <p class="dd"> onFileDeleteHandler: 文件删除事件回调</p>
                                <p class="dd"> uploadFinishedHandler: 文件上传完成时间回调</p>
                                <p class="dd"> onLoadFinishedHandler: 插件加载完成时间回调</p>
                            </section>

                        </div>



                        <div class="tab-pane " id="tab_2">

                            <section class="form-horizontal">
                                <h4 class="page-header">同 配置说明</h4>

                            <p class="dt">示例：</p>
                        <#--<div class="wt-uploader" id="user_files" data-wt-upload-preview="group1/M00/00/5C/eSqQlFnrRKOAawkEAA7vbyjKx0M144.jpg"></div>-->
                            <div class="wt-uploader" id="user_files" data-wt-upload-name="user_files"></div>
                            <div class="wt-uploader" id="user_other_files" data-wt-upload-name="user_other_files"></div>
                            </section>

                        </div>


                    <#--自定义上传组件配置（文档类型） 开始 -->
                        <div class="tab-pane " id="tab_3">

                            <section class="form-horizontal">
                                <h4 class="page-header">同 配置说明</h4>
                                <p>1. 使用dom初始化</p>
                                <p><pre><code class="language-html">${r'<div id="user_documents" data-wt-upload-name="user_documents"
        data-wt-upload-preview="group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png,group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png"
        data-wt-upload-preview-names="xxxx.jpg,xxxx.png">
</div>'}
</code></pre></p>
                                <p>2. 使用jquery初始化</p>
                                <p>
<pre><code class="language-javascript">
$("#user_documents").WTBlankUploader({
    accept: {},
    createUploadBtn: wtblankuploader_createUploadBtn,
    createUploadItem: wtblankuploader_createUploadItem,
    maxItems: 2,
    isAllowAddFile: wtblankuploader_isAllowAddFile,
    onLoadFinishedHandler: function(wtuploader) {
        $("#user_documents").WTBlankUploader('preview', [{
                name: '测试1.png',
                url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
            }, {
                name: '测试1.png',
                url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
        }]);
    }
});
</code></pre>
                                </p>
                            </section>




                            <br/>

                            <p class="dt">示例1：</p>

                            <div id="user_documents" data-wt-upload-name="user_documents"></div>

                            <br/>

                            <p class="dt">示例2：</p>

                            <div id="user_other_documents" data-wt-upload-name="user_other_documents"
                                 data-wt-upload-preview="group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png,group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png"
                                 data-wt-upload-preview-names="xxxx.jpg,xxxx.png" data-wt-readonly="true"></div>

                        </div>
                    <#--自定义上传组件配置（文档类型） 结束 -->


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
    <#include "./a/commons/upload.ftl" />
    <#include "./a/plugins/plugins_prism.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        //  上传图片
        $("#user_files").WTUploader({
            maxItems: 1,
            onLoadFinishedHandler: function () {
                $("#user_files").WTUploader('preview', ['group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'])
            }
        });
        $("#user_other_files").WTUploader({
            onLoadFinishedHandler: function () {
                $("#user_other_files").WTUploader('preview', ['group1/M00/00/5C/eSqQlFnrRKOAawkEAA7vbyjKx0M144.jpg'])
            }
        });


        //  上传文件
        $("#user_documents").WTBlankUploader({
            accept: wtblankuploader_accept,
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: wtblankuploader_createUploadItem,
            maxItems: 2,
            isAllowAddFile: wtblankuploader_isAllowAddFile,
            onLoadFinishedHandler: function (wtuploader) {
                $("#user_documents").WTBlankUploader('preview',
                        [{
                            name: '测试1.png',
                            url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
                        }, {
                            name: '测试2.png',
                            url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
                        }, {
                            name: '测试3.png',
                            url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
                        }
                        ]);
            },

        });
        $("#user_other_documents").WTBlankUploader({
            accept: wtblankuploader_accept,
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: wtblankuploader_createUploadItem
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
