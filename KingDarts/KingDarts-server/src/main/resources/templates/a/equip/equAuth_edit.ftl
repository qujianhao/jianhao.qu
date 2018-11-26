<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/equAuth/equAuth_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equno">设备编号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equno" id="equno" value="${equInfo.equno}" maxlength="30" required readonly="readonly">
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="models">设备型号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="models" id="models" value="${equInfo.models}" maxlength="30" readonly="readonly" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equname">设备名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equname" id="equname" value="${equInfo.equname}" maxlength="50" readonly="readonly"  >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="prod_batch">生产批次</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="prod_batch" id="prod_batch" value="${equInfo.prod_batch}" maxlength="10" readonly="readonly">
                                </div>
                            </div>
                            <#if type == 'manage'>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equ_status">设备状态</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="equ_status" id="equ_status" data-wt-auto="${equInfo.equ_status}" required>
				                        <option value="0">未授权</option>
				                        <option value="1">已授权</option>
				                        <option value="6">回收</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="produ_addr">设备合作方式</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="cooperation" id="cooperation" data-wt-auto="${equAuth.cooperation}" required>
				                        <option value="">请选择</option>
				                        <option value="1">出售</option>
				                        <option value="2">租赁</option>
				                    </select>
                                </div>
                            </div> 
                            <#else>
                            	<input name="equ_status" type="hidden" value="1">
                            </#if>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-4 control-label" for="equ_version">使用版本</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="equ_version" id="equ_version" value="${equInfo.equ_version}" readonly="readonly">
                                </div>
                            </div>
                            <!-- <#if type == 'manage'>
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-4 control-label" for="profit_ratio">分成比例</label>
                                <div class="col-sm-8">
	                                 <div class="col-sm-4">公司</div>
	                                 <div class="col-sm-4"><input type="text" class="form-control" name="profit_ratio" id="profit_ratio" value="${equAuth.profit_ratio}" required></div>
	                                 <div class="col-sm-4">代理/直营</div>
	                                 <div class="col-sm-4"><input type="text" class="form-control" name="def_ratio" id="def_ratio" value="" readonly="readonly"></div>
                                </div>
                            </div> 
                            </#if> -->
                    </div>
                </section>
            </div>
        </div>
    </section>
    <#if type != 'manage'>
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
    </#if>
    
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="id" id="id" value="${equAuth.id}"/>
             <@wtAuth hasPrem="_EQUAUTH:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('授权');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        
        $("#def_ratio").val(10 - fomatFloat($("#profit_ratio").val(),1))
        
        $("#profit_ratio").keyup(function(){
        	validateRatio()
    	})
    	
    	function validateRatio(){
        	if('${type}' != 'manage') return true;
        	
        	var x = $("#profit_ratio").val();
        	if (isNaN(x)){ 
    			WT.wt_alert("分成比例，请输入数字");
    			return false;
    		}
    		if(x<=0 || x>=10){
    			WT.wt_alert("比例需要是0-10的数字");
    			return false;
    		}
    		if(x.indexOf('.')!=(x.length-1)){
    			$("#profit_ratio").val(fomatFloat(x,1));
    		}
    		$("#def_ratio").val(10 - fomatFloat(x,1))
    		return true;
        }
        	
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            /* if(!validateRatio())
            	return false */
            	
            var url = "${base}/a/equAuth/equAuth_add";
            if($("#id").val()){
            	url = "${base}/a/equAuth/equAuth_edit";
            }
            var text = "是否授权?";
            var restext = "授权成功";
            if($("#equ_status").val()=='6'){
            	text = "是否回收?";
            	restext = "回收成功";
            }else  if($("#equ_status").val()=='0'){
            	text = "是否取消授权?";
            	restext = "取消授权成功";
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
