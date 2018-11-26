<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/clubInfo/clubInfo_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">俱乐部信息</h4>
                    <div class="row">
                    	<input type="hidden" name="id" id="id" value="${map.clubInfo.id}">
                    	<input type="hidden" name="cno" id="cno" value="${map.clubInfo.cno}">
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="cname"><span style="color:red">*</span>俱乐部名称：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="cname" id="cname" value="${map.clubInfo.cname}" placeholder="请输入俱乐部名称" required disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label"><span style="color:red">*</span>分成比例</label>
                            <div class="col-sm-9">
                                 <div class="col-sm-3 control-label" style="font-size:15px;">公司</div>
                                 <div class="col-sm-3"><input type="text" class="form-control" name="companyscale" id="companyscale" value="${map.clubInfo.companyscale}" required disabled></div>
                                 <div class="col-sm-3 control-label" style="font-size:15px;">代理/直营</div>
                                 <div class="col-sm-3"><input type="text" class="form-control" name="clubscale" id="clubscale" value="${map.clubInfo.clubscale}" readonly="readonly"></div>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="app_url"><span style="color:red">*</span>LOGO：</label>
                            <div class="col-sm-9">
                                <div id="logo_urldiv" data-wt-upload-name="logo" data-wt-upload-preview="${map.clubInfo.logo}" disabled>
								</div>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="captain"><span style="color:red">*</span>负责人：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="captain" id="captain" value="${map.clubInfo.captain}" placeholder="请输入负责人" title="请输入负责人" required disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="cardid">身份证：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="cardid" id="cardid" value="${map.clubInfo.cardid}" placeholder="请输入身份证" title="请输入身份证" disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="c_password"><span style="color:red">*</span>密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" name="c_password" id="c_password"  placeholder="无需修改密码时可不填"  disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="c_password"><span style="color:red">*</span>确认密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" name="re_password" id="re_password" placeholder="无需修改密码时可不填"  disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="mobile">手机号：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="mobile" id="mobile" value="${map.clubInfo.mobile}" placeholder="请输入手机号" disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="telno">固定电话：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="telno" id="telno" value="${map.clubInfo.telno}" placeholder="请输入固定电话" title="请输入固定电话"  disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="email">Email：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="email" id="email" value="${map.clubInfo.email}" placeholder="请输入email" title="请输入email"  disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="qq">QQ：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="qq" id="qq" value="${map.clubInfo.qq}" placeholder="请输入QQ" title="请输入QQ" disabled>
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="describe">描述信息：</label>
                            <div class="col-sm-9" style="float:left">
                            	<input type="text" class="form-control" name="describe_message" id="describe_message" value="${map.clubInfo.describe_message}" placeholder="请输入描述信息" title="请输入描述信息" >
                            </div>
                        </div>
                        <div class="col-sm-6 form-group">
                            <label class="col-sm-3 control-label" for="describe">所属代理商：</label>
                            <div class="col-sm-9" >
                            	<select class="form-control" name="agno" id="agno" data-wt-auto="${map.clubInfo.agno}" >
			                        <option value="">直营</option>
			                        <#list aglist as item>
                                  		<option value="${item.agno }">${item.agname }</option>
                                    </#list>
			                    </select>
                            </div>
                        </div>
                        <#if map.type=undefined||map.type=1>
	                        <div class="col-sm-12 form-group" >
	                        	<div class="col-sm-6 form-group">
	                        	<label class="col-sm-3 control-label"><span style="color:red">*</span>营业场所</label>
	                            <div class="col-sm-9">
	                                <input value="添加" id="addplace" class="btn btn-white btn-sm" type="button" required/>
	                            </div>
	                        	</div>
	                        </div>
                        </#if>
                    </div>
                </section>
                <section class="form-horizontal">
                    <div class="block-content">
                    	<table class="table table-bordered detailtable" >
                    		<tr>
                    			<td width="10%">序号</td>
                                <td>营业场所</td>
                                <td>操作</td>
                            </tr>
                        	<#list map.clubPlaceList as item>
                        		<tr data-trid="${item.id}">
                        			<td>${item_index + 1}</td>
                        			<td>${item.place_name}</td>
									<td>
										<#if map.type==0>
										<button type="button" class="btn btn-sm btn-primary wt-view">查看</button>
										</#if>
										<#if map.type==1>
										<button type="button" class="btn btn-sm btn-primary wt-edit" >修改</button>
										<button type="button" class="btn btn-sm btn-primary wt-del" >删除</button>
										</#if>
										<input type="hidden" name="clubPlaceResultList['+(item_index)+'].tempid" data-dname="tempid" value="${item.id}" required>
										<input type="hidden" name="clubPlaceResultList['+(item_index)+'].id" data-dname="id" value="${item.id}" required>
										<input type="hidden" name="clubPlaceResultList['+(item_index)+'].place_name" data-dname="place_name" value="${item.place_name}" required>
					                    <input type="hidden" name="clubPlaceResultList['+(item_index)+'].consdes" data-dname="consdes" value="${item.consdes}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].businesshour" data-dname="businesshour" value="${item.businesshour}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].country" data-dname="country" value="${item.country}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].province" data-dname="province" value="${item.province}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].city" data-dname="city" value="${item.city}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].areas" data-dname="areas" value="${item.areas}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].address" data-dname="address" value="${item.address}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].pcaptain" data-dname="pcaptain" value="${item.pcaptain}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].pmobile" data-dname="pmobile" value="${item.pmobile}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].ptelno" data-dname="ptelno" value="${item.ptelno}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].details" data-dname="details" value="${item.details}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].longitude" data-dname="longitude" value="${item.longitude}" required>
							            <input type="hidden" name="clubPlaceResultList['+(item_index)+'].latitude" data-dname="latitude" value="${item.latitude}" required>
									</td>
                        		</tr>
                        	</#list>
                            <tr style="display: none;">
                            	<td></td>
                            	<td></td>
                                <td>
                                	<button type="button" class="btn btn-sm btn-primary wt-view">查看</button>
									<button type="button" class="btn btn-sm btn-primary wt-edit">修改</button>
									<button type="button" class="btn btn-sm btn-primary wt-del" >删除</button>
									<input type="hidden" data-dname="tempid" value="" required>
									<input type="hidden" data-dname="id" value="" required>
									<input type="hidden" data-dname="place_name" value="" required>
				                    <input type="hidden" data-dname="consdes" value="" required>
						            <input type="hidden" data-dname="businesshour" value="" required>
						            <input type="hidden" data-dname="country" value="" required>
						            <input type="hidden" data-dname="province" value="" required>
						            <input type="hidden" data-dname="city" value="" required>
						            <input type="hidden" data-dname="areas" value="" required>
						            <input type="hidden" data-dname="address" value="" required>
						            <input type="hidden" data-dname="pcaptain" value="" required>
						            <input type="hidden" data-dname="pmobile" value="" required>
						            <input type="hidden" data-dname="ptelno" value="" required>
						            <input type="hidden" data-dname="details" value="" required>
						            <input type="hidden" data-dname="longitude" value="" required>
						            <input type="hidden" data-dname="latitude" value="" required>
                                </td>
                            </tr>
                    	</table>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
        	<#if map.type=undefined||map.type=1>
             	<@wtAuth hasPrem="_CLUBINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            </#if>
            <#if map.type=0||map.type=1>
            	<button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
            </#if>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<#include "./a/commons/upload.ftl" />
<script src="${base}/assets/plugins/ueditor/ueditor.config.js"></script>
<script src="${base}/assets/plugins/ueditor/ueditor.all.min.js"></script>
<script src="${base}/assets/bootstrap/dist/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">

	var ue = UE.getEditor('describe_message',{initialFrameWidth:"100%"});
	
	var $tr0 = $(".detailtable > > tr:hidden").clone();
	$(".detailtable tr:hidden").remove();
	indexTable();
	
	function indexTable(){
		$(".detailtable tr").each(function(i){
    		if(i>0){
    			$(this).find("> td:first").text(i);
    			$.each($(this).find('input,select'), function(ide, v){
    				$(this).attr('name', 'clubPlaceResultList['+(i-1)+'].'+$(this).data('dname'));
    			});
    		}
    	})
	}
	function addTr(_obj){
		if(_obj&&_obj.tempid){
			$(".detailtable tr[data-trid="+_obj.tempid+"]").remove();
		}
		var tempid = _obj.tempid?_obj.tempid:"temp"+randomNum(-10000,-100000);
		var $tr = $tr0.clone(true).show();
		$tr.attr("data-trid",tempid);
		$tr.find("td:eq(1)").text(_obj.place_name);
		
		$tr.find("input[data-dname=tempid]").val(tempid); 
		$tr.find("input[data-dname=id]").val(_obj.id); 
		$tr.find("input[data-dname=place_name]").val(_obj.place_name); 
		$tr.find("input[data-dname=consdes]").val(_obj.consdes); 
		$tr.find("input[data-dname=businesshour]").val(_obj.businesshour); 
		$tr.find("input[data-dname=country]").val(_obj.country); 
		$tr.find("input[data-dname=province]").val(_obj.province); 
		$tr.find("input[data-dname=city]").val(_obj.city); 
		$tr.find("input[data-dname=areas]").val(_obj.areas); 
		$tr.find("input[data-dname=address]").val(_obj.address); 
		$tr.find("input[data-dname=pcaptain]").val(_obj.pcaptain); 
		$tr.find("input[data-dname=pmobile]").val(_obj.pmobile); 
		$tr.find("input[data-dname=ptelno]").val(_obj.ptelno); 
		$tr.find("input[data-dname=details]").val(_obj.details); 
		$tr.find("input[data-dname=longitude]").val(_obj.longitude); 
		$tr.find("input[data-dname=latitude]").val(_obj.latitude); 
		
		$('.detailtable').append($tr);
		
		indexTable();
	}
	
    $("document").ready(function () {
    	
        //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        
        if('${map.type}'==1){
        	$('#cname').attr("disabled",false);
        	$('#companyscale').attr("disabled",false);
        	$('#logo').attr("disabled",false);
        	$('#acac').attr("disabled",false);
        	$('#captain').attr("disabled",false);
        	$('#cardid').attr("disabled",false);
        	$('#c_password').attr("disabled",false);
        	$('#re_password').attr("disabled",false);
        	$('#mobile').attr("disabled",false);
        	$('#telno').attr("disabled",false);
        	$('#email').attr("disabled",false);
        	$('#qq').attr("disabled",false);
        	$('#describe').attr("disabled",false);
        }else{
        	$('#cname').attr("disabled",true);
        	$('#companyscale').attr("disabled",true);
        	$('#logo').attr("disabled",true);
        	$('#acac').attr("disabled",true);
        	$('#captain').attr("disabled",true);
        	$('#cardid').attr("disabled",true);
        	$('#c_password').attr("disabled",true);
        	$('#re_password').attr("disabled",true);
        	$('#mobile').attr("disabled",true);
        	$('#telno').attr("disabled",true);
        	$('#email').attr("disabled",true);
        	$('#qq').attr("disabled",true);
        	$('#describe').attr("disabled",true);
        }
        
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            var x = $("#companyscale").val();
        	if (isNaN(x)){ 
    			WT.wt_alert("分成比例，请输入数字");
    			return false;
    		}
    		if(x<0 || x>10){
    			WT.wt_alert("比例需要是0-10的数字");
    			return false;
    		}
            if($('[name="logo"]').val()==undefined
            &&$('[data-wt-upload-name="logo"]').attr("data-wt-upload-preview")==""){
            	WT.wt_alert("请上传LOGO");
            	return false;
            }
            if($(".detailtable tr").length<2){
            	WT.wt_alert("请添加营业场所");
            	return false;
            }
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/clubInfo/clubInfo_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(index){
                    	WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
        
        $('#addplace').click(function () {
        	if($(".detailtable tr").length>=2){
            	WT.wt_alert("只能添加一个营业场所");
            	return false;
            }
        	WT.wt_open_fullscreen('${base}/a/clubplace/clubPlace_Form',{});
		})
		
		$(".detailtable").on("click",".wt-del",function(){
			$(this).parent().parent().remove();
		})
		$(".detailtable").on("click",".wt-edit",function(){
			var data = {}
			var $td = $(this).parent();
			$td.find("input").each(function( index ) {
				 data[$(this).attr("data-dname")] = $(this).val();
			});
			data.details = encodeURIComponent(data.details);
			//console.log(data.details)
			WT.wt_open_fullscreen('${base}/a/clubplace/clubPlace_Form',data);
		})
		$(".detailtable").on("click",".wt-view",function(){
			var data = {isShow:true}
			var $td = $(this).parent();
			$td.find("input").each(function( index ) {
				 data[$(this).attr("data-dname")] = $(this).val();
			});
			WT.wt_open_fullscreen('${base}/a/clubplace/clubPlace_Form',data);
		})
		
		$("#clubscale").val( fomatFloat(10 -$("#companyscale").val(),1))
        
        $("#companyscale").keyup(function(){
        	validateRatio()
    	})
		                                                      
		function validateRatio(){
        	
        	
        	var x = $("#companyscale").val();
        	if (isNaN(x)){ 
    			WT.wt_alert("分成比例，请输入数字");
    			return false;
    		}
        	if(x<0 || x>10){
    			WT.wt_alert("比例需要是0-10的数字");
    			return false;
    		}
    		if(x.indexOf('.')!=(x.length-1)){
    			$("#companyscale").val(fomatFloat(x,1));
    		}
    		$("#clubscale").val(fomatFloat(10 -x,1))
    		return true;
        }
        
        $("#layerForm").validate({
        	rules:{
        		  captain:{
        		  	maxlength: 30
        		  },
        		  telno:{
        		  	maxlength: 30
        		  },
        		  qq:{
        		  	maxlength: 30
        		  },
        		  describe:{
        		  	maxlength: 50
        		  },                 
		          c_password:{
		           	   minlength: 6,
                	   maxlength: 16,
		          },
		          re_password:{
		               equalTo:"#c_password"    //新密码的id选择器
		          },
		          email: {    //验证邮箱
			            email: true
			        },
		           mobile: {
		           		//required: true,
		           		digits:true,
                		minlength: 11,
                		maxlength: 11,
                        remote: {
                        	type: "post",
                            url: "${base}/a/clubInfo/checkUnique",
                            data: {
                            	mobile:function() {
                            		return  $("#mobile").val();
                            	},
                            	id:function() {
                            		return  $("#id").val();
                            	}
                            },
                            dataType: "json",
                            dataFilter: function(data, type){
                            	if (data == "true"){
                            		return true;
                            	}else{
                            		return false;
                            	}
                            }
                        }
	                },
	                cardid: {
		           		//required: true,
		           		minlength: 18,
                		maxlength: 18
	                },
	                cname: {
		           		required: true,
                		maxlength: 30,
                        remote: {
                        	type: "post",
                            url: "${base}/a/clubInfo/checkExistCname",
                            data: {
                            	cname:function() {
                            		return  $("#cname").val();
                            	},
                            	id:function() {
                            		return  $("#id").val();
                            	}
                            },
                            dataType: "json",
                            dataFilter: function(data, type){
                            	if (data == "true"){
                            		return true;
                            	}else{
                            		return false;
                            	}
                            }
                        }
	                }
		     },
		       
		     messages:{
		     	  captain:{
		     	  	maxlength:"最多可输入30字",
		     	  },
		     	  telno:{
		     	  	maxlength:"最多可输入30字",
		     	  },
		     	  qq:{
		     	  	maxlength:"最多可输入30字",
		     	  },
		     	  describe:{
		     	  	maxlength:"最多可输入50字",
		     	  },
		          c_password:{
		          	   minlength:"6-16位密码，区分大小写",
		          	   maxlength:"6-16位密码，区分大小写",
		           },
		          re_password:{
		               equalTo:"两次密码输入不一致"
		           },
		          mobile:{
		          		required:"手机号不能为空",
		          		digits:"手机号格式不正确",
		          		minlength:"手机号格式不正确",
		          		maxlength:"手机号格式不正确",
		          		remote:"手机号不可用"
		          },
		          cardid:{
		          		required:"身份证号不能为空",
		          		minlength:"身份证号格式不正确",
		          		maxlength:"身份证号格式不正确"
		          },
		          cname:{
		          		required:"俱乐部名称不能为空",
		          		remote:"俱乐部名称不可用",
		          		maxlength:"最多可输入50字"
		          },
		          email:{
		               email: "email格式不正确",
		           }                              
		     }
        });
        
        $("#logo_urldiv").WTBlankUploader({
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
        
        function createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file) {
        	if(file){
        		$("#file_name").val(file.name)
        	}
        	wtblankuploader_createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file)
        }
		
    });
    function fomatFloat(src,pos){
    	return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
    }
</script>
</body>
</#escape>
</html>
