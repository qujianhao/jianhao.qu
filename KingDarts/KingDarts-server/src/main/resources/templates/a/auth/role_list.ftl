<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
  <#include "./a/commons/nav.ftl" />
<section class="content">
<#--表单开始-->
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/role/role_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                    <div class="col-sm-2 form-group">
                        <label for="name">角色名</label>
                        <input type="text" class="form-control" name="name" id="name" value="${searchBean.name}" placeholder="请输入用户名">
                    </div>
                    <div class="col-sm-2 form-group">
                        <label for="is_publish">是否发布</label>
                        <select class="form-control" name="isPublish"  id="isPublish" data-wt-auto="${searchBean.isPublish}">
                            <option value="">请选择</option>
                            <option value="1">已发布</option>
                            <option value="0">未发布</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-sm btn-default pull-left">搜索</button>
                </div>
            </div>
        </div>
    </form>
<#--表单结束-->

<#--表格开始-->
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
<#--表格结束-->

</section>
  <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        WT.wt_jqtable_multi('${base}/a/role/role_search',
                [ 'ID', '是否发布' ,'名称', '更新时间', '是否发布', '操作'],
                [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'isPublish', index: 'isPublish', hidden: true},
                    {name: 'name', index: 'name'},
                    {name: 'updateTime', index: 'updateTime'},
                    {name: 'isPublishName', index: 'isPublishName',formatter: function(cellvalue, options, rowObject){
                        return (rowObject.isPublish == "1" ? "启用" : "禁用")
                    }},
                    {name: 'btns', index: 'btns', formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var status = (rowObject.isPublish == "0" ? "启用" : "禁用");
                        return "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-edit'>编辑</a>" +
                                "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-status'>"+status+"</a>"+
                                "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-danger wt-delete'>删除</a>";
                    }}
                ]);

        //  编辑角色
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            WT.wt_open_fullscreen('${base}/a/role/role_edit?roleId=' + rowData['id']);
        });
        //  新增角色
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/role/role_edit');
        });
        //  停用启用角色
        $(".jqGrid_wrapper").on('click', '.wt-status', function () {
            var rowId = $(this).attr('data-rowId');
            var text = $(this).text();
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            var params = {roleId : rowData.id, isPublish: rowData.isPublish}
            WT.wt_confirm('是否'+text+'?', function () {
                WT.wt_ajax_jsondata('${base}/a/role/role_edit_status',params,function(data){
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        //  删除角色
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowId = $(this).attr('data-rowId');
            var text = $(this).text();
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            var params = {roleId : rowData.id, isDelete: 1}
            WT.wt_confirm('是否删除角色？删除角色不可恢复', function () {
                WT.wt_ajax_jsondata('${base}/a/role/role_edit_status',params,function(data){
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });

    function wt_done(){
        WT.wt_reload_jqtable(this);
    }
</script>
</body>
</#escape>
</html>
