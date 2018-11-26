<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" action="${base}/a/medalWinner/medalWinner_list">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row">
                        <div class="col-sm-2 form-group">
                            <label for="medal_name">勋章名称</label>
                            <input type="text" class="form-control" name="medal_name" id="medal_name" value="${paramMap.medal_name}" placeholder="请输入勋章名称">
                        </div>
                </div>
            </div>
            <div class="box-footer">
                <div class="col-sm-12 wt-btns-left">
                    <button type="submit" class="btn btn-sm btn-default">搜索</button>
                    <button  class="btn btn-reset btn-default">重置</button>
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

        WT.wt_jqtable_multi('${base}/a/medalWinner/medalWinner_search',
                [ 'id', 'user_id', '得到勋章的账号', '勋章名称', '是否领取'],
                [ 
                    {name: 'id', index: 'id', width: 5 , sortable: false,hidden:true},
                    {name: 'user_id', index: 'user_id', width: 5 , sortable: false,hidden:true},
                    {name: 'useraccount', index: 'useraccount', width: 5 , sortable: false},
                    {name: 'medal_name', index: 'medal_name', width: 5 , sortable: false},
                    {name: 'is_receive', index: 'is_receive', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var text = "";
                        if(cellvalue==1){
                            text="已领取";
                        }
                        if(cellvalue==0){
                            text="未领取";
                        }
                        return text;
                    }}
                ]);
    });
</script>
</body>
</#escape>
</html>
