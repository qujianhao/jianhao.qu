<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/clubrank/rank_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
		                    <label>月份</label>
		                    <div class="form-inline">
		                        <div class="form-group wt-gap-left">
		                            <input type="text" class="form-control wt-datepicker" name="rank_time" value="${paramMap.rank_time}" placeholder="请选择">
		                        </div>
		                    </div>
		                </div>
                        <div class="col-sm-2 form-group">
                            <label for="cno">省份</label>
                            <input type="text" class="form-control" name="province" id="province" value="${paramMap.province}" placeholder="请输入">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="acac">ACAC</label>
                            <select class="form-control" name="acac" id="acac" data-wt-auto="${paramMap.acac}">
                                    <option value="">请选择</option>
			                        <option value="1">是</option>
			                        <option value="0">否</option>
		                    </select>
                        </div> 
                        <div class="col-sm-2 form-group">
                            <label for="cno">排名类型</label>
                            <select class="form-control" name="type" id="type">
		                        <option selected="selected" value="1">竞技榜</option>
		                        <option value="2">实力榜</option>
		                    </select>
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

        $(".wt-search").click(function(){
    		if($("#type").val()==1){
    			$("#wt_table_list").setGridParam().hideCol("strength_value");
    			$("#wt_table_list").setGridParam().showCol("athletics_num");  
    		}else{
    			$("#wt_table_list").setGridParam().hideCol("athletics_num");
    			$("#wt_table_list").setGridParam().showCol("strength_value");  
    		}
    		$("#wt_table_list").jqGrid('setGridParam',{
  			  postData : WT.wt_serializeJSONObject("frameForm"),  
  		      page:1
  		}).trigger("reloadGrid");
    	})
    	
    	$('.wt-datepicker').datetimepicker({
	        format: 'yyyy-mm',
	        weekStart: 1,
	        autoclose: true,
	        startView: 3,
	        minView: 3,
	        forceParse: false,
	        language: 'zh-CN'
    	});
    	
        	WT.wt_jqtable_multi('${base}/a/clubrank/rank_search',
                    ['排名','俱乐部名称','省份','竞技值', '实力值'],
                    [ 
                        {name: 'rank', index: 'rank', width: 5 , sortable: false},
                        {name: 'cname', index: 'cname', width: 5 , sortable: false},
                        {name: 'province', index: 'province', width: 5 , sortable: false},
                        {name: 'athletics_num', index: 'athletics_num', width: 5 , sortable: false},
                        {name: 'strength_value', index: 'strength_value', sortable: false, width: 10,hidden:true}
                    ]);
        
        

    });
</script>
</body>
</#escape>
</html>
