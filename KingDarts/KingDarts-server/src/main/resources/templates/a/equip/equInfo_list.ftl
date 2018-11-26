<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/equInfo/equInfo_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="equ_status">设备状态</label>
                            	<select class="form-control" name="equ_status" id="equ_status">
			                        <option value="">全部</option>
			                        <option value="0">未授权</option>
			                        <option value="1">已授权</option>
			                        <option value="6">回收</option>
			                    </select>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="equno">设备编号</label>
                            <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" placeholder="请输入设备编号">
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
        <button type="button" class="btn btn-sm btn-default wt-add-unentity">新增虚拟设备</button>
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

        WT.wt_jqtable_multi('${base}/a/equInfo/equInfo_search',
                [ '', '设备编号', '设备名称', '设备状态', '型号','类型', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'equno', index: 'equno', width: 5 , sortable: false},
                    {name: 'equname', index: 'equname', width: 5 , sortable: false},
                    {name: 'equ_status', index: 'equ_status', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        if(cellvalue == 0) return '未授权';
                        if(cellvalue == 1) return '已授权';
                        if(cellvalue == 6) return '回收';
                        return '';
                    }},
                    {name: 'models', index: 'models', width: 5 , sortable: false},
                    {name: 'isentity', index: 'isentity', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        if(cellvalue == 0) return '虚拟';
                        if(cellvalue == 1) return '实体';
                        return '';
                    }},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                    	var rowId = options.rowId;
                        var text = "";
                        text +=  "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>";
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equInfo/equInfo_view?id=' + rowData['id']);
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equInfo/equInfo_edit?id=' + rowData['id']);
        });

        //  新增
        $(".wt-add-unentity").click(function () {
            WT.wt_open_fullscreen('${base}/a/equAuth/unentity_edit');
        });

        //  发布
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var is_publish = $(this).attr('data-value');
            var state_text = is_publish == 1 ? "取消发布" : "发布";
            var params_is_publish = is_publish == 1 ? 0 : 1;
            var params = {id : rowData.id, is_publish: params_is_publish};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/equInfo/equInfo_state', params, function (data) {
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
                WT.wt_ajax_jsondata('${base}/a/equInfo/equInfo_delete', params, function (data) {
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
