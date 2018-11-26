<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/challenge/challenge_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="sponsor_nickname">sponsor_nickname</label>
                            <input type="text" class="form-control" name="sponsor_nickname" id="sponsor_nickname" value="${paramMap.sponsor_nickname}" placeholder="请输入sponsor_nickname">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="receiver_nickname">receiver_nickname</label>
                            <input type="text" class="form-control" name="receiver_nickname" id="receiver_nickname" value="${paramMap.receiver_nickname}" placeholder="请输入receiver_nickname">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="game_name">game_name</label>
                            <input type="text" class="form-control" name="game_name" id="game_name" value="${paramMap.game_name}" placeholder="请输入game_name">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="club_name">club_name</label>
                            <input type="text" class="form-control" name="club_name" id="club_name" value="${paramMap.club_name}" placeholder="请输入club_name">
                        </div>

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
        <@wtAuth hasPrem="_CHALLENGE:VIEW" ><span id="PREM__CHALLENGE_VIEW" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-default wt-view wt-hidden" >查看</button></@wtAuth>
        <@wtAuth hasPrem="_CHALLENGE:EDIT" ><span id="PREM__CHALLENGE_EDIT" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-edit wt-hidden" >编辑</button></@wtAuth>
        <@wtAuth hasPrem="_CHALLENGE:ADD" ><span id="PREM__CHALLENGE_ADD" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-add" >新增</button></@wtAuth>
        <@wtAuth hasPrem="_CHALLENGE:DELETE" ><span id="PREM__CHALLENGE_DELETE" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-danger wt-delete wt-hidden" >删除</button></@wtAuth>
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

        WT.wt_jqtable_multi('${base}/a/challenge/challenge_search',
                [ 'id', 'sponsor_id', 'sponsor_useraccount', 'sponsor_nickname', 'sponsor_headimg', 'receiver_id', 'receiver_useraccount', 'receiver_nickname', 'receiver_headimg', 'game_type_id', 'game_type_value', 'game_name', 'club_id', 'club_cno', 'club_name', 'receive_status', 'challenge_status', 'sponsor_miss', 'receiver_miss', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false},
                    {name: 'sponsor_id', index: 'sponsor_id', width: 5 , sortable: false},
                    {name: 'sponsor_useraccount', index: 'sponsor_useraccount', width: 5 , sortable: false},
                    {name: 'sponsor_nickname', index: 'sponsor_nickname', width: 5 , sortable: false},
                    {name: 'sponsor_headimg', index: 'sponsor_headimg', width: 5 , sortable: false},
                    {name: 'receiver_id', index: 'receiver_id', width: 5 , sortable: false},
                    {name: 'receiver_useraccount', index: 'receiver_useraccount', width: 5 , sortable: false},
                    {name: 'receiver_nickname', index: 'receiver_nickname', width: 5 , sortable: false},
                    {name: 'receiver_headimg', index: 'receiver_headimg', width: 5 , sortable: false},
                    {name: 'game_type_id', index: 'game_type_id', width: 5 , sortable: false},
                    {name: 'game_type_value', index: 'game_type_value', width: 5 , sortable: false},
                    {name: 'game_name', index: 'game_name', width: 5 , sortable: false},
                    {name: 'club_id', index: 'club_id', width: 5 , sortable: false},
                    {name: 'club_cno', index: 'club_cno', width: 5 , sortable: false},
                    {name: 'club_name', index: 'club_name', width: 5 , sortable: false},
                    {name: 'receive_status', index: 'receive_status', width: 5 , sortable: false},
                    {name: 'challenge_status', index: 'challenge_status', width: 5 , sortable: false},
                    {name: 'sponsor_miss', index: 'sponsor_miss', width: 5 , sortable: false},
                    {name: 'receiver_miss', index: 'receiver_miss', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消发布" : "发布";
                        var text = "";
                        text += WT.wt_has_view('_CHALLENGE') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>" : "";
                        text += WT.wt_has_edit('_CHALLENGE') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>" : "";
                        text += WT.wt_has_edit('_CHALLENGE') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_publish + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>" : "";
                        text += WT.wt_has_delete('_CHALLENGE') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" : "";
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/challenge/challenge_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/challenge/challenge_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/challenge/challenge_add');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/challenge/challenge_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/challenge/challenge_delete', params, function (data) {
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
