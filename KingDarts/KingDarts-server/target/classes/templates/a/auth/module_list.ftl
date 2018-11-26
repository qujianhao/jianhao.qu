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
                <div class="box-header with-border rightTitleH5">
                    功能模块
                </div>
                <div class="box-header with-border">
                    <button type="button" class="btn btn-sm btn-default" id="wt-module-refresh">刷新</button>
                    <#--<button type="button" class="btn btn-sm btn-info" id="wt-module-add">新建</button>-->
                    <#--<button type="button" class="btn btn-sm btn-info" id="wt-module-edit">编辑</button>-->
                    <#--<button type="button" class="btn btn-sm btn-danger" id="wt-module-delete">删除</button>-->
                </div>
                <div class="box-body">
                    <ul id="tree" class="ztree" style="overflow:auto;min-height: 700px;"></ul>
                </div>
            </div>
        </div>
        <div class="col-sm-9">
        <#--表单开始-->
        <#--表单结束-->

        <#--表格开始-->
            <div class="box box-default">
                <div class="box-header with-border rightTitleH5">
                    后台管理系统
                </div>
                <div class="box-header ">
                    <input type="hidden" name="id" id="id" value="${paramMap.id }">
                    <button type="button" class="btn btn-sm btn-info" id="wt-module-add">新建</button>
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
    zNodes.push({id:0, pId:0, name:"后台管理系统", open:true, collapse:false});
    var subNodes = eval('${moduleissions}');
    if (subNodes != null && subNodes.length > 0) {
        for (var int = 0; int < subNodes.length; int++) {
            zNodes.push(subNodes[int]);
        }
    }

    var setting = {
        data: {simpleData: {enable: true}},
        view: {selectedMulti: false},
        async: {
            enable: true,
            url:"${base}/a/module/module_tree",
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
        $(".rightTitleH5").text(treeNode.name);
        $("#id").val(treeNode.id);
        $("#wt_table_list").jqGrid('setGridParam',{ url: '${base}/a/module/module_search?id=' + treeNode.id }).trigger('reloadGrid');
    }

    function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {

    }
    function onAsyncSuccess(event, treeId, treeNode, msg) {
        // if (treeNode == null)
            // $.fn.zTree.getZTreeObj("tree").expandAll(true);
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

        WT.wt_jqtable_multi('${base}/a/module/module_search',
                ['ID', '名称', '编码', '图标', '请求地址', '排序', '更新时间', '操作'],
                [
                    {name: 'id', index: 'id', hidden:true},
                    {name: 'name', index: 'name', sortable: false, width: 5},
                    {name: 'code', index: 'code', sortable: false, width: 5},
                    {name: 'icon', index: 'icon', sortable: false, width: 5, formatter: function(cellvalue, options, rowObject){
                        var icon_text = WT.wt_is_empty(rowObject.icon) ? 'fa-circle-o' : rowObject.icon;
                        return '<i class="fa fa-fw '+icon_text+'"></i>'
                    }},
                    {name: 'url', index: 'url', sortable: false, width: 10},
                    {name: 'order_num', index: 'order_num', sortable: true, width: 5},
                    {name: 'update_time', index: 'update_time', sortable: false, width: 10},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        return "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-edit'>编辑</a>" +
                                "&nbsp;&nbsp;<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-delete wt-danger'>删除</a>";
                    }}
                ]);

        //  点击新建
        $("#wt-add").click(function () {
            WT.wt_open_fullscreen('${base}/a/module/module_add?parent=' + $("#id").val());
        });

        //  点击编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);
            WT.wt_open_fullscreen('${base}/a/module/module_edit?id=' + rowData['id']);
        });

        //  点击删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowId = $(this).attr('data-rowId');
            var rowData = $("#wt_table_list").jqGrid("getRowData", rowId);

            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_formdata('${base}/a/module/module_delete','id=' + rowData['id'],function(data){
                    WT.wt_alert('删除成功',function(){
                        wt_done();
                        WT.wt_reload_jqtable(window);
                        layer.closeAll();
                    });
                });
            });
        });

        $.fn.zTree.init($("#tree"), setting,zNodes );
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        treeObj.reAsyncChildNodes(0, "refresh");


        //  刷新模块
        $("#wt-module-refresh").click(function () {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
                    nodes = zTree.getSelectedNodes();
            if (nodes.length <= 0) {
                WT.wt_alert("请先选择一个父节点");
                return;
            }
            var node = nodes[0];
            var parentNode = node.getParentNode();
            if (node.id == 0){
                zTree.reAsyncChildNodes(node, 'refresh', false);
                zTree.selectNode(node);
                $("#wt_table_list").jqGrid('setGridParam',{ url: '${base}/a/module/module_search?id=' + node.id }).trigger('reloadGrid');
            }else{
                zTree.reAsyncChildNodes(parentNode, 'refresh', false);
                zTree.selectNode(parentNode);
                $("#wt_table_list").jqGrid('setGridParam',{ url: '${base}/a/module/module_search?id=' + parentNode.id }).trigger('reloadGrid');
            }

        });

        //  新建模块
        $("#wt-module-add").click(function () {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
                    nodes = zTree.getSelectedNodes();
            if (nodes.length <= 0) {
                WT.wt_alert("请先选择一个父节点");
                return;
            }
            WT.wt_open_layer('${base}/a/module/module_add?parent=' + $("#id").val());
        });

        //  编辑模块
        $("#wt-module-edit").click(function () {
            var nodes = $.fn.zTree.getZTreeObj("tree").getSelectedNodes();
            if (nodes.length <= 0) {
                WT.wt_alert("请先选择一个父节点");
                return;
            }
            if (nodes[0].id == 0){
                WT.wt_alert("根节点不能设置");
                return;
            }
            WT.wt_open_layer('${base}/a/module/module_edit?id=' + nodes[0].id);

        });

        //  删除模块
        $("#wt-module-delete").click(function () {
            var zTree = $.fn.zTree.getZTreeObj("tree"),
                    nodes = zTree.getSelectedNodes();
            if (nodes.length <= 0) {
                WT.wt_alert("请先选择要删除的节点");
                return;
            }
            if (nodes[0].id == 0 ){
                WT.wt_alert("根节点不能删除");
                return;
            }
            WT.wt_confirm('是否删除当前模块?', function () {
                WT.wt_ajax_formdata('${base}/a/module/module_delete', {id: nodes[0].id}, function (data) {
                    WT.wt_alert('删除成功');
                    wt_done();
                });
            });
        });


    });

    function wt_done(){
        $.fn.zTree.getZTreeObj("tree").reAsyncChildNodes(null, "refresh");
    }
</script>
</body>
</#escape>
</html>

