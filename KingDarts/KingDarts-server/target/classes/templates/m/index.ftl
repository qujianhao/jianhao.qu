<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台管理系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${base}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/assets/plugins/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${base}/assets/plugins/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${base}/assets/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${base}/assets/dist/css/skins/_all-skins.min.css">
    <!--[if lt IE 9]>
    <script src="${base}/assets/plugins/html5shiv/html5shiv.min.js"></script>
    <script src="${base}/assets/plugins/respond/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper" style="height: 100%;overflow-y: hidden;">
<#include "./commons/header.ftl" />
<#include "./commons/sidebar.ftl" />
    <div class="content-wrapper" style="height: calc(100% - 100px);overflow: hidden;">
        <iframe id="mainFrame" name="mainFrame" src="${base}/main/main" width="100%" height="100%" frameborder="0"></iframe>
    </div>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2017-2018 WangtianSoft.</strong> All rights
        reserved.
    </footer>
</div>
<script src="${base}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${base}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${base}/assets/plugins/fastclick/fastclick.js"></script>
<script src="${base}/assets/dist/js/app.js"></script>
</body>
</html>
