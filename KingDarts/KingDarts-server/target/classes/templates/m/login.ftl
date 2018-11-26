<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./m/commons/top.ftl" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="${base}/m/login"><b>WangtianSoft</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">后台管理系统</p>

        <form action="${base}/m/login" method="post">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" name="username" id="username"
                       value="" placeholder="手机号" required>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password" id="password"
                       value="" placeholder="密码" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                </div>
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登 录</button>
                </div>
            </div>
        </form>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<script src="${base}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${base}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${base}/assets/plugins/iCheck/icheck.min.js"></script>
<script>
</script>
</body>
</html>
