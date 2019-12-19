<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
    <form method="post" name="frameForm" id="frameForm" action="">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="cno">俱乐部编号</label>
                            <input type="text" class="form-control" name="cno" id="cno" value="${paramMap.cno}" maxlength="30" placeholder="请输入俱乐部编号">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="cname">俱乐部名称</label>
                            <input type="text" class="form-control" name="cname" id="cname" value="${paramMap.cname}" maxlength="30" placeholder="请输入俱乐部名称">
                        </div>
                        
                        <div class="col-sm-2 form-group">
                            <label for="describe">描述</label>
                            <input type="text" class="form-control" name="describe_message" id="describe_message" value="${paramMap.describe_message}" maxlength="50" placeholder="请输入描述信息">
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

<div class="wt-layer-footer">
		    <div class="col-sm-12 col-sm-offset-5 wt-layer-footer-content">
		    	
		        <button type="button" class="btn btn-sm btn-primary wt-confirm">确认</button>
		        <button type="button" class="btn btn-sm btn-primary wt-close">关闭</button>
		    </div>
		</div>
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

    	$(".wt-close").click(function(){
    	   WT.wt_close();
       	})
       	
       	$(".wt-confirm").click(function(){
    	   var clubInfos =$('#wt_table_list').jqGrid('getGridParam','selarrrow');
    	   var rowData;
    	   for(var i=0 ;i<clubInfos.length;i++){
    		   rowData = $('#wt_table_list').jqGrid('getRowData',clubInfos[i]);
    	   }
    	   window.parent.putAuthNo(rowData);

    	   WT.wt_close();
       })
       
        WT.wt_jqtable_multi('${base}/a/clubInfo/clubInfo_search',
                [ 'id', '俱乐部编号', '俱乐部名称', '俱乐部负责人', '创建时间'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'cno', index: 'cno', width: 5 , sortable: false},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'captain', index: 'captain', width: 5 , sortable: false},
                    {name: 'add_time', index: 'add_time', width: 5 }
                ],{multiselect: true, multiboxonly:true, gridComplete: hideSelectAll, beforeSelectRow: beforeSelectRow});

        function hideSelectAll() {  
			$("#cb_wt_table_list").hide();//隐藏全选按钮  
            return(true);  
        } 

        function beforeSelectRow() {  
            $("#wt_table_list").jqGrid('resetSelection');  
            return(true);  
        }  

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
