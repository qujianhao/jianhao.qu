<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<body class="hold-transition login-page" style="background-image: url('${base}/assets/dist/img/bg_2.jpg');background-repeat: no-repeat;background-size: cover;">
<script type="text/javascript">
    if( top.location != self.location){
        top.location = self.location;
    }
</script>
<div style="height: 10%;"></div>
<div class="login-box">
    <div class="login-logo">
        <a href="${base}/a/login" style="color: #fff"><b>凯帝狮飞镖机系统</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">凯帝狮后台管理系统</p>

        <form action="${base}/a/login" method="post">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" name="username" id="username"
                       value="" placeholder="用户名" required>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" name="password" id="password"
                       value="" placeholder="密码" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback login-validate-code">
                <input type="text" class="form-control login-validate-code-input" name="validate_code" id="validate_code"
                       value="" placeholder="验证码" required maxlength="4">
                <img width="100" height="34" src="${base}/a/captcha"/>
                <div style="height: 34px;">&nbsp;</div>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <span style="color: red;">${Session.LOGIN_MSG!""}</span>
                </div>
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-info btn-block btn-flat">登 录</button>
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
