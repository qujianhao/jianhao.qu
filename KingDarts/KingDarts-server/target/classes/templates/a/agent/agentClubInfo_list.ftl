<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/agentInfo/agentClubInfo_list">
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
                    <button type="submit" class="btn btn-sm btn-default wt-search">搜索</button>
                    <input type="button" onclick="resetForm()" class="btn btn-sm btn-default" value="重置" >
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

        WT.wt_jqtable_multi('${base}/a/agentInfo/agentClubInfo_search',
                [ 'id', '俱乐部编号', '俱乐部名称', '俱乐部负责人', '创建时间', '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'cno', index: 'cno', width: 5 , sortable: false},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false},
                    {name: 'captain', index: 'captain', width: 5 , sortable: false},
                    {name: 'add_time', index: 'add_time', width: 5 , sortable: true,
                        formatter: function(cellvalue, options, rowObject){
                            if(cellvalue != '' && cellvalue != undefined){
                                return WT.formatDate(cellvalue);
                            }
                            return "-";
                        }
                    },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var state_text = rowObject.acac == 1 ? "取消ACAC" : "设置ACAC";
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-view'>查看</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edity'>游戏定价</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' data-value='" + rowObject.acac + "' class='wt-a wt-warning wt-state'>" + state_text + "</a>";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-danger wt-delete'>删除</a>" ;
                        return text;
                    }}
                ]);

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/clubInfo/clubInfo_edit?type=0&id=' + rowData['id'],null,"查看");
        });

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/clubInfo/clubInfo_edit?type=1&id=' + rowData['id'],null,"编辑");
        });
        
        //  游戏定价
        $(".jqGrid_wrapper").on('click', '.wt-edity', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            wt_open_layer('${base}/a/clubInfo/clubInfo_manage_game_price?id=' + rowData['id'],null,"游戏定价");
			return false;
        });
        
        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {id : rowData.id};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/agentInfo/AgentclubInfo_delete', params, function (data) {
                    WT.wt_alert('删除成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        
        //  ACAC设置
        $(".jqGrid_wrapper").on('click', '.wt-state', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var acac = $(this).attr('data-value');
            var state_text = acac == 1 ? "取消ACAC" : "设置ACAC";
            var params_acac = acac == 1 ? 0 : 1;
            var params = {id : rowData.id, acac: params_acac};
            WT.wt_confirm('是否' + state_text + '?', function () {
                WT.wt_ajax_jsondata('${base}/a/clubInfo/update_acac', params, function (data) {
                    WT.wt_alert('保存成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });
        
        function wt_open_layer(url,params,title){
	        title = title || "详情";
	        layer.open({
	            type: 2,
	            title: title,
	            shadeClose: false,
	            shade: 0.5,
	            scrollbar:false,
	            area: ['50%', '60%'],
	            content: getUrl(url, params)
	        });
	    }
	    
	    
	    
	    function getUrl(url, datas) {
	        datas = eval(datas);
	        var dataStr = "";
	        var flag = 0;
	        for(var data in datas){
	            dataStr += ((flag===0?"?":"&") + data + "=" + datas[data]);
	            flag++;
	        }
	        return url + dataStr;
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
