<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body">
<#include "./a/commons/nav.ftl" />
<form method="post" name="layerForm" id="layerForm" action="${base}/a/challenge/challenge_edit">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <h4 class="page-header">基础信息</h4>
                    <div class="row">
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="sponsor_id">sponsor_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sponsor_id" id="sponsor_id" value="${entity.sponsor_id}" placeholder="请输入sponsor_id" title="请输入sponsor_id" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="sponsor_useraccount">sponsor_useraccount</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sponsor_useraccount" id="sponsor_useraccount" value="${entity.sponsor_useraccount}" placeholder="请输入sponsor_useraccount" title="请输入sponsor_useraccount" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="sponsor_nickname">sponsor_nickname</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sponsor_nickname" id="sponsor_nickname" value="${entity.sponsor_nickname}" placeholder="请输入sponsor_nickname" title="请输入sponsor_nickname" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="sponsor_headimg">sponsor_headimg</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sponsor_headimg" id="sponsor_headimg" value="${entity.sponsor_headimg}" placeholder="请输入sponsor_headimg" title="请输入sponsor_headimg" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receiver_id">receiver_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receiver_id" id="receiver_id" value="${entity.receiver_id}" placeholder="请输入receiver_id" title="请输入receiver_id" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receiver_useraccount">receiver_useraccount</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receiver_useraccount" id="receiver_useraccount" value="${entity.receiver_useraccount}" placeholder="请输入receiver_useraccount" title="请输入receiver_useraccount" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receiver_nickname">receiver_nickname</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receiver_nickname" id="receiver_nickname" value="${entity.receiver_nickname}" placeholder="请输入receiver_nickname" title="请输入receiver_nickname" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receiver_headimg">receiver_headimg</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receiver_headimg" id="receiver_headimg" value="${entity.receiver_headimg}" placeholder="请输入receiver_headimg" title="请输入receiver_headimg" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="game_type_id">game_type_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="game_type_id" id="game_type_id" value="${entity.game_type_id}" placeholder="请输入game_type_id" title="请输入game_type_id" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="game_type_value">game_type_value</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="game_type_value" id="game_type_value" value="${entity.game_type_value}" placeholder="请输入game_type_value" title="请输入game_type_value" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="game_name">game_name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="game_name" id="game_name" value="${entity.game_name}" placeholder="请输入game_name" title="请输入game_name" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="club_id">club_id</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="club_id" id="club_id" value="${entity.club_id}" placeholder="请输入club_id" title="请输入club_id" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="club_cno">club_cno</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="club_cno" id="club_cno" value="${entity.club_cno}" placeholder="请输入club_cno" title="请输入club_cno" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="club_name">club_name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="club_name" id="club_name" value="${entity.club_name}" placeholder="请输入club_name" title="请输入club_name" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >start_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="start_time" id="start_time" placeholder="请输入start_time" class="form-control wt-datepicker" value="${entity.start_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group" class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" >float_time</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                        <input type="text" name="float_time" id="float_time" placeholder="请输入float_time" class="form-control wt-datepicker" value="${entity.float_time}" >
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receive_status">receive_status</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receive_status" id="receive_status" value="${entity.receive_status}" placeholder="请输入receive_status" title="请输入receive_status" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="challenge_status">challenge_status</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="challenge_status" id="challenge_status" value="${entity.challenge_status}" placeholder="请输入challenge_status" title="请输入challenge_status" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="sponsor_miss">sponsor_miss</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="sponsor_miss" id="sponsor_miss" value="${entity.sponsor_miss}" placeholder="请输入sponsor_miss" title="请输入sponsor_miss" >
                                </div>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label class="col-sm-3 control-label" for="receiver_miss">receiver_miss</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="receiver_miss" id="receiver_miss" value="${entity.receiver_miss}" placeholder="请输入receiver_miss" title="请输入receiver_miss" >
                                </div>
                            </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <input type="hidden" name="id" id="id" value="${entity.id}"/>
             <@wtAuth hasPrem="_CHALLENGE:EDIT" ><button type="button" class="btn btn-sm btn-info wt-save">保存</button></@wtAuth>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
<#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('详情');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        //  保存
        $(".wt-save").click(function () {
            if(!$("#layerForm").valid())
                return false;
            WT.wt_confirm('是否保存?', function () {
                WT.wt_ajax_form('${base}/a/challenge/challenge_edit','layerForm',function(data){
                    WT.wt_alert('保存成功',function(){
                        WT.wt_reload_jqtable(parent);
                        WT.wt_close();
                    });
                });
            });
        });
    });
</script>
</body>
</#escape>
</html>
