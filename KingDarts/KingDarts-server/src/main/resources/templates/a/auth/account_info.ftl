<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<body class="content-wrapper-body zw ">
    <#include "./a/commons/nav.ftl" />
<style>
    .msgtitle {
        font-size: 20px;
        font-weight: bold;
        text-align: center;
    }

    .frameRight {
        margin-left: 200px;
        /*background: #00a0e9;*/
        position: relative;

    }

    .frameLeft {
        position: absolute;
        width: 150px;
        height: 150px;
        top: 2px;
        /*background: #00a0e9;*/

    }

    .frameLeft img {
        width: 100%;
    }

    .tb10 {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .tbinfo {
        width: 100%;
        text-align: left;
    }

    .tbinfo tr {
        line-height: 30px;
    }

    .title_info {
        color: #FF8F00
    }

    .phone {
        border: 1px solid #fafafa;
    }

    .quit {
        position: absolute;
        height: 30px;
        line-height: 30px;
        width: 105px;
        cursor: pointer;
        color: white;
        text-align: center;
        right: 22px;
        top: 37px;
        background-color: #00c0ef;
        border-color: #00acd6;
    }

</style>
<form method="post" name="layerForm" id="layerForm" action="${base}/a/fc/message/message_view">
    <section class="content">
        <div class="box box-default">
            <div class="box-body">
                <section class="form-horizontal">
                    <div class="">
                        <div class="frameLeft">
                            <img src="${base}/assets/dist/img/logo.png" class="" alt="Logo Image">
                        </div>
                        <div class="frameRight">
                            <div class="">
                                <span style="font-size: 25px;
    font-weight: bold;">${entity.username}</span>
                            </div>
                            <div class="tb10">
                                <span>所属&nbsp;:&nbsp;</span><span>${treeName}</span>
                            </div>
                            <div class="">
                                <span>职务&nbsp;:&nbsp;</span><span>${dutys.duty_names}</span>
                            </div>
                            <div class="quit">退出登录</div>
                            <span class="line"></span>
                            <div>
                                <span class="title_info">账号信息</span>
                            </div>

                            <div class="tb10">
                                <span>账号&nbsp;:&nbsp;</span><span>${entity.username}</span>
                            </div>
                            <div class="tb10">
                                <span>用户名称&nbsp;:&nbsp;</span><span>${entity.realname}</span>
                            </div>
                            <button type="button" class="btn btn-sm btn-info wt-editPass">修改密码</button>
                            <span class="line"></span>
                            <div>
                            <#--<div class="tb10 title_info">个人资料</div>-->
                                <#--<table class="tbinfo">-->
                                    <#--<tr>-->
                                        <#--<td colspan="2">-->
                                            <#--<span>手机号&nbsp;:&nbsp;</span>-->
                                            <#--<input type="number" id="infoPhone" readonly="readonly" name="mobile"-->
                                                   <#--value="${entity.mobile}" style="    line-height: 20px;border: none;">-->
                                            <#--<span id="editPhone" style="color: #4F66FF">修改</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->
                                    <#--<tr>-->
                                        <#--<td >-->
                                            <#--<span>籍贯&nbsp;:&nbsp;</span><span>北京市海淀区</span>-->
                                        <#--</td>-->
                                        <#--<td>-->
                                            <#--<span>毕业院校&nbsp;:&nbsp;</span><span>清华大学</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->
                                    <#--<tr>-->
                                        <#--<td style="width: 30%;min-width: 420px;">-->
                                            <#--<span>学历&nbsp;:&nbsp;</span><span>本科</span>-->
                                        <#--</td>-->
                                        <#--<td>-->
                                            <#--<span>住址&nbsp;:&nbsp;</span><span>天津消防总队</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->
                                    <#--<tr>-->
                                        <#--<td>-->
                                            <#--<span>性别&nbsp;:&nbsp;</span><span>男</span>-->
                                        <#--</td>-->
                                        <#--<td>-->
                                            <#--<span>档案保管所&nbsp;:&nbsp;</span><span>天津消防总队</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->

                                    <#--<tr>-->
                                        <#--<td>-->
                                            <#--<span>出生年月&nbsp;:&nbsp;</span><span>1990年02月</span>-->
                                        <#--</td>-->
                                        <#--<td>-->
                                            <#--<span>紧急联系人&nbsp;:&nbsp;</span><span>李林峰</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->

                                    <#--<tr>-->
                                        <#--<td>-->
                                            <#--<span>政治面貌&nbsp;:&nbsp;</span><span>党员</span>-->
                                        <#--</td>-->
                                        <#--<td>-->
                                            <#--<span>联系电话&nbsp;:&nbsp;</span><span>15910812134</span>-->
                                        <#--</td>-->
                                    <#--</tr>-->

                                <#--</table>-->
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
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>

<div id="setPasswordDiv" class="zw_hide">
    <form id="setPasswordFrm">
        <input type="hidden" name="id" id="uid" value="${entity.id}"/>
        <div class="row">
            <div class="col-sm-12 form-group">
                <label for="passowrd1">原始密码</label>
            <#--<input type="password" name="passowrd" style="display:none;">-->
                <input autocomplete="off" type="password" class="form-control" name="password" id="passowrd1" value=""
                       placeholder="请输入原始密码" title="请输入原始密码" required="">
            </div>

            <div class="col-sm-12 form-group">
                <label for="passowrd1">新密码</label>
            <#--<input type="password" name="passowrd" style="display:none;">-->
                <input autocomplete="off" type="password" class="form-control" name="" id="passowrd2" value=""
                       placeholder="请输入新密码" title="请输入新密码" required="">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 form-group">
                <label for="passowrd2">确认密码</label>
            <#--<input type="password" name="passowrd" style="display:none;">-->
                <input autocomplete="off" type="password" class="form-control" name="password_confirm" id="passowrd3"
                       value="" placeholder="请再次输入新密码" title="请再次输入新密码" required="">
            </div>
        </div>
    </form>
</div>
    <#include "./a/commons/bottom.ftl" />
<script type="text/javascript">
    $("document").ready(function () {
        WT.wt_set_navtitle('查看');   //  设置页面标题
        $(".wt-close").click(function () {  //  关闭
            window.parent.WT.wt_reload_jqtable(window.parent);
            WT.wt_close();

        });
    });

    $('#editPhone').click(function () {
        var phone = $('#infoPhone');
        var self = $(this);
        if (phone.attr("readonly") == "readonly") {
            phone.removeAttr("readonly");
            phone.css("border", "1px solid #ccc");
            self.text('保存');
            return;
        } else {
            $.post('${base}/a/account/editAccount', $('#layerForm').serialize(), function () {
                //   location.href='${base}/a/account/getAccount'
                location.reload();
            })
        }


    })


    function logout() {
        WT.wt_confirm('是否退出?', function () {
            parent.top.location.href = '${base}/a/logout';
        });
    }

    $('.quit').click(function () {
        logout();
    })


    $(document).on('click', '.wt-editPass', function () {
        customopen2('setPasswordDiv', '300px', '500px', function () {


            $("#setPasswordFrm").validate({
                debug: true
            });
            $("#setPasswordFrm").submit();


            if ($('#passowrd2').val()!=$('#passowrd3').val()){
                tips('新密码与确认密码不一致')
                return
            }

            if (!validateFrmError('#setPasswordFrm')) {
                    return
            } else {


                WT.wt_ajax_jsondata('${base}/a/account/account_edit_pasword', $('#setPasswordFrm').serialize(), function (data) {
                    if (data.code == 0) {
                        tips('修改成功')
                        delayCall(2000, function () {
                            layer.closeAll();
                        })
                    } else {
                        tips(data.msg)
                    }


                });


            }


        }, '密码修改');
    })
</script>
</body>

</html>
