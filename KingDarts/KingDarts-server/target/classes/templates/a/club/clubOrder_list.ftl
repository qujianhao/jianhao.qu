<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="cno">代理商编号</label>
                            <input type="text" class="form-control" name="agno" id="agno" value="${paramMap.agno}" >
                        </div> 
                        <div class="col-sm-2 form-group">
                            <label for="cno">俱乐部编码</label>
                            <input type="text" class="form-control" name="cno" id="cno" value="${paramMap.cno}" >
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="describe_message">描述</label>
                            <input type="text" class="form-control" name="describe_message" id="describe_message" value="${paramMap.describe_message}" >
                        </div>
                        <div class="col-sm-4 form-group">
		                    <label>消费日期</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_start" name="time_start" value="${paramMap.time_start}" placeholder="请选择">
		                        </div>
		                        <div class="form-group wt-gap">
		                            <label>~</label>
		                        </div>
		                        <div class="form-group  wt-gap-right">
		                            <input type="text" class="form-control wt-datepicker" style="width: 120px;" id="time_end" name="time_end" value="${paramMap.time_end}" placeholder="请选择">
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

        WT.wt_jqtable_multi('${base}/a/clubInfo/clubOrder_search',
                ['俱乐部名称','所属代理商','描述','地区','累计消费'],
                [ 
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'agname', index: 'agname', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        return cellvalue?cellvalue:'直营';
                    }},
                    {name: 'describe_message', index: 'describe_message', width: 5 , sortable: false},
                    {name: 'province', index: 'province', width: 5 , sortable: false},
                    {name: 'cost', index: 'cost', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        return cellvalue?cellvalue:'0';
                    }}
                ]);

    });
</script>
</body>
</#escape>
</html>
