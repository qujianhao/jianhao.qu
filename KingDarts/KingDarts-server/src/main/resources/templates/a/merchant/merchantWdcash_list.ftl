<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/merchantWdcash/merchantWdcash_search">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
						<div class="col-sm-3 form-group">
                            <label for="wd_status">提现状态</label>
                            <select class="form-control" name="wd_status" id="wd_status">
                            	<option value="">全部</option>
                            	<option value="1">成功</option>
                            	<option value="2">审核中</option>
                            	<option value="3">审核不通过</option>
                            	<option value="0">失败</option>
                            </select>
                        </div>
                        <div class="col-sm-3 form-group">
                            <label for="pay_order_no">订单编号</label>
                            <input type="text" class="form-control" name="pay_order_no" id="pay_order_no" value="" placeholder="请输入订单编号">
                        </div>
                        <div class="col-sm-6 form-group">
		                    <label>申请日期</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" name="apply_time_start" id="time_start" value="" placeholder="请选择">
		                        </div>
		                        <div class="form-group wt-gap">
		                            <label>~</label>
		                        </div>
		                        <div class="form-group  wt-gap-right">
		                            <input type="text" class="form-control wt-datepicker" name="apply_time_end" id="time_end" value="" placeholder="请选择">
		                        </div>
		                    </div>
		                </div>

                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="button" class="btn btn-sm btn-default wt-search">搜索</button>
                    <button type="reset" class="btn btn-sm btn-default">重置</button>
                </div>
            </div>
        </div>
    </form>
    <div class="box box-default">
        <div class="box-body">
            <div class="jqGrid_wrapper ">
                <table id="wt_table_list" class="wt-table-resize"></table>
                <div id="wt_pager_list"></div>
            </div>
        </div>
    </div>

</section>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

    	$('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});
        $(".wt-search").click(function(){
        	if(!compareDate($("#time_start").val(),$("#time_end").val())){
        		return false;
        	}
            $("#wt_table_list").jqGrid('setGridParam',{
                postData : WT.wt_serializeJSONObject("frameForm"),
                page:1
            }).trigger("reloadGrid");
        })
        

        WT.wt_jqtable_multi('${base}/a/merchant/merchantWdcash_search',
                [ '', '订单编号','商户编号', '申请时间', '提现金额', '联系方式', '状态', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'pay_order_no', index: 'pay_order_no', width: 5 , sortable: false},
                    {name: 'merno', index: 'merno', width: 5 , sortable: false},
                    {name: 'apply_time', index: 'apply_time', width: 5 , sortable: false},
                    {name: 'cash_nums', index: 'cash_nums', width: 5 , sortable: false},
                    {name: 'me_rmobile', index: 'me_rmobile', width: 5 , sortable: false},
                    {name: 'wd_status', index: 'wd_status', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                    	if(cellvalue == 1){
                    		return "成功";
                    	}else if(cellvalue == 0){
                    		return "失败";
                    	}else if(cellvalue == 2){
                    		return "审核中";
                    	}else if(cellvalue == 3){
                    		return "审核不通过";
                    	}
                        return "";
                    }},
                    {name: 'btns', index: 'btns', sortable: false, width: 5, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        if(rowObject.wd_status == 2)
                        	text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>审核</a>";
                        return text;
                    }}
                ]);

    	//  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            
            if (rowData['id'] != ""){
        		$.ajax({
	        		url : "${base}/a/merchant/checkExist",
	        		type : "POST",
	        		async:false,
	        		data:{'id':rowData['id']},
	                dataType: "json",
	                dataFilter: function(data, type){
	                	if (data == "true"){
            				WT.wt_open_fullscreen('${base}/a/merchant/merchantWdcash_edit?id=' + rowData['id']);
	                	}else{
	                		WT.wt_alert("该提现申请已审核，请刷新页面！");
	                		return false;
	                	}
	                }
	        	})
        	}
        });
    

    });
</script>
</body>
</#escape>
</html>
