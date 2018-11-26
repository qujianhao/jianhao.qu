<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/equInfo/equInfo_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equno"><span style="color:red">*</span>设备编号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equno" id="equno" value="${entity.equno}" placeholder="请输入设备编号" title="请输入设备编号" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="models"><span style="color:red">*</span>设备型号</label>
                                <div class="col-sm-8">
                                	<select class="form-control" name="models" id="models" required>
				                        <option value="K2">K2</option>
				                        <option value="K3">K3</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equname"><span style="color:red">*</span>设备名称</label>
                                <div class="col-sm-8">
                                	<select class="form-control" name="equname" id="equname" required disabled="disabled">
				                        <option value="K2联网飞镖机">K2联网飞镖机</option>
				                        <option value="K3联网飞镖机">K3联网飞镖机</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="prod_batch">生产批次</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="prod_batch" id="prod_batch" value="${entity.prod_batch}" placeholder="请输入生产批次" title="请输入生产批次" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equ_version"><span style="color:red">*</span>使用版本</label>
                                <div class="col-sm-8">
                                	<select class="form-control" name="equ_version" id="equ_version" required>
				                        <option value="V1.0.0">V1.0.0</option>
				                    </select>
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_EQUINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">新建</button></@wtAuth>
            <#-- <button type="button" class="btn btn-sm btn-default wt-close">关闭</button> -->
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
        
        $("#models").change(function(){
        	if($(this).val() == 'K2'){
        		$("#equname").val("K2联网飞镖机");
        	}else{
        		$("#equname").val("K3联网飞镖机");
        	}
        })
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
            	$("#equname").prop("disabled",false);
                WT.wt_ajax_form('${base}/a/equInfo/equInfo_add','layerForm',function(data){
                    WT.wt_alert('保存成功',function(index){
                    	$("#layerForm")[0].reset();  
                    	layer.close(index);
                    });
                });
            });
        });
    });
</script>
</body>
</#escape>
</html>
