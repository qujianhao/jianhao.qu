<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/advertInfo/advertInfo_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                    	<div class="col-sm-12 form-group">
                             <label class="col-sm-2 control-label" for="qrcode_url"><span style="color:red">*</span>二维码</label>
                             <div class="col-sm-10">
                                 <div id="qrcode_urldiv" data-wt-upload-name="qrcode_url" data-wt-upload-preview="${entity.qrcode_url}"> </div>
                             </div>
                         </div>
                         <div class="col-sm-12 form-group">
                             <label class="col-sm-2 control-label" for="top"><span style="color:red">*</span>距离顶部(%)</label>
                             <div class="col-sm-4">
                             	<input type="text" class="form-control " name="qr_top" id="qr_top" value="${entity.qr_top}" maxlength="2" min="0" max="100" required>
                             </div>
                             <label class="col-sm-2 control-label" for="left"><span style="color:red">*</span>距离左边(%)</label>
                             <div class="col-sm-4">
                             	<input type="text" class="form-control " name="qr_left" id="qr_left" value="${entity.qr_left}" maxlength="2"  min="0" max="100" required>
                             </div>
                         </div>
                         <div class="col-sm-12 form-group">
                             <label class="col-sm-2 control-label" for="user_id"><span style="color:red">*</span>资源文件</label>
                             <div class="col-sm-10">
                                 <div id="file_urldiv" data-wt-upload-name="file_url" data-wt-upload-preview="${entity.file_url}"></div>
                             </div>
                         </div>
                         <div class="col-sm-12 form-group">
                             <label class="col-sm-2 wt-revise-col-sm-2 control-label" for="des_title"><span style="color:red">*</span>描述</label>
                             <div class="col-sm-10">
                             <input type="hidden" class="form-control" name="file_name" id="file_name" value="${entity.file_name }" required>
                             <input type="hidden" class="form-control" name="file_size" id="file_size" value="${entity.file_size }" required>
                                 <textarea class="form-control" name="des_title" id="des_title" rows="6" title="请输入描述"  maxlength="255" required>${entity.des_title }</textarea>
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
             <@wtAuth hasPrem="_ADVERTINFO:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<#include "./a/commons/upload.ftl" />
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
            var file_url = $("input[name=file_url]").val();
            var qrcode_url = $("input[name=qrcode_url]").val();
            if(!file_url){
            	WT.wt_alert('请选择资源文件');
            	return false;
            }
            if(!qrcode_url){
            	WT.wt_alert('请选择二维码文件');
            	return false;
            }
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/advertInfo/advertInfo_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
        
        $("#qrcode_urldiv").WTBlankUploader({
        	accept: {
        		extensions: 'jpg,jpeg,png,gif,bmp',
        		mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
            },
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: createUploadItem,
            maxItems: 1,
            isAllowAddFile: wtblankuploader_isAllowAddFile,
            onLoadFinishedHandler: function(wtuploader) {
            }
        });
        $("#file_urldiv").WTBlankUploader({
        	accept: {
        		extensions: 'jpg,jpeg,png,gif,bmp,mp4,mpeg',
        		mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp,audio/mp4,video/mpeg'
            },
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: createUploadItem,
            maxItems: 1,
            isAllowAddFile: wtblankuploader_isAllowAddFile,
            onLoadFinishedHandler: function(wtuploader) {
                /* $("#user_documents").WTBlankUploader('preview', [{
                        name: '测试1.png',
                        url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
                    }, {
                        name: '测试1.png',
                        url: 'group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png'
                }]); */
            }
        });
        
        function createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file) {
        	if(file){
        		$("#file_name").val(file.name)
        		$("#file_size").val(file.size)
        	}
        	wtblankuploader_createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file)
        }
    });
</script>
</body>
</#escape>
</html>
