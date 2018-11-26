<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<section class="content">
    <div class="box box-primary">
        <div class="box-body">
            <div class="row">
                    <div class="col-sm-2 form-group">
                        <label for="worth">消耗游戏点数</label>
                        <input type="text" class="form-control"  id="worth" value="${balance}"/>
                    </div>
                    <div class="col-sm-12 wt-btns-left">
		                <button type="button" id="resetBalance" class="btn btn-sm btn-default">提交</button>
		            </div>
            </div>
        </div>
    </div>
    <div class="box box-default">
        <div class="box-body">
            <div class="jqGrid_wrapper ">
                <table id="wt_table_list" class="wt-table-resize"></table>
            </div>
        </div>
    </div>

</section>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        WT.wt_jqtable_multi('${base}/a/funcatchPrize/funcatchPrize_search',
                [ 'prize_id','位置', '名称','icon文件', '类型','概率','配置时间', '操作'],
                [ 
                    {name: 'prize_id', index: 'prize_id', width: 5 , sortable: false,hidden:true},
                    {name: 'position_num', index: 'position_num', width: 5 , sortable: false},
                    {name: 'prize_name', index: 'prize_name', width: 10 , sortable: false},
                    {name: 'icon_name', index: 'icon_name', width: 5 , sortable: false},
                    {name: 'is_physical', index: 'is_physical', width: 5 , sortable: false,
                    formatter: function(cellvalue, options, rowObject){
                        var text='';
                        if(cellvalue != undefined){
                        text= cellvalue == 'Y' ? '实物':cellvalue=='N'?'虚拟奖品':'抽奖按钮';
                        }
                        return text;
                    }},
                    {name: 'win_rate', index: 'win_rate', width: 10 , sortable: false},
                    {name: 'update_time', index: 'update_time', width: 5 , sortable: false},
                    {name: 'btns', index: 'btns', sortable: false, width: 10, formatter: function(cellvalue, options, rowObject){
                        var rowId = options.rowId;
                        var text = "";
                        if(rowObject.is_physical!='B' && rowObject.prize_name!='谢谢参与'){
                        text += "<a href='javascript:void(0);' data-rowId='" + rowId + "' class='wt-a wt-info wt-edit'>编辑</a>";
                        }
                        return text;
                    }}
                ]);

        //  编辑
        $(".jqGrid_wrapper").on('click', '.wt-edit', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            WT.wt_open_fullscreen('${base}/a/funcatchPrize/funcatchPrize_edit?prize_id=' + rowData['prize_id']);
        });

        //  修改消耗游戏点数
        $("#resetBalance").click(function () {
        var r = /^[0-9]*[1-9][0-9]*$/　　//正整数 
        if(r.test($("#worth").val())){
            WT.wt_confirm('是否修改消耗游戏点数?', function () {
               var params = {worth:$("#worth").val()};
               WT.wt_ajax_jsondata('${base}/a/funcatchPrize/funcatchPrize_consume', params, function (data) {
                    if(data.code==0){
                     WT.wt_alert('修改成功');
                    }else{
                     WT.wt_alert('请重新修改');
                    }
                });
            });
         }else{
            WT.wt_alert('消耗游戏点数修改必须为正整数！');
            return;
         }
        });

         
        //  删除
        $(".jqGrid_wrapper").on('click', '.wt-delete', function () {
            var rowData = WT.wt_jqtable_rowdata($(this).attr('data-rowId'));
            var params = {prize_id : $("#prizeId").val(),};
            WT.wt_confirm('是否删除?', function () {
                WT.wt_ajax_jsondata('${base}/a/funcatchPrize/funcatchPrize_delete', params, function (data) {
                    WT.wt_alert('删除成功');
                    WT.wt_reload_jqtable(this);
                });
            });
        });

    });
</script>
</body>
</#escape>
</html>
