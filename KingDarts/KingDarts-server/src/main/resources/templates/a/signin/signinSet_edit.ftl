<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/KingDarts/signinSet/signinSet_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="signin_day">累计天数</label>
                                <div class="col-sm-8">
                                	<label class="control-label" >${entity.signin_day}</label>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="name">名称</label>
                                <div class="col-sm-8">
                                	<label class="control-label" >${entity.name}</label>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-5 control-label" for="point">签到获取点数</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="point" id="point" value="${entity.point}" placeholder="请输入签到获取点数" title="请输入签到获取点数" >
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
	                WT.wt_ajax_form('${base}/a/signinSet/signinSet_edit','layerForm',function(data){
	                    WT.wt_alert('保存成功',function(){
	                        WT.wt_reload_jqtable(parent);
	                        WT.wt_close();
	                    });
	                });
                }else{
                    WT.wt_ajax_form('${base}/a/signinSet/signinSet_add','layerForm',function(data){
	                    WT.wt_alert('保存成功',function(){
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
