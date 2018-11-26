<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="">
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
                                	<select class="form-control" name="equname" id="equname" required >
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
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">授权对象</h4>
                    <div class="row">
                            <div class="col-sm-5 form-group">
                                <label class="col-sm-4 control-label" for="merchant"><span style="color:red">*</span>商户类型</label>
                                <div class="col-sm-8">
                                <select class="form-control" name="merchant" id="merchant" data-wt-auto="${equAuth.merchant}" required>
			                        <option value="">请选择</option>
			                        <option value="1">俱乐部</option>
			                        <option value="2">代理商</option>
			                    </select>
                                </div>
                            </div>
                            
                            <div class="col-sm-5 form-group merchant_public">
                            	<label class="col-sm-4 control-label" for="merchant"><span style="color:red">*</span>俱乐部名称</label>
			                    <div class="col-sm-8">
			                    	<input type="hidden" name="auth_no" id="auth_no" value="${equAuth.auth_no}">
                            		<input type="text" class="form-control" name="auth_name" id="auth_name" value="${equAuth.auth_name}" readonly="readonly">
			                    <!-- <select class="form-control" id="auth_no_club" data-wt-auto="${equAuth.auth_no}" required>
			                        <option value="">请选择</option>
			                        <#list clubInfoList as clubInfo>
			                            <option value="${clubInfo.cno}" date-value="${clubInfo.cname}">${clubInfo.cname}</option>
			                        </#list>
			                    </select> -->
			                    </div>
			                </div>
			                
			                <div class="col-sm-5 form-group merchant_unpublic">
			                	<label class="col-sm-4 control-label" for="merchant"><span style="color:red">*</span>代理商名称</label>
			                    <div class="col-sm-8">
			                    <select class="form-control" id="auth_no_agent" data-wt-auto="${equAuth.auth_no}">
			                        <option value="">请选择</option>
			                        <#list agentInfoList as agentInfo>
			                            <option value="${agentInfo.agno}" date-value="${agentInfo.agname}">${agentInfo.agname}</option>
			                        </#list>
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
            <input type="hidden" name="id" id="id" value="${equAuth.id}"/>
             <button type="button" class="btn btn-sm btn-info wt-save">保存</button>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('新增虚拟设备');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        	
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            
           	var text = "是否新增虚拟设备?";
            var restext = "新增成功";
            var url = "${base}/a/equAuth/unentity_add";
            if($("#id").val()){
            	url = "${base}/a/equAuth/unentity_edit";
            	var text = "是否修改虚拟设备?";
                var restext = "修改成功";
            }
            
            WT.wt_confirm(text, function () {
                WT.wt_ajax_form(url,'layerForm',function(data){
                    WT.wt_alert(restext,function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
        
        if('${equAuth.merchant}'){
    		$('[name="merchant"]').trigger("change");
    	}else{
    		$(".merchant_public").hide();
			$(".merchant_unpublic").hide();
    	}
        
    });
    
    //商户类型
	 $('[name="merchant"]').change(function() {
		$("#auth_no").empty();
     	$("#auth_name").empty();
		if (this.value == '1') {
		    $(".merchant_public").show();
	        $(".merchant_unpublic").hide();
		} else{ 
			$(".merchant_public").hide();
	        $(".merchant_unpublic").show();
	    }
	});
	
    function putAuthNo(data){
    	if(data){
    		$("#auth_name").val(data.cname);
			$("#auth_no").val(data.cno);
    	}else{
    		$("#auth_name").val("");
			$("#auth_no").val("");
    	}
    }
    
    $("#auth_name").click(function(){
    	WT.wt_open_layer('${base}/a/clubInfo/select/clubInfo_list',null,"选择俱乐部");
    })
	/* $("#auth_no_club").change(function(){
		var opt=$("#auth_no_club").val();
		var _name = $("#auth_no_club").find("option:selected").attr("date-value");
		if(opt!=null){
			$("#auth_name").val(_name);
			$("#auth_no").val(opt);
		}else{
			$("#auth_name").val();
		}
	}); */
	
	$("#auth_no_agent").change(function(){
		var opt=$("#auth_no_agent").val();
		var _name = $("#auth_no_agent").find("option:selected").attr("date-value");
		if(opt!=null){
			$("#auth_name").val(_name);
			$("#auth_no").val(opt);
		}else{
			$("#auth_name").val();
		}
	});
    
    
    
    function fomatFloat(src,pos){
    	return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
    }
</script>
</body>
</#escape>
</html>
