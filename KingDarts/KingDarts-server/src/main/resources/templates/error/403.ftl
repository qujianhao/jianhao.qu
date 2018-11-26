<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./../a/commons/top.ftl" />
</head>
<body class="content-wrapper-body">
<#--内容区域开始-->
<section class="content-header">
    <h1>
        403 Error Page
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="error-page">
        <h2 class="headline text-red">403</h2>

        <div class="error-content">
            <h3><i class="fa fa-warning text-red"></i> 没有资源权限</h3>
            <br>
            <p>没有资源权限
                ，请返回首页...</p>
        </div>
    </div>
    <!-- /.error-page -->
<#--内容区域结束-->
<#include "./../a/commons/bottom.ftl" />
<script type="text/javascript">

</script>
</body>
</html>
