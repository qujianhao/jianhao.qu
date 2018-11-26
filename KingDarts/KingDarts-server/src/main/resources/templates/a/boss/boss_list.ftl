<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <button type="button" class="btn btn-sm btn-info wt-add" >新增</button>
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

        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/boss/boss_search',
                [ 'id', '编号', 'boss名称', '血量', '创建时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'bno', index: 'bno', width: 5 , sortable: false},
                    {name: 'bname', index: 'bname', width: 5 , sortable: false},
                    {name: 'bvolume', index: 'bvolume', width: 5 , sortable: false},
                    {name: 'create_time', index: 'create_time', width: 10 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_use == 1 ? "取消应用" : "应用";
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_use + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>详情</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>修改</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>";
                        return text;
                    }}
                ]);

        //  详情
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/bossEntity/bossEntity_list?boss_id=' + rowData['id']);
        });
        
        //  修改
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/boss/boss_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/boss/boss_add');
        });

        //  应用
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_use = $(this).attr('data-value');
            var state_text = is_use == 1 ? "是否取消应用" : "应用后，boss将在第二天约0点开始应用。";
            var params_is_use = is_use == 1 ? 0 : 1;
            var params = {id : rowData.id, is_use: params_is_use};
            WT.wt_confirm(state_text, function () {
                WT.wt_ajax_jsondata('${base}/a/boss/boss_state', params, function (data) {
                    WT.wt_alert('修改状态成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            var is_use = rowData['is_use'];
            var state_text = is_use == 1 ? "删除后你将没有正在应用的boss,可能会导致此游戏无法使用。" : "是否删除boss";
            WT.wt_confirm(state_text, function () {
                WT.wt_ajax_jsondata('${base}/a/boss/boss_delete', params, function (data) {
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
