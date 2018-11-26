<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body skin-green">
<#include "./a/commons/nav.ftl" />

<section class="content">
<#--表单开始-->
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/account/account_list">
    <div class="box box-primary">
        <div class="box-body">
            <div class="row">
                <div class="col-sm-2 form-group">
                    <label for="name">用户ID</label>
                    <input type="text" class="form-control" name="id" id="id" value="${searchBean.id}" placeholder="请输入用户ID">
                </div>
                <div class="col-sm-2 form-group">
                    <label for="name">账号名称</label>
                    <input type="text" class="form-control" name="username" id="username" value="${searchBean.username}" placeholder="请输入用户名">
                </div>
                <div class="col-sm-2 form-group">
                    <label>注册起始日期</label>
                    <input type="text" name="time_start" id="time_start" placeholder="请输入起始日期" class="form-control wt-datepicker" value="${searchBean.startTime}">
                </div>
                <div class="col-sm-2 form-group">
                    <label>注册截止日期</label>
                    <input type="text" name="time_end" id="time_end" placeholder="请输入截止日期" class="form-control wt-datepicker" value="${searchBean.endTime}">
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
<#--表单结束-->

<#--表格开始-->
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
            <@wtAuth hasPrem="ACCOUNT:ADD" >
                <button type="button" class="btn btn-sm btn-info wt-add" >新增</button>
            </@wtAuth>
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

    	$(".wt-search").click(function(){
    		if(!compareDate($("#time_start").val(),$("#time_end").val())){
        		return false;
        	}
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})
    	
        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/account/account_search',
                ['ID', '账号名称', '创建时间',  '状态', '操作',''],
                [
                    {name: 'id', index: 'id'},
                    {name: 'username', index: 'username'},
                    {name: 'create_time', index: 'create_time'},
                    {name: 'isPublishName', index: 'isPublishName',formatter: function(cellvalue, options, rowObject){
                        return (rowObject.is_publish == "1" ? "启用" : "禁用")
                    }},
                    {name: 'btns', index: 'btns', formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var status = (rowObject.is_publish == "0" ? "启用" : "禁用");
                        var content = "";
                            content += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-edit'>编辑</a>"
                            content += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-status'>"+status+"</a>";
                            content += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-danger wt-delete'>删除</a>";
                        return content;
                    }},
                    {name: "is_publish", index: "is_publish", hidden:true},
                ]);

        //  编辑账号
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            WT.wt_open_fullscreen('${base}/a/account/account_edit?id=' + rowData['id']);
        });

        //  添加账号
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/account/account_edit');
        });

        //  停用启用账号
        $(".jqGrid_wrapper").on('click', '.wt-status', function () {
            var rowId = $(this).attr('data-rowId');
            var text = $(this).text();
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            var params = {id : rowData.id, is_publish: rowData.is_publish==1?0:1}
            WT.wt_confirm('是否'+text+'?', function () {
                WT.wt_ajax_jsondata('${base}/a/account/account_state',params,function(data){
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        //  删除账号
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowId = $(this).attr('data-rowId');
            var text = $(this).text();
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            var params = {id : rowData.id, is_delete: 1}
            WT.wt_confirm('是否删除角色？删除角色不可恢复', function () {
                WT.wt_ajax_jsondata('${base}/a/account/account_delete',params,function(data){
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });

</script>
</body>
</#escape>
</html>
