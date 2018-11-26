<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./../commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
    <#include "./../commons/nav.ftl" />
<section class="content">
<#--内容开始-->
    <div class="wt-uploader" id="user_avator" data-wt-upload-name="user_avator" data-wt-upload-preview="ueditor/jsp/upload/file/20170423/1492945547439015517.png" ></div>
<#-- 内容结束-->
</section>
    <#include "./../commons/bottom.ftl" />
<link href="${base}/assets/plugins/wtUploader/css/wtUploader.css" rel="stylesheet">
<script src="${base}/assets/plugins/wtUploader/script/webuploader.js"></script>
<script src="${base}/assets/plugins/wtUploader/wtUploader.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('.wt-uploader').WTUploader({});
        $('#user_avator').WTUploader('fileNames', function(fileNames){
            console.log(fileNames);
        });
    });
</script>
</body>
</#escape>
</html>
