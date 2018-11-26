<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" >
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-5 form-group">
                                <label class="col-sm-4 control-label" for="medal_name">勋章名称</label>
                                <div class="col-sm-8">
                                	<label class="control-label" >${entity.medal_name}</label>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="medal_desc">勋章描述</label>
                                <div class="col-sm-8">
                                	<label class="control-label" >${entity.medal_desc}</label>
                                </div>
                            </div>
                            <div class="col-sm-5 form-group">
                                <label class="col-sm-4 control-label" for="medal_url">勋章图标</label>
                                <div class="col-sm-8">
                                     <div id="icon_file" data-wt-upload-name="medal_url"  data-wt-upload-preview="${entity.medal_url}"  
                                     data-wt-upload-preview-names="勋章图标"></div>
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
             <@wtAuth hasPrem="MEDALWINNER_MEDALWINNER:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<#include "./a/commons/upload.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
    
        $("#icon_file").WTBlankUploader({
		    accept: {},
		    createUploadBtn: wtblankuploader_createUploadBtn,
		    createUploadItem: wtblankuploader_createUploadItem,
		    maxItems: 1,
		    isAllowAddFile: wtblankuploader_isAllowAddFile
		});
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
	                WT.wt_ajax_form('${base}/a/medal/medal_edit','layerForm',function(data){
	                    WT.wt_alert('保存成功',function(){
	                        WT.wt_reload_jqtable(parent);
	                        WT.wt_close();
	                    });
	                });
                }else{
	                WT.wt_ajax_form('${base}/a/medal/medal_add','layerForm',function(data){
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
