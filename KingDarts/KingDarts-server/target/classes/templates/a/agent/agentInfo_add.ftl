<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/agentInfo/agentInfo_add">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">代理商信息添加</h4>
                    <div class="row">
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="agname"><span style="color:red">*</span>代理商名称：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="agname" id="agname" value="${entity.agname}" placeholder="请输入代理商名称" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label"><span style="color:red">*</span>分成比例：</label>
                                <!-- <label class="col-sm-3" style="float:left;" for="companyscale">公司</label>
                                <div class="col-sm-125">
                                    <input type="text" name="companyscale" id="companyscale" value="${entity.companyscale}" title="请输入1-10" >
                                </div>
                                <label class="col-sm-3" style="float:left;" for="agentscale">代理</label>
                                <div class="col-sm-125">
                                    <input type="text" name="agentscale" id="agentscale" value="${entity.agentscale}" title="请输入1-10" >
                                </div> -->
                                <div class="col-sm-9">
	                                 <div class="col-sm-2 control-label" style="font-size:15px;">公司</div>
	                                 <div class="col-sm-3"><input type="text" class="form-control" name="companyscale" id="companyscale" value="${entity.companyscale}" required></div>
	                                 <div class="col-sm-3 control-label" style="font-size:15px;">代理/直营</div>
	                                 <div class="col-sm-3"><input type="text" class="form-control" name="agentscale" id="agentscale" value="${entity.agentscale}" readonly="readonly"></div>
	                            </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="captain"><span style="color:red">*</span>负责人：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="captain" id="captain" value="${entity.captain}" placeholder="请输入负责人" title="请输入负责人" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="card_id">身份证：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="card_id" id="card_id" value="${entity.card_id}" placeholder="请输入身份证" title="请输入身份证">
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="ag_password"><span style="color:red">*</span>密码：</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="ag_password" id="ag_password" value="${entity.ag_password}" placeholder="6-16位密码，区分大小写" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="ag_password"><span style="color:red">*</span>确认密码：</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="cag_password" id="cag_password" value="${entity.ag_password}" required>
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="mobile">手机号：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="mobile" id="mobile" value="${entity.mobile}" placeholder="请输入手机号" >
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="telno">固定电话：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="telno" id="telno" value="${entity.telno}" placeholder="请输入固定电话" title="请输入固定电话" >
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="email">email：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="email" id="email" value="${entity.email}" placeholder="请输入email" title="请输入email" >
                                </div>
                            </div>
                            <div class="col-sm-6 form-group">
                                <label class="col-sm-3 control-label" for="ag_addr">地址：</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="ag_addr" id="ag_addr" value="${entity.ag_addr}" placeholder="请输入地址" title="请输入地址" >
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
             <@wtAuth hasPrem="_AGENTINFO:ADD" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
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
    		$("#agentscale").val( fomatFloat(10 -x,1))
    		return true;
        }
        
        //  保存
        $(".wt-save").click(function () {
        
        	var x = $("#companyscale").val();
        	if (isNaN(x)){ 
    			WT.wt_alert("分成比例，请输入数字");
    			return false;
    		}
    		if(x<0 || x>10){
    			WT.wt_alert("比例需要是0-10的数字");
    			return false;
    		}
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/agentInfo/agentInfo_save','layerForm',function(data){
                    WT.wt_alert('保存成功',function(index){
                    	$("#layerForm")[0].reset();  
                    	layer.close(index);
                    });
                });
            });
        });
        
        $("#layerForm").validate({
        	rules:{
        		  captain:{
        		  	maxlength: 30
        		  },
        		  telno:{
        		  	maxlength: 30
        		  },
        		  ag_addr:{
        		  	maxlength: 200
        		  },    
		          ag_password:{
		               required:true,
		           	   minlength: 6,
                	   maxlength: 16,
		          },
		          cag_password:{
		               required:true,
		               equalTo:"#ag_password"    //新密码的id选择器
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
                            url: "${base}/a/agentInfo/checkExistMobile",
                            data: {
                            	mobile:function() {
                            		return  $("#mobile").val();
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
	                card_id: {
		           		//required: true,
		           		minlength: 18,
                		maxlength: 18
	                },
	                agname: {
		           		required: true,
        		  		maxlength: 30,
                        remote: {
                        	type: "post",
                            url: "${base}/a/agentInfo/checkExisAgname",
                            data: {
                            	agname:function() {
                            		return  $("#agname").val();
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
		     	  ag_addr:{
		     	  	maxlength:"最多可输入200字",
		     	  },
		          ag_password:{
		               required: "请输入密码",
		          	   minlength:"6-16位密码，区分大小写",
		          	   maxlength:"6-16位密码，区分大小写",
		           },
		          cag_password:{
		               required: "请确认密码",
		               equalTo:"两次密码输入不一致"
		           },
		          mobile:{
		          		required:"手机号不能为空",
		          		digits:"手机号格式不正确",
		          		minlength:"手机号格式不正确",
		          		maxlength:"手机号格式不正确",
		          		remote:"手机号不可用"
		          },
		          card_id:{
		          		required:"身份证号不能为空",
		          		minlength:"身份证号格式不正确",
		          		maxlength:"身份证号格式不正确"
		          },
		          agname:{
		          		required:"代理商名称不能为空",
		          		remote:"代理商名称不可用",
		     	  		maxlength:"最多可输入30字",
		          },
		          email:{
		               email: "email格式不正确",
		           }                          
		     }
        });
    });
    
</script>
</body>
</#escape>
</html>
