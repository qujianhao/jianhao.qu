<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/advertInfo/advertInfo_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="file_name">文件名</label>
                            <input type="text" class="form-control" name="file_name" id="file_name" value="${paramMap.file_name}" placeholder="请输入文件名称">
                        </div>
                        <#-- <div class="col-sm-2 form-group">
                            <label>update_time起始</label>
                            <input type="text" name="update_time_start" id="update_time_start" placeholder="请输入起始时间" class="form-control wt-datepicker" value="${paramMap.update_time_start}">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>update_time截止</label>
                            <input type="text" name="update_time_end" id="update_time_end" placeholder="请输入截止时间" class="form-control wt-datepicker" value="${paramMap.update_time_end}">
                        </div> -->

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
        <@wtAuth hasPrem="_ADVERTINFO:VIEW" ><span id="PREM__ADVERTINFO_VIEW" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-default wt-view wt-hidden" >查看</button></@wtAuth>
        <@wtAuth hasPrem="_ADVERTINFO:EDIT" ><span id="PREM__ADVERTINFO_EDIT" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-edit wt-hidden" >编辑</button></@wtAuth>
        <@wtAuth hasPrem="_ADVERTINFO:ADD" ><span id="PREM__ADVERTINFO_ADD" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-info wt-add" >新增</button></@wtAuth>
        <@wtAuth hasPrem="_ADVERTINFO:DELETE" ><span id="PREM__ADVERTINFO_DELETE" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-danger wt-delete wt-hidden" >删除</button></@wtAuth>
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
        
        $(".wt-search").click(function(){
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})

        WT.wt_jqtable_multi('${base}/a/advertInfo/advertInfo_search',
                [ '',  '名称', '大小', '描述', '上传时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'file_name', index: 'file_name', width: 5 , sortable: false},
                    {name: 'file_size', index: 'file_size', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                    	var  size = cellvalue?cellvalue/1024/1024:0;
                        return size.toFixed(2)+"M";
                    }},
                    {name: 'des_title', index: 'des_title', width: 5 , sortable: false},
                    {name: 'update_time', index: 'update_time', width: 5 },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.is_publish == 1 ? "取消发布" : "发布";
                        var text = "";
                        text += WT.wt_has_edit('_ADVERTINFO') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>" : "";
                        text += WT.wt_has_edit('_ADVERTINFO') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.is_publish + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>" : "";
                        text += WT.wt_has_delete('_ADVERTINFO') ? "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" : "";
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/advertInfo/advertInfo_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/advertInfo/advertInfo_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/advertInfo/advertInfo_add');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/advertInfo/advertInfo_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/advertInfo/advertInfo_delete', params, function (data) {
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
