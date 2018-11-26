<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/agentInfo/agentEquInfo_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">

                        <div class="col-sm-2 form-group">
                            <label for="equno">设备编号</label>
                            <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" maxlength="30" placeholder="请输入设备编号">
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
        <input type="hidden" name="agno" id="agno" value="${paramMap.agno}">
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

        WT.wt_jqtable_multi('${base}/a/agentInfo/agentEquInfo_search',
                [ '', '设备编号', '设备名称', '型号', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'equno', index: 'equno', width: 10 , sortable: false},
                    {name: 'equname', index: 'equname', width: 10 , sortable: false},
                    {name: 'models', index: 'models', width: 10 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 20, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>";
                        return text;
                    }}
                ]);
        
        
        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/agentInfo/agentEquInfo_view?type=0&id=' + rowData['id'],null,"查看");
			return false;
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
