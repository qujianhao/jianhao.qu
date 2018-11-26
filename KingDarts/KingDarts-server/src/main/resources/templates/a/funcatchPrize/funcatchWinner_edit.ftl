<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/funcatchWinner/funcatchWinner_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">获奖信息</h4>
                    <div class="row">
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-3 control-label" for="position_num"><span style="color:red">*</span>获奖用户名</label>
                                <div class="col-sm-9">
                                    <input type="text"  class="form-control" name="receive_name" id="receive_name" 
                                    value="${entity.receive_name}" placeholder="请输入用户名" title="请输入用户名" required>
                                </div>
                            </div>
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-3 control-label" for="is_physical">类型</label>
                                <div class="col-sm-9">
                                    <select class="form-control"  disabled="disabled" data-wt-auto="${funcatchPrize.is_physical}" >
				                        <option value="">请选择</option>
				                        <option value="N">虚拟奖品</option>
				                        <option value="Y">实物</option>
				                    </select>
                                </div>
                            </div>
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-3 control-label" for="is_physical">奖品</label>
                                <div class="col-sm-9">
                                    <input type="text"  disabled="disabled" class="form-control" name="prize_name" id="prize_name" 
                                    value="${entity.prize_name}" placeholder="请输入奖品名称" title="请输入奖品名称" required>
                                </div>
                            </div>
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-3 control-label" for="receive_address"><span style="color:red">*</span>地址</label>
                                <div class="col-sm-9">
                                	<input type="text"  class="form-control" name="receive_address" id="receive_address" 
                                    value="${entity.receive_address}" placeholder="请输入地址" title="请输入地址" required>
                                </div>
                            </div>
                            <div class="col-sm-8 form-group">
                                <label class="col-sm-3 control-label" for="receive_phone"><span style="color:red">*</span>手机号</label>
                                <div class="col-sm-9">
                                	<input type="text"  class="form-control" name="receive_phone" id="receive_phone" 
                                    value="${entity.receive_phone}" placeholder="请输入手机号" title="请输入手机号" required>
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" id="id" name="id" value="${entity.id}"/>
            <input type="hidden" id="is_deliver" name="is_deliver" value="${entity.is_deliver}"/>
            <#if (funcatchPrize.is_physical=='Y' && entity.is_deliver==0)>
            <button type="button" class="btn btn-sm btn-info wt-deliever">已发货</button>
            <button type="button" class="btn btn-sm btn-info wt-save">保存</button>
            </#if>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                  WT.wt_ajax_form('${base}/a/funcatchWinner/funcatchWinner_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                  }); 
            });
        });
        
        //发货确认
        $(".wt-deliever").click(function () {
            if(!$("#layerForm").valid())
                return false;
            $("#is_deliver").val(1);
            WT.wt_confirm('是否发货?', function () {
                  WT.wt_ajax_form('${base}/a/funcatchWinner/funcatchWinner_edit','layerForm',function(data){
                    WT.wt_alert('发货成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                  }); 
            });
        });
        
    });
</script>
</body>
</#escape>
</html>
