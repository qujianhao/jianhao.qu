<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/bossEntity/bossEntity_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >生成日期</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="application_time" id="application_time"  class="form-control wt-datepicker"
                                         value="${entity.application_time}" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="evolume">剩余血量</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="evolume" id="evolume" value="${entity.evolume}" title="请输入剩余血量" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="kill_status">击杀状态</label>
                                <div class="col-sm-9">
                                    <select class="form-control" disabled="disabled" name="kill_status" id="kill_status" data-wt-auto="${entity.kill_status}"  >
				                        <option value="">请选择</option>
				                        <option value="0">未击杀</option>
				                        <option value="1">已击杀</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >击杀时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="kill_time" id="kill_time"  class="form-control wt-datepicker" 
                                        value="<#if (entity.kill_time)??>${entity.kill_time?string('yyyy-mm-dd HH:mm:ss')}</#if>" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <h4 class="page-header">贡献值排名</h4>
                    <#if rankPage??>
                    <div class="row">
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >排名</label>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="evolume">用户昵称</label>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="kill_status">贡献值</label>
                            </div>
                    </div>
                    <#list rankPage as rankList>
                    <div class="row">
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label">${rankList.rank}</label>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label">${rankList.nickname}</label>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label">${rankList.score}</label>
                            </div>
                    </div>
                    </#list>
                    </#if>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
    });
</script>
</body>
</#escape>
</html>
