<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/advertInfo/advertInfo_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-12 form-group">
                                <label class="col-sm-1 wt-revise-col-sm-1 control-label" for="file_url">file_url</label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" name="file_url" id="file_url" title="请输入file_url"  readonly >${entity.file_url}</textarea>
                                </div>
                            </div>
                            <div class="col-sm-12 form-group">
                                <label class="col-sm-1 wt-revise-col-sm-1 control-label" for="file_name">file_name</label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" name="file_name" id="file_name" title="请输入file_name"  readonly >${entity.file_name}</textarea>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="file_size">file_size</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="file_size" id="file_size" value="${entity.file_size}" placeholder="请输入file_size" title="请输入file_size"  readonly >
                                </div>
                            </div>
                            <div class="col-sm-12 form-group">
                                <label class="col-sm-1 wt-revise-col-sm-1 control-label" for="describe">describe</label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" name="describe" id="describe" title="请输入describe"  readonly >${entity.describe}</textarea>
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
                                <label class="col-sm-3 control-label" for="user_id">user_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="user_id" id="user_id" value="${entity.user_id}" placeholder="请输入user_id" title="请输入user_id"  readonly >
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
