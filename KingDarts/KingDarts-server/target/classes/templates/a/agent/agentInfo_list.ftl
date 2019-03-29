<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/agentInfo/agentInfo_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="agno">代理商编号</label>
                            <input type="text" class="form-control" name="agno" id="agno" value="${paramMap.agno}" maxlength="30" placeholder="请输入代理商编号">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="agname">代理商名称</label>
                            <input type="text" class="form-control" name="agname" id="agname" value="${paramMap.agname}" maxlength="30" placeholder="请输入代理商名称">
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

        WT.wt_jqtable_multi('${base}/a/agentInfo/agentInfo_search',
                [  'id','代理商编号', '代理商名称', '创建时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'agno', index: 'agno', width: 10 , sortable: false},
                    {name: 'agname', index: 'agname', width: 10 , sortable: false},
                    {name: 'add_time', index: 'add_time', width: 10 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 20, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-viewf'>名下飞镖机</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-viewj'>名下俱乐部</a>";
                            text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-advertManage'>广告管理</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>修改</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" ;
                        return text;
                    }}
                ]);
           //  广告管理
        $(".jqGrid_wrapper").on('click', '.wt-advertManage', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/advert_manage?type=1&id=' + rowData['id'],null,"广告管理");
        });             
                
                
        //  名下飞镖机
        $(".jqGrid_wrapper").on('click', '.wt-viewf', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/agentEquInfo_list?type=0&agno=' + rowData['agno'],null,"查看");
			return false;
        });
        
        //  名下俱乐部
        $(".jqGrid_wrapper").on('click', '.wt-viewj', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/agentClubInfo_list?type=0&agno=' + rowData['agno'],null,"查看");
			return false;
        });

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/agentInfo_edit?type=0&id=' + rowData['id'],null,"查看");
			return false;
        });

        //  修改
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/agentInfo_edit?type=1&id=' + rowData['id'],null,"修改");
        });
        
        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/agentInfo/agentInfo_delete', params, function (data) {
                    WT.wt_alert('删除成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });
    
    // 清空form表单查询信息
	function resetForm(){
	
	    $("input[type='button']").parent().parent().parent().find("input").each(
	        function(){
	            if(this.type !="button"){
	                this.value=""
	            }
	        });
	
	}
</script>
</body>
</#escape>
</html>
