<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
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

        WT.wt_jqtable_multi('${base}${base}/a/medal/medal_search',
                [ 'id', '勋章名称', '勋章描述','is_valid', '是否生效', '排序','更新时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'medal_name', index: 'medal_name', width: 5 , sortable: false},
                    {name: 'medal_desc', index: 'medal_desc', width: 5 , sortable: false},
                    {name: 'is_valid', index: 'is_valid', width: 5 , sortable: false,hidden:true},
                    {name: 'valid_desc', width: 5 , sortable: false,formatter: function(cellvalue, options, rowObject){
                        var val=rowObject.is_valid;
                        var text = "";
                        if(val==1){
                          text="生效";
                        }
                        if(val==0){
                          text="未生效";
                        }
                        return text;
                    }},
                    {name: 'order_no', index: 'order_no', width: 5 , sortable: false},
                    {name: 'update_time', index: 'update_time', width: 5 },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_valid == 1 ? "不生效" : "生效";
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_valid + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>";
                        return text;
                    }}
                ]);


        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/medal/medal_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/medal/medal_add');
        });

        //  生效
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_valid = $(this).attr('data-value');
            var state_text = is_valid == 1 ? "不生效" : "生效";
            var params_is_valid = is_valid == 1 ? 0 : 1;
            var params = {id : rowData.id, is_valid: params_is_valid};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/medal/medal_state', params, function (data) {
                    WT.wt_alert('保存成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/medal/medal_delete', params, function (data) {
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
