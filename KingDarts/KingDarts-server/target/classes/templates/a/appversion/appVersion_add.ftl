<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body" style="height: 400px;">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/appVersion/appVersion_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-4 control-label" for="app_version">版本号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="app_version" id="app_version" value="${version}" required>
                                    <input type="hidden" class="form-control" name="file_name" id="file_name" value="" required>
                                </div>
                            </div>
                    </div>
                    <div class="row">
                             <div class="col-sm-12 form-group">
                                <label class="col-sm-2 control-label" for="app_version">安装文件</label>
                                <div class="col-sm-10">
                                	<input type="file" name="file" id="appfile" required/>
                                    <!-- <div id="app_urldiv" data-wt-upload-name="app_url">
									</div> -->
                                </div>
                            </div>
                    </div>
                    
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_APPVERSION:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">新建</button></@wtAuth>
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
        var layindex = 0
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            
            getPhotoSize(document.getElementById("appfile"));
            
           /*  var app_url = $("input[name=app_url]").val();
            if(!app_url){
            	WT.wt_alert('请选择文件');
            	return false;
            } */
            WT.wt_confirm('是否保存?', function () {
                /* WT.wt_ajax_form('${base}/a/appversion/appVersion_add','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                }); */
                layindex = layer.load(1,{
                	  shade: [0.1,'#fff'], //0.1透明度的白色背景
                	  time:600*1000}); 
                ajax_form();
            });
        });
        
        function ajax_form(){
        	 var formData = new FormData($("#layerForm" )[0]);  
        	 $.ajax({ 
                 url: '${base}/a/appversion/appVersion_add' , 
                 type: 'POST', 
                 data: formData, 
                 //async: false, 
                 //cache: false, 
                 contentType: false, 
                 processData: false, 
                 success: function (data) { 
                	 layer.close(layindex);
                	 if (data.code == 0) { // 成功
                		 WT.wt_alert('保存成功',function(){
                             WT.wt_reload_jqtable(parent);
                             WT.wt_close();
                         });
                	 }else{
                		 layer.msg(data.msg);
                	 }
                 }, 
                 error: function (data) { 
                	 layer.close(layindex);
                	 layer.msg(data.msg);
                 } 
            });  
        }
        
      //判断照片大小
        function getPhotoSize(obj){
            photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
            if(photoExt!='.apk'){
            	WT.wt_alert("请上传后缀名为apk的文件!");
                return false;
            }
            var fileSize = 0;
            var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
            if (isIE && !obj.files) {          
                 var filePath = obj.value;            
                 var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
                 var file = fileSystem.GetFile (filePath);               
                 fileSize = file.Size;         
            }else {  
                 fileSize = obj.files[0].size;     
            } 
            //fileSize=Math.round(fileSize/1024*100)/100; //单位为KB
            if(fileSize>=100*1024*1024){
            	WT.wt_alert("文件大小需要小于100M，请重新上传!");
                return false;
            }
            return true;
        }
        
        
        /* $("#app_urldiv").WTBlankUploader({
        	accept: {
                extensions: 'apk,zip',
                mimeTypes: 'application/vnd.android.package-archive,application/zip'
            },
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: createUploadItem,
            maxItems: 1,
            isAllowAddFile: wtblankuploader_isAllowAddFile,
            onLoadFinishedHandler: function(wtuploader) {
            }
        });
        
        function createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file) {
        	if(file){
        		$("#file_name").val(file.name)
        	}
        	wtblankuploader_createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file)
        } */
    });
</script>
</body>
</#escape>
</html>
