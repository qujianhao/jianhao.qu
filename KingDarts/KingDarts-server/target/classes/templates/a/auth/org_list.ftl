<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
    <link href="${base}/assets/plugins/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
    <#include "./a/commons/nav.ftl" />
<section class="content">
    <div class="row">
        <div class="col-sm-3">
            <div class="box box-primary">
                <ul id="tree" class="ztree" style="overflow:auto;min-height: 700px;"></ul>
            </div>
        </div>
        <div class="col-sm-9">
        <#--表单开始-->
        <#--表单结束-->

        <#--表格开始-->
            <div class="box box-default">
                <div class="box-header with-border" id="rightTitleH5">
                    默认
                </div>
                <div class="box-header with-border" id="rightTitleH5">
                    <input type="hidden" name="id" id="id" value="${searchBean.id }">
                    <button type="button" class="btn btn-sm btn-info" id="wt-add">新 建</button>
                </div>
                <div class="box-body">
                    <div class="jqGrid_wrapper ">
                        <table id="wt_table_list" class="wt-table-resize"></table>
                        <div id="wt_pager_list"></div>
                    </div>
                </div>
            </div>
        <#--表格结束-->
        </div>
    </div>
</section>
    <#include "./a/commons/bottom.ftl" />
<script src="${base}/assets/plugins/zTree/jquery.ztree.all.min.js"></script>
<script type="text/javascript">

    var zNodes = [];
    try {
        zNodes = eval('${permissions}')
    } catch (e){

    }

    var setting = {
        data: {simpleData: {enable: true}},
        view: {selectedMulti: false},
        async: {
            enable: true,
            url:"${base}/a/org/org_tree",
            autoParam:["id", "name=n", "level=lv"],
            otherParam:{"otherParam":"zTreeAsyncTest"},
            dataFilter: filter
        },
        callback: {
            beforeClick: zTreeBeforeClick,
            onClick : zTreeOnClick,
            onAsyncError: onAsyncError,
            onAsyncSuccess: onAsyncSuccess
        }
    };

    function zTreeBeforeClick(treeId, treeNode, clickFlag) {
        return true;
    };

    function zTreeOnClick(event, treeId, treeNode){
        $("#rightTitleH5").text(treeNode.name);
        $("#id").val(treeNode.id);
        $("#wt_table_list").jqGrid('setGridParam',{ url: '${base}/a/org/org_search?id=' + treeNode.id }).trigger('reloadGrid');
    }

    function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {

    }
    function onAsyncSuccess(event, treeId, treeNode, msg) {
        if (treeNode == null)
            $.fn.zTree.getZTreeObj("tree").expandAll(true);
    }

    function filter(treeId, parentNode, childNodes) {
        childNodes = childNodes.data;
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }

    $("document").ready(function () {

        WT.wt_jqtable_multi('${base}/a/org/org_search?id=1',
                ['ID', '名称', '描述', '排序', '更新时间', '操作'],
                [
                    {name: 'id', index: 'id', hidden:true},
                    {name: 'name', index: 'name'},
                    {name: 'summary', index: 'summary'},
                    {name: 'orderNum', index: 'orderNum'},
                    {name: 'createTime', index: 'createTime'},
                    {name: 'btns', index: 'btns', formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        return "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-edit'>编辑</a>" +
                                "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-danger wt-delete'>删除</a>";
                    }}
                ]);

        //  点击新建
        $("#wt-add").click(function () {
            console.log('wt-add', $("#id").val());
            WT.wt_open_layer('${base}/a/org/org_add?parent=' + $("#id").val());
        });

        //  点击编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            WT.wt_open_layer('${base}/a/org/org_edit?id=' + rowData['id']);
        });

        //  点击删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_formdata('${base}/a/perm/perm_delete','id=' + rowData['id'],function(data){
                    WT.wt_alert('删除成功',function(){
                        wt_done();
                        WT.wt_reload_jqtable(window);
                        layer.closeAll();
                    });
                });
            });
        });

        $.fn.zTree.init($("#tree"), setting,zNodes );
    });

    function wt_done(){
        $.fn.zTree.getZTreeObj("tree").reAsyncChildNodes(null, "refresh");
    }
</script>
</body>
</#escape>
</html>
