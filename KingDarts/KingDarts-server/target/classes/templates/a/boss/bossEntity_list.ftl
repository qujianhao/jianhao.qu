<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <form method="post" name="frameForm" id="frameForm" >
        <input  type="hidden" class="form-control" name="boss_id" id="boss_id" value="${paramMap.boss_id}">
    </form>
    <#if paramMap.boss_id!=''>
    <div class="box-footer">
        <div class="col-sm-12 wt-btns-left">
        <span id="PREM__BOSS_ADD" class="wt-hidden"></span><button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
    </#if>
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

        WT.wt_jqtable_multi('${base}/a/bossEntity/bossEntity_search',
                [ 'uuid', 'boss_id','编号','boss名称','总血量', '击杀时间','剩余血量','时间','操作'],
                [ 
                    {name: 'uuid', index: 'uuid', width: 5 , sortable: false,hidden:true},
                    {name: 'boss_id', index: 'boss_id', width: 5 , sortable: false,hidden:true},
                    {name: 'bno', index: 'bno', width: 5 , sortable: false},
                    {name: 'bname', index: 'bname', width: 5 , sortable: false},
                    {name: 'bvolume', index: 'bvolume', width: 5 , sortable: false},
                    {name: 'kill_time', index: 'kill_time', width: 5 , sortable: false,formatter: function(cellvalue, options, rowObject){
                        var text=cellvalue;
                        if(cellvalue==undefined){
                        text="未击杀";
                        }
                        return text;
                    }},
                    {name: 'evolume', index: 'evolume', width: 5 , sortable: false},
                    {name: 'update_time', index: 'update_time', width: 5 },
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text="";
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a  wt-view'>详情</a>";
                        return text;
                    }}
                ]);
        //关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        
         //  详情
        $(".jqGrid_wrapper").on('click', '.wt-view', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/bossEntity/bossEntity_view?uuid=' + rowData['uuid']);
        });
    });
</script>
</body>
</#escape>
</html>
