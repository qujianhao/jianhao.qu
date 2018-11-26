<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/commission/club_commission_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label>月份</label>
                            <input type="text" name="query_month" id="query_month" placeholder="请选择月份" class="form-control wt-datepicker" value="${paramMap.query_month}">
                        </div>
                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="submit" class="btn btn-sm btn-default">查询</button>
                    <button type="reset" class="btn btn-reset btn-default">重置</button>
                </div>
            </div>
        </div>
    </form>
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
                <button type="button" class="btn btn-sm btn-info wt-export">导出充值提成日流水</button>
                <button type="button" class="btn btn-sm btn-info wt-exportpay">导出充值日流水</button>
        </div>
    </div>
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

		$('.wt-datepicker').datetimepicker({
	        format: 'yyyy-mm',
	        weekStart: 1,
	        autoclose: true,
	        startView: 3,
	        minView: 3,
	        forceParse: false,
	        language: 'zh-CN'
	    });
    
        WT.wt_jqtable_multi('${base}/a/commission/club_commission_search',
                [ '俱乐部名称', '俱乐部编号', '区域','类别','机器台数','月流水','月流水合计'],
                [ 
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'cno', index: 'cno', width: 5 , sortable: false},
                    {name: 'city', index: 'city', width: 5 , sortable: false},
                    {name: 'type', index: 'type', sortable: false, width: 5, formatter: function(cellvalue, options, rowObject){
                        var text = "俱乐部";
                        return text;
                    }},
                    {name: 'eq_num', index: 'eq_num', width: 5 , sortable: false},
                    {name: 'month_amount',  width: 5 , sortable: false},
                    {name: 'month_pay_amount',  width: 5 , sortable: false}
                ],{
                    multiselect: false,
                    rownumbers: true
                });
                
        $(".wt-export").click(function(){
	         WT.wt_confirm('是否导出?', function () {
	        	var postData = WT.wt_serializeJSONObject("frameForm");
	        	postData.type = 1;
	            WT.wt_download('${base}/a/commission/club_commission_down', postData);
	         });
        });
        $(".wt-exportpay").click(function(){
	         WT.wt_confirm('是否导出?', function () {
	        	var postData = WT.wt_serializeJSONObject("frameForm");
		        postData.type = 2;
	            WT.wt_download('${base}/a/commission/club_commission_down', postData);
	         });
        });
                
    });
</script>
</body>
</#escape>
</html>
