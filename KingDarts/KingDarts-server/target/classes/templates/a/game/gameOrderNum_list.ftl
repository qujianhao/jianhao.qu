<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/equAuth/equAuth_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="equno">设备编号</label>
                            <input type="hidden" name="status" value="4">
                            <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" placeholder="请输入设备编码">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="cno">俱乐部名称</label>
                            <input type="text" class="form-control" name="cname" id="cname" value="${paramMap.cname}" placeholder="请输入俱乐部名称">
                        </div> 
                        <div class="col-sm-4 form-group">
		                    <label>申请日期</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_start" name="order_time_start" value="${paramMap.order_time_start}" placeholder="请选择">
		                        </div>
		                        <div class="form-group wt-gap">
		                            <label>~</label>
		                        </div>
		                        <div class="form-group  wt-gap-right">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_end" name="order_time_end" value="${paramMap.order_time_end}" placeholder="请选择">
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

    	$(".wt-search").click(function(){
    		if(!compareDate($("#time_start").val(),$("#time_end").val())){
        		return false;
        	}
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})
    	
        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/gameOrder/gameOrderNum_search',
                ['','设备编码','俱乐部', '击打总计',  '操作'],
                [ 
                    {name: 'auth_no', index: 'auth_no', width: 5 , sortable: false,hidden:true},
                    {name: 'equno', index: 'equno', width: 5 , sortable: false},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'num', index: 'num', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>" ;
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var pdata = WT.wt_serializeJSONObject("frameForm");
            pdata.equno = rowData.equno;
            pdata.auth_no = rowData.auth_no;
            WT.wt_open_fullscreen('${base}/a/gameOrder/gameTypeNum_list',pdata);
        });


    });
</script>
</body>
</#escape>
</html>
