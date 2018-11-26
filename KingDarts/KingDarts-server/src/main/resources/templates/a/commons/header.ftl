<header class="main-header">
    <!-- Logo -->
    <a href="${base}/a" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>KDS</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>凯帝狮</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu" style="float: left;">
            <ul class="nav navbar-nav">
                <#list Session['SESSION_PERM_MAP']['0'] as modulePerm>
                    <li class="dropdown tasks-menu">
                        <a href="#" class="dropdown-toggle btn-module" data-toggle="dropdown" data-id="${modulePerm.id}">
                            ${modulePerm.name}
                        </a>
                    </li>
                </#list>

            </ul>
        </div>
        <div class="navbar-custom-menu" style="float: right;">
            <ul class="nav navbar-nav">
                <li class="">
                    <a href="javascript:void(0);" onclick="javascript:logout();" style="float: right;" class=""><i class="fa fa fa-sign-out"></i> 退出 </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
<script>
    function logout(){
        if (confirm("是否登出?")) {
            top.location.href = '${base}/a/logout';
        }else{
            return false;
        }
    }
</script>