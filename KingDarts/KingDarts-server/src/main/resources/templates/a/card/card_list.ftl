<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/card/card_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">


                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="submit" class="btn btn-sm btn-default">搜索</button>
                    <button type="reset" class="btn btn-sm btn-default">重置</button>
                </div>
            </div>
        </div>
    </form>
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <@wtAuth hasPrem="_CARD:VIEW" ><span id="PREM__CARD_VIEW" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-default wt-view wt-hidden" >查看</button></@wtAuth>
        <@wtAuth hasPrem="_CARD:EDIT" ><span id="PREM__CARD_EDIT" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-edit wt-hidden" >编辑</button></@wtAuth>
        <@wtAuth hasPrem="_CARD:ADD" ><span id="PREM__CARD_ADD" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-add" >新增</button></@wtAuth>
        <@wtAuth hasPrem="_CARD:DELETE" ><span id="PREM__CARD_DELETE" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-danger wt-delete wt-hidden" >删除</button></@wtAuth>
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

        WT.wt_jqtable_multi('${base}/a/card/card_search',
                [ 'card_id', 'card_no', 'card_id_hex', 'denomination', 'balance', 'card_type', 'cno', 'user_id', 'activation_equno', 'isvalid', 'give_balance', 'isdefault', 'coupon_balance', '操作'],
                [ 
                    {name: 'card_id', index: 'card_id', width: 5 , sortable: false},
                    {name: 'card_no', index: 'card_no', width: 5 , sortable: false},
                    {name: 'card_id_hex', index: 'card_id_hex', width: 5 , sortable: false},
                    {name: 'denomination', index: 'denomination', width: 5 , sortable: false},
                    {name: 'balance', index: 'balance', width: 5 , sortable: false},
                    {name: 'card_type', index: 'card_type', width: 5 , sortable: false},
                    {name: 'cno', index: 'cno', width: 5 , sortable: false},
                    {name: 'user_id', index: 'user_id', width: 5 , sortable: false},
                    {name: 'activation_equno', index: 'activation_equno', width: 5 , sortable: false},
                    {name: 'isvalid', index: 'isvalid', width: 5 , sortable: false},
                    {name: 'give_balance', index: 'give_balance', width: 5 , sortable: false},
                    {name: 'isdefault', index: 'isdefault', width: 5 , sortable: false},
                    {name: 'coupon_balance', index: 'coupon_balance', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消发布" : "发布";
                        var text = "";
                        text += WT.wt_has_view('_CARD') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>" : "";
                        text += WT.wt_has_edit('_CARD') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>" : "";
                        text += WT.wt_has_edit('_CARD') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_publish + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>" : "";
                        text += WT.wt_has_delete('_CARD') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" : "";
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/card/card_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/card/card_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/card/card_add');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/card/card_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/card/card_delete', params, function (data) {
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
