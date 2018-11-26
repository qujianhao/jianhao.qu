<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/equAuth/equAuth_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="equ_status">设备状态</label>
                            <select class="form-control" name="equ_status" id="equ_status">
                            <option value="">全部</option>
                            <option value="0">未授权</option>
                            <option value="1">已授权</option>
                            <!-- <option value="2">投产审核</option>
                            <option value="3">投产</option>
                            <option value="4">临时维修</option>
                            <option value="5">报废</option> -->
                            <option value="6">回收</option>
                            </select>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="equno">设备编号</label>
                            <input type="text" class="form-control" name="equno" id="equno" value="${paramMap.equno}" placeholder="请输入设备编码">
                        </div>
                        <div class="col-sm-2 form-group">
                            <label for="cno">所属俱乐部名称</label>
                            <input type="text" class="form-control" name="cname" id="cname" value="${paramMap.cname}" placeholder="请输入俱乐部名称">
                        </div> 
                        <div class="col-sm-2 form-group">
                            <label for="cno">所属代理商名称</label>
                            <input type="text" class="form-control" name="agname" id="agname" value="${paramMap.agname}" placeholder="请输入代理商名称">
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
    		$("#wt_table_list").jqGrid('setGridParam',{
    			  postData : WT.wt_serializeJSONObject("frameForm"),  
    		      page:1
    		}).trigger("reloadGrid");
    	})
    	
        //$('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/equAuth/equAuth_search',
                [ 'id', '设备编码','设备名称', '设备状态',  '所属俱乐部/代理商', '型号',  '操作'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'equno', index: 'equno', width: 5 , sortable: false},
                    {name: 'equname', index: 'equname', width: 5 , sortable: false},
                    {name: 'equ_status', index: 'equ_status', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                        if(cellvalue == 0) return '未授权';
                        if(cellvalue == 1) return '已授权';
                        if(cellvalue == 2) return '投产审核';
                        if(cellvalue == 3) return '投产';
                        if(cellvalue == 4) return '临时维修';
                        if(cellvalue == 5) return '报废';
                        if(cellvalue == 6) return '回收';
                        return '';
                    }},
                    {name: 'cname', index: 'cname', width: 5 , sortable: false, formatter: function(cellvalue, options, rowObject){
                    	var t = cellvalue?cellvalue:rowObject.agname
                        return t?t:"";
                    }},
                    {name: 'models', index: 'models', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>授权</a>" ;
                        if(rowObject.equ_status == 1){
                        	text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-manage'>管理</a>" ;
                        	text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-qrcode'>查看二维码</a>" ;
                        }
                        return text;
                    }}
                ],{multiselect: false});

        //  查看
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equAuth/equAuth_view?id=' + rowData['id']);
        });

        //  授权
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equAuth/equAuth_edit?id=' + rowData['id']+'&equno=' + rowData['equno']);
        });
        //  管理
        $(".jqGrid_wrapper").on('click', '.wt-manage', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/equAuth/equAuth_edit?type=manage&id=' + rowData['id']+'&equno=' + rowData['equno']);
        });

        //  新增
        $(".wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/equAuth/equAuth_add');
        });

        $(".jqGrid_wrapper").on('click', '.wt-qrcode', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_layer('${base}/a/equAuth/qrcode?id=' + rowData['id']+'&equno=' + rowData['equno'],"","二维码",{area: ['300px', '300px']});
        });

    });
</script>
</body>
</#escape>
</html>
