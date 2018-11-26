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
        <div class="box box-primary" style="display: none;">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-4 form-group">
		                    <label>日期</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" name="order_time_start" value="${paramMap.order_time_start}" placeholder="请选择">
		                        </div>
		                        <div class="form-group wt-gap">
		                            <label>~</label>
		                        </div>
		                        <div class="form-group  wt-gap-right">
		                            <input type="text" class="form-control wt-datepicker" name="order_time_end" value="${paramMap.order_time_end}" placeholder="请选择">
		                        </div>
		                    </div>
		                </div>
                        <div class="col-sm-2 form-group">
                            <label for="equno">设备编号</label>
                            <input type="hidden" name="status" value="4">
                            <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" placeholder="请输入设备编码">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="cno">俱乐部名称</label>
                            <input type="hidden" class="form-control" name="auth_no" id="auth_no" value="${paramMap.auth_no}" >
                            <input type="text" class="form-control" name="cname" id="cname" value="${paramMap.cname}" placeholder="请输入俱乐部名称">
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
                <!-- <div id="wt_pager_list"></div> -->
            </div>
        </div>
    </div>

	<div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</section>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
    	 //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        
    	$(".wt-search").click(function(){
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})
    	
        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/gameOrder/gameTypeNum_search',
                ['游戏类型', '总计'],
                [ 
                    {name: 'game_type_name', index: 'game_type_name', width: 5 , sortable: false},
                    {name: 'num', index: 'num', width: 5 , sortable: false}
                ],{multiselect: false});

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equAuth/equAuth_view?id=' + rowData['id']);
        });


    });
</script>
</body>
</#escape>
</html>
