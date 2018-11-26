<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/funcatchWinner/funcatchWinner_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="username">用户名</label>
                            <input type="text" class="form-control" name="username" id="username" value="${paramMap.username}" placeholder="请输入用户名">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="is_physical">类型</label>
                            <select class="form-control" name="is_physical" id="is_physical" data-wt-auto="${paramMap.is_physical}" >
		                        <option value="">请选择</option>
		                        <option value="N">虚拟奖品</option>
		                        <option value="Y">实物</option>
				            </select>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="is_deliver">状态</label>
                            <select class="form-control" name="is_deliver" id="is_deliver" data-wt-auto="${paramMap.is_deliver}" >
		                        <option value="">请选择</option>
		                        <option value="0">待发货</option>
		                        <option value="1">已发货</option>
				            </select>
                        </div>
                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="submit" class="btn btn-sm btn-default">搜索</button>
                    <button  class="btn btn-reset btn-default">重置</button>
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

        WT.wt_jqtable_multi('${base}/a/funcatchWinner/funcatchWinner_search',
                [ 'id', '用户名','is_physical', '类型', '奖品名称', '地址', '时间', '状态', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'username', index: 'username', width: 5 , sortable: false},
                    {name: 'is_physical', index: 'is_physical', width: 5 , sortable: false,hidden:true},
                    {name: 'is_physical_desc', index: 'is_physical_desc', width: 5 , sortable: false,
                    formatter: function(cellvalue, options, rowObject){
                        var text='';
                        if(rowObject.is_physical != undefined){
                        text= rowObject.is_physical == 'Y' ? '实物':rowObject.is_physical=='N'?'虚拟奖品':'';
                        }
                        return text;
                    }},
                    {name: 'prize_name', index: 'prize_name', width: 5 , sortable: false},
                    {name: 'receive_address', index: 'receive_address', width: 5 , sortable: false},
                    {name: 'create_time', index: 'create_time', width: 5 , sortable: false},
                    {name: 'is_deliver', index: 'is_deliver', width: 5 , sortable: false,
                     formatter: function(cellvalue, options, rowObject){
                        var text='';
                        if(cellvalue != undefined){
                        	if(rowObject.is_physical == 'Y')
                        		text= cellvalue == 1 ? '已发货':'未发货';
                        	if(rowObject.is_physical == 'N')
                        		text= cellvalue == 1 ? '已领取':'未领取';
                        }
                        return text;
                    }},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        if(rowObject.is_physical == 'Y'){
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>详情</a>";
                        }
                        return text;
                    }}
                ]);

        //  详情
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/funcatchWinner/funcatchWinner_view?id=' + rowData['id']);
        });


    });
</script>
</body>
</#escape>
</html>
