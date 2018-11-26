<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${base}/assets/dist/img/logo.jpg" class="img-circle" alt="Logo Image">
            </div>
            <div class="pull-left info">
                <p>${UserName}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <#--<form action="#" method="get" class="sidebar-form">-->
            <#--<div class="input-group">-->
                <#--<input type="text" name="q" class="form-control" placeholder="Search...">-->
              <#--<span class="input-group-btn">-->
                <#--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>-->
                <#--</button>-->
              <#--</span>-->
            <#--</div>-->
        <#--</form>-->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">

            <#list Session['SESSION_PERM_MAP']['0'] as modulePerm>
                <li class="header module_${modulePerm.id}" data-id="${modulePerm.id}" >${modulePerm.name}</li>
                <#list Session['SESSION_PERM_MAP']['${modulePerm.id}'] as parentPerm>
                    <li class="treeview module_${modulePerm.id}">
                        <a href="#">
                            <i class="fa <#if parentPerm.icon == ''>fa-circle-o<#else>${parentPerm.icon}</#if>"></i>
                            <span>${parentPerm.name}</span>
                            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                        </a>
                        <ul class="treeview-menu">
                            <#list Session['SESSION_PERM_MAP']['${parentPerm.id}'] as nodePerm>
                                <li><a href="javascript:void(0);" class="btn-node"
                                       data-module-name="${modulePerm.name}" data-parent-name="${parentPerm.name}"
                                       data-url="${base}/${nodePerm.url}" data-ourl="${nodePerm.url}" data-name="${nodePerm.name}" >
                                    <i class="fa <#if nodePerm.icon == ''>fa-circle-o<#else>${nodePerm.icon}</#if>"></i> ${nodePerm.name}</a></li>

                            </#list>
                        </ul>
                    </li>
                </#list>
            </#list>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>