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
                            <label for="cno">代理商编号</label>
                            <input type="text" class="form-control" name="agno" id="agno" value="${paramMap.agno}" placeholder="请输入代理商编号">
                        </div> 
                        <div class="col-sm-2 form-group">
                            <label for="nickname">用户名</label>
                            <input type="text" class="form-control" name="nickname" id="nickname" value="${paramMap.nickname}" placeholder="请输入设备编码">
                        </div>
                        <div class="col-sm-4 form-group">
		                    <label>激活日期</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_start" name="create_time_start" value="${paramMap.create_time_start}" placeholder="请选择">
		                        </div>
		                        <div class="form-group wt-gap">
		                            <label>~</label>
		                        </div>
		                        <div class="form-group  wt-gap-right">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_end" name="create_time_end" value="${paramMap.create_time_end}" placeholder="请选择">
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

        WT.wt_jqtable_multi('${base}/a/user/member_search',
                ['用户名','累计充值','余额','代理商','俱乐部', '激活时间'],
                [ 
                    {name: 'nickname', index: 'nickname', width: 5 , sortable: false},
                    {name: 'amount', index: 'amount', width: 5 , sortable: false},
                    {name: 'balance', index: 'balance', width: 5 , sortable: false},
                    {name: 'agname', index: 'agname', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        return cellvalue?cellvalue:'直营';
                    }},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'create_time', index: 'create_time', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        return new Date(cellvalue).format('yyyy-MM-dd HH:mm:ss');
                    }}
                ]);

    });
</script>
</body>
</#escape>
</html>
