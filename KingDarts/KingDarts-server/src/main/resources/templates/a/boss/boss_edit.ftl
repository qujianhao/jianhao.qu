<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">boss信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" ><span style="color:red">*</span>boss编号</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="bno" id="bno" placeholder="请输入boss编号" class="form-control" value="${entity.bno}" required >
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" ><span style="color:red">*</span>boss名称</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="bname" id="bname" placeholder="请输入boss名称" class="form-control" value="${entity.bname}" required >
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="evolume"><span style="color:red">*</span>血量</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="bvolume" id="bvolume" value="${entity.bvolume}" placeholder="请输入血量" title="请输入血量" required  >
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
            <button type="button" class="btn btn-sm btn-info wt-save">保存</button>
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
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                if($("#id").val()!=''){
                  WT.wt_ajax_form('${base}/a/boss/boss_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                  });
                }else{
                  WT.wt_ajax_form('${base}/a/boss/boss_add','layerForm',function(data){
                    WT.wt_alert('新增成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                  });
                }
            });
        });
    });
</script>
</body>
</#escape>
</html>
