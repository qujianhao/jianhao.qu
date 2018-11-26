<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/protocol/userprotocol_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="mnames">创建人</label>
                            <input type="text" class="form-control" name="mnames" id="mnames" value="${paramMap.mnames}" placeholder="请输入创建人">
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
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <@wtAuth hasPrem="KINGDARTS_USERPROTOCOL:VIEW" ><span id="PREM_KINGDARTS_USERPROTOCOL_VIEW" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-default wt-view wt-hidden" >查看</button></@wtAuth>
        <@wtAuth hasPrem="KINGDARTS_USERPROTOCOL:EDIT" ><span id="PREM_KINGDARTS_USERPROTOCOL_EDIT" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-edit wt-hidden" >编辑</button></@wtAuth>
        <@wtAuth hasPrem="KINGDARTS_USERPROTOCOL:ADD" ><span id="PREM_KINGDARTS_USERPROTOCOL_ADD" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-add" >新增</button></@wtAuth>
        <@wtAuth hasPrem="KINGDARTS_USERPROTOCOL:DELETE" ><span id="PREM_KINGDARTS_USERPROTOCOL_DELETE" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-danger wt-delete wt-hidden" >删除</button></@wtAuth>
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

        WT.wt_jqtable_multi('${base}/a/protocol/userprotocol_search',
                ['id','主题','创建人姓名','是否发布', '更新时间', '创建时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'title', index: 'title', width: 10 , sortable: false},
                    {name: 'mnames', index: 'mnames', width: 5 , sortable: false},
                    {name: 'is_publish', index: 'is_publish', width: 5 , sortable: false,
                     formatter: function(cellvalue, options, rowObject){
                        var state_text = cellvalue == 1 ? "已发布" : "未发布";
                        return state_text;
                     }},
                    {name: 'update_time', index: 'update_time', width: 5 , sortable: false},
                    {name: 'create_time', index: 'create_time', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消发布" : "发布";
                        var text = "";
                        text += WT.wt_has_view('KINGDARTS_USERPROTOCOL') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>" : "";
                        text += WT.wt_has_edit('KINGDARTS_USERPROTOCOL') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>" : "";
                        text += WT.wt_has_edit('KINGDARTS_USERPROTOCOL') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_publish + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>" : "";
                        text += WT.wt_has_delete('KINGDARTS_USERPROTOCOL') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" : "";
                        return text;
                    }}
                ],{
                    multiselect: false,
                    rownumbers: true
                });

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/protocol/userprotocol_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/protocol/userprotocol_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/protocol/userprotocol_add');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/protocol/userprotocol_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/protocol/userprotocol_delete', params, function (data) {
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
