<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/agentInfo/agentInfo_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="agno">agno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="agno" id="agno" value="${entity.agno}" placeholder="请输入agno" title="请输入agno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="pagno">pagno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="pagno" id="pagno" value="${entity.pagno}" placeholder="请输入pagno" title="请输入pagno"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="agname">agname</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="agname" id="agname" value="${entity.agname}" placeholder="请输入agname" title="请输入agname"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="captain">captain</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="captain" id="captain" value="${entity.captain}" placeholder="请输入captain" title="请输入captain"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="card_id">card_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_id" id="card_id" value="${entity.card_id}" placeholder="请输入card_id" title="请输入card_id"  readonly >
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
                                <label class="col-sm-3 control-label" for="ag_addr">ag_addr</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ag_addr" id="ag_addr" value="${entity.ag_addr}" placeholder="请输入ag_addr" title="请输入ag_addr"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="lnglat">lnglat</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="lnglat" id="lnglat" value="${entity.lnglat}" placeholder="请输入lnglat" title="请输入lnglat"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="latitude">latitude</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="latitude" id="latitude" value="${entity.latitude}" placeholder="请输入latitude" title="请输入latitude"  readonly >
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
                                <label class="col-sm-3 control-label" for="ag_password">ag_password</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ag_password" id="ag_password" value="${entity.ag_password}" placeholder="请输入ag_password" title="请输入ag_password"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="ag_level">ag_level</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ag_level" id="ag_level" value="${entity.ag_level}" placeholder="请输入ag_level" title="请输入ag_level"  readonly >
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
                                <label class="col-sm-3 control-label" for="racepht">racepht</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="racepht" id="racepht" value="${entity.racepht}" placeholder="请输入racepht" title="请输入racepht"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="ag_type">ag_type</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ag_type" id="ag_type" value="${entity.ag_type}" placeholder="请输入ag_type" title="请输入ag_type"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="country">country</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="country" id="country" value="${entity.country}" placeholder="请输入country" title="请输入country"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="province">province</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="province" id="province" value="${entity.province}" placeholder="请输入province" title="请输入province"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="city">city</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="city" id="city" value="${entity.city}" placeholder="请输入city" title="请输入city"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="areas">areas</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="areas" id="areas" value="${entity.areas}" placeholder="请输入areas" title="请输入areas"  readonly >
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
