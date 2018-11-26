<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/newuser/newuser_give_balance_list">
        <div class="box box-primary">
        </div>
    </form>
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <button type="button" class="btn btn-sm btn-default wt-add-unentity">新增</button>
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
    	$(".wt-search").click(function(){
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})
        //$('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/newuser/usergivebalance_search',
                [ '', '奖励游戏点','起始时间', '结束时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'give_game_balance', index: 'give_game_balance', width: 15 , sortable: false},
                    {name: 'start_time', index: 'start_time', width: 12 , sortable: true,
	                        formatter: function(cellvalue, options, rowObject){
	                            if(cellvalue != '' && cellvalue != undefined){
	                                return formatDate(cellvalue);
	                            }
	                            return "-";
	                        }
	                },
                    {name: 'end_time', index: 'end_time', width: 12 , sortable: true,
	                        formatter: function(cellvalue, options, rowObject){
	                            if(cellvalue != '' && cellvalue != undefined){
	                                return formatDate(cellvalue);
	                            }
	                            return "-";
	                        }
	                },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                    	var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消应用" : "应用";
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_publish + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>";
                        
                        return text;
                    }}
                ],{
                    multiselect: false,
                    rownumbers: true
                });

        //  新增
        $(".wt-add-unentity").click(function () {
            WT.wt_open_layer('${base}/a/newuser/newusergivebalance_add',{});
        });

        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/newuser/newusergivebalance_delete', params, function (data) {
                    WT.wt_alert('删除成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        
        //  ACAC设置
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消应用" : "应用";
            var params_acac = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_acac};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/newuser/update_is_publish', params, function (data) {
                    WT.wt_alert('保存成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        
        function formatDate(time) {
	        var date = new Date(time);
	        var y = date.getFullYear();
	        var m = date.getMonth() + 1;
	        var d = date.getDate();
	        return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
	    }

    });
</script>
</body>
</#escape>
</html>
