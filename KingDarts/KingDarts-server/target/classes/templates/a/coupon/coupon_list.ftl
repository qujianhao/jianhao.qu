<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/coupon/coupon_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="couponno">优惠券编码</label>
                            <input type="text" class="form-control" name="couponno" id="couponno" value="${paramMap.couponno}" placeholder="请输入优惠券编码">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="coupon_status">优惠券状态</label>
                            	<select class="form-control" name="coupon_status" id="coupon_status">
			                        <option value="">全部</option>
			                        <option value="0">未使用</option>
			                        <option value="1">已使用</option>
			                    </select>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="nickname">使用人</label>
                            <input type="text" class="form-control" name="nickname" id="nickname" value="${paramMap.nickname}" placeholder="请输入使用人">
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

        WT.wt_jqtable_multi('${base}/a/coupon/coupon_search',
                [ '', '优惠券编码','游戏点', '状态', '创建时间', '使用人','激活时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'couponno', index: 'couponno', width: 25 , sortable: false},
                    {name: 'game_balance', index: 'game_balance', width: 5 , sortable: false},
                    {name: 'coupon_status', index: 'coupon_status', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        if(cellvalue == 0) return '未使用';
                        if(cellvalue == 1) return '已使用';
                        return '';
                    }},
                    {name: 'create_time', index: 'create_time', width: 12 , sortable: true,
	                        formatter: function(cellvalue, options, rowObject){
	                            if(cellvalue != '' && cellvalue != undefined){
	                                return WT.formatDate(cellvalue);
	                            }
	                            return "-";
	                        }
	                },
                    {name: 'nickname', index: 'nickname', width: 5 , sortable: false},
                    {name: 'apply_time', index: 'apply_time', width: 12 , sortable: true,
	                        formatter: function(cellvalue, options, rowObject){
	                            if(cellvalue != '' && cellvalue != undefined){
	                                return WT.formatDate(cellvalue);
	                            }
	                            return "-";
	                        }
	                },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                    	var rowId = options.rowId;
                        var text = "";
                        if(rowObject.coupon_status=='0'){
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>";
                        }
                        return text;
                    }}
                ]);

        //  新增
        $(".wt-add-unentity").click(function () {
            WT.wt_open_layer('${base}/a/coupon/coupon_add',{});
        });

        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/coupon/coupon_delete', params, function (data) {
                    WT.wt_alert('删除成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });
</script>
</body>
</#escape>
</html>
