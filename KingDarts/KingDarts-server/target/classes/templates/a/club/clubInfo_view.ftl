<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/clubInfo/clubInfo_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="cno">cno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="cno" id="cno" value="${entity.cno}" placeholder="请输入cno" title="请输入cno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="cname">cname</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="cname" id="cname" value="${entity.cname}" placeholder="请输入cname" title="请输入cname"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="agno">agno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="agno" id="agno" value="${entity.agno}" placeholder="请输入agno" title="请输入agno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="ctype">ctype</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ctype" id="ctype" value="${entity.ctype}" placeholder="请输入ctype" title="请输入ctype"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="captain">captain</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="captain" id="captain" value="${entity.captain}" placeholder="请输入captain" title="请输入captain"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="mobile">mobile</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mobile" id="mobile" value="${entity.mobile}" placeholder="请输入mobile" title="请输入mobile"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="telno">telno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="telno" id="telno" value="${entity.telno}" placeholder="请输入telno" title="请输入telno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="email">email</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="email" id="email" value="${entity.email}" placeholder="请输入email" title="请输入email"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="qq">qq</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="qq" id="qq" value="${entity.qq}" placeholder="请输入qq" title="请输入qq"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="resum">resum</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="resum" id="resum" value="${entity.resum}" placeholder="请输入resum" title="请输入resum"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="incomes">incomes</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="incomes" id="incomes" value="${entity.incomes}" placeholder="请输入incomes" title="请输入incomes"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="expends">expends</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="expends" id="expends" value="${entity.expends}" placeholder="请输入expends" title="请输入expends"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="clubintr">clubintr</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="clubintr" id="clubintr" value="${entity.clubintr}" placeholder="请输入clubintr" title="请输入clubintr"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="c_password">c_password</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="c_password" id="c_password" value="${entity.c_password}" placeholder="请输入c_password" title="请输入c_password"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="activate_flag">activate_flag</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="activate_flag" id="activate_flag" value="${entity.activate_flag}" placeholder="请输入activate_flag" title="请输入activate_flag"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="commnets">commnets</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="commnets" id="commnets" value="${entity.commnets}" placeholder="请输入commnets" title="请输入commnets"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >add_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="add_time" id="add_time" placeholder="请输入add_time" class="form-control wt-datepicker" value="${entity.add_time}"   readonly >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="isvalid">isvalid</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="isvalid" id="isvalid" value="${entity.isvalid}" placeholder="请输入isvalid" title="请输入isvalid"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="logo">logo</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="logo" id="logo" value="${entity.logo}" placeholder="请输入logo" title="请输入logo"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="version">version</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="version" id="version" value="${entity.version}" placeholder="请输入version" title="请输入version"  readonly >
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="id" id="id" value="${entity.id}"/>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        WT.wt_set_navtitle('详情');   //  设置页面标题
        $(".wt-close").click(function () {  //  关闭
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
