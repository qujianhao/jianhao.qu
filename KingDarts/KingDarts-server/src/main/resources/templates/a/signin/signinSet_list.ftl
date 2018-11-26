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

        WT.wt_jqtable_multi('${base}/a/signinSet/signinSet_search',
                [ 'id', '累计天数', '名称', '签到获取点数', '是否发布', '修改时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'signin_day', index: 'signin_day', width: 5 , sortable: false},
                    {name: 'name', index: 'name', width: 5 , sortable: false},
                    {name: 'point', index: 'point', width: 5 , sortable: false},
                    {name: 'is_publish', index: 'is_publish', width: 5 , sortable: false,
                    formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = rowObject.is_publish == 1 ? "已发布" : "未发布";
                        return text;
                    }},
                    {name: 'update_time', index: 'update_time', width: 5 },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消发布" : "发布";
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>" ;
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/signinSet/signinSet_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/signinSet/signinSet_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/signinSet/signinSet_add');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/signinSet/signinSet_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/signinSet/signinSet_delete', params, function (data) {
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
