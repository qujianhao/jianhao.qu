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
    <script id="container" name="content" type="text/plain" ></script>
    <script id="container1" name="content" type="text/plain" ></script>
    <script id="container2" name="content" type="text/plain" ></script>
    <script id="container3" name="content" type="text/plain" ></script>
<#-- 内容结束-->
</section>
    <#include "./../commons/bottom.ftl" />
<script src="${base}/assets/plugins/ueditor/ueditor.config.js"></script>
<script src="${base}/assets/plugins/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    var ue = UE.getEditor('container',{});
});
</script>
</body>
</#escape>
</html>
