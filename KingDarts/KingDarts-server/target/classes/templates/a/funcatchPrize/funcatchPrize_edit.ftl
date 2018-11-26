<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/funcatchPrize/funcatchPrize_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">抓娃娃配置</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="position_num"><span style="color:red">*</span>位置编号</label>
                                <div class="col-sm-9">
                                   ${entity.position_num}
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="prize_name"><span style="color:red">*</span>名称</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="prize_name" id="prize_name" 
                                    value="${entity.prize_name}" placeholder="请输入名称" title="请输入名称" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="icon_file"><span style="color:red">*</span>icon文件上传</label>
                                <div class="col-sm-9">
                                    <div id="icon_file" data-wt-upload-name="icon_url" 
                                    data-wt-upload-preview="${entity.icon_url}"  data-wt-upload-preview-names="${entity.icon_name}">
									</div>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="is_physical"><span style="color:red">*</span>类型</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="is_physical" id="is_physical" data-wt-auto="${entity.is_physical}" required >
				                        <option value="">请选择</option>
				                        <option value="N">虚拟奖品</option>
				                        <option value="Y">实物</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="worth"><span style="color:red">*</span>奖励价值（虚拟点数或实物价格）</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="worth" id="worth" 
                                    value="${entity.worth}" placeholder="请输入奖励价值" title="请输入奖励价值" required>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="win_rate"><span style="color:red">*</span>中奖率</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="win_rate" id="win_rate" 
                                    value="${entity.win_rate}" placeholder="请输入中奖率" title="请输入中奖率" required>
                                </div>
                            </div>
                           <!-- <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="stock">实物库存数量</label>
                                <div class="col-sm-9">
                                	<input type="text" class="form-control" name="stock" id="stock" 
                                    value="${entity.stock}" placeholder="请输入库存数量" title=请输入库存数量" >
                                </div>
                            </div>-->
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" id="prize_id" name="prize_id" value="${entity.prize_id}"/>
             <@wtAuth hasPrem="_EQUINFO:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<#include "./a/commons/upload.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
		$("#icon_file").WTBlankUploader({
		    accept: {extensions: 'jpg,jpeg,png,gif,bmp',
			         mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
			        },
		    createUploadBtn: wtblankuploader_createUploadBtn,
		    createUploadItem: wtblankuploader_createUploadItem,
		    isAllowAddFile: wtblankuploader_isAllowAddFile,
		    maxItems: 1
		});
    
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            if(isNaN($("#win_rate").val())){
               WT.wt_alert("中奖率必须为数字");
               return false;
            }
            if($("#win_rate").val()>=1){
                WT.wt_alert("中奖率必须小于1");
                return false;
            }
            if($("#win_rate").val()<0){
                WT.wt_alert("中奖率必须大于等于0");
                return false;
            }
            if($('[name="icon_url"]').val()==undefined ){
		        WT.wt_alert("请上传icon文件");
                return false;
	        }
	        var r = /^[0-9]*[1-9][0-9]*$/　　//正整数 
            if(!r.test($("#worth").val())){
                WT.wt_alert("奖励价值填写正整数");
                return false;
            }
            WT.wt_confirm('是否保存?', function () {
               var params = {prize_id:$("#prize_id").val(),win_rate: $("#win_rate").val()};
	           WT.wt_ajax_jsondata('${base}/a/funcatchPrize/funcatchPrize_valid',params, function (data) {
	                if(data.code==0){
	                  WT.wt_ajax_form('${base}/a/funcatchPrize/funcatchPrize_edit','layerForm',function(data){
	                    WT.wt_alert('保存成功',function(){
	                        WT.wt_reload_jqtable(parent);
	                        WT.wt_close();
	                    });
	                  }); 
	                }else{
	                   WT.wt_alert('总概率加起来不等于1，请重新设置');
	                }
               });
            
                
            });
        });
    });
</script>
</body>
</#escape>
</html>
