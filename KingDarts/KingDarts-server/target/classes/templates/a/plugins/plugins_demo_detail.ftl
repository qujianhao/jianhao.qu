<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body class="content-wrapper-body wt-demo-content">
<form method="post" name="layerForm" id="layerForm" action="${base}/a/plugins/plugins_edit">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="false">详情页内容示例</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab_1">

                            <section class="form-horizontal">
                                <h4 class="page-header">入库单信息</h4>

                                <div class="row">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test1">文本框</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="test1" id="test1" value=""
                                                   placeholder="请输入测试字段1">
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test2">下拉列表</label>

                                        <div class="col-sm-9">
                                            <select class="form-control" name="test2" id="test2" data-wt-auto="1"
                                                    required>
                                                <option value="">---请选择---</option>
                                                <option value="1">选中</option>
                                                <option value="2">未选中</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test3">日期选择</label>

                                        <div class="col-sm-9">
                                            <div class="input-group">
                                                <input type="text" name="test3" id="test3" placeholder="请输入测试字段3"
                                                       class="form-control wt-datepicker" value="">

                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12 form-group">
                                        <label class="col-sm-1 wt-revise-col-sm-1 control-label" for="test4">大文本</label>

                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="test4" id="test4"
                                            >测试字段4</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test4">附件上传</label>

                                        <div class="col-sm-9">
                                            <div id="user_other_documents" data-wt-upload-name="user_other_documents"
                                                 data-wt-upload-preview="group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png,group1/M00/00/5C/eSqQlFnrSq-AeY4pAAF3lq9jDZE901.png"
                                                 data-wt-upload-preview-names="xxxx.jpg,xxxx.png"></div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                            <section class="form-horizontal">
                                <h4 class="page-header">入库产品信息</h4>

                                <div class="row">
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label">
                                            <i class="wt-">*</i>单选</label>

                                        <div class="col-sm-9 radio">
                                            <label>
                                                <input type="radio" name="test15" id="test15_1" value="1" title="请选择">
                                                男
                                            </label>
                                            <label>
                                                <input type="radio" name="test15" id="test15_2" value="2">
                                                女
                                            </label>
                                        </div>

                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test16"><i
                                                class="wt-">*</i>多选</label>

                                        <div class="col-sm-9 ">
                                            <div class="checkbox">
                                                <label> <input name="test16" type="checkbox" checked>是否新品</label>
                                                <label> <input name="test16" type="checkbox">是否推荐</label>
                                                <label> <input name="test16" type="checkbox">是否置顶</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test17">测试字段17</label>

                                        <div class="col-sm-9"><input type="text" class="form-control" name="test17"
                                                                     id="test17" value="" placeholder="请输入测试字段17"></div>
                                    </div>
                                    <div class="col-sm-4 form-group">
                                        <label class="col-sm-3 control-label" for="test18"><i
                                                class="wt-">*</i>测试字段18</label>

                                        <div class="col-sm-9"><input type="text" class="form-control" name="test18"
                                                                     id="test18" value="" placeholder="请输入测试字段18"
                                        ></div>
                                    </div>
                                </div>
                            </section>

                            <section class="form-horizontal">
                                <h4 class="page-header">入库表格</h4>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <table class="wt-table">
                                            <tr class="wt-table-header">
                                                <th>产品编码</th>
                                                <th>产品名称</th>
                                                <th>计量单位</th>
                                                <th>型号</th>
                                                <th>数量</th>
                                                <th>操作</th>
                                            </tr>
                                            <tr class="wt-table-row">
                                                <td>
                                                    <select class="form-control" name="test20" id="test20_0">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select class="form-control">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" value="" placeholder="请输入"
                                                    >
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" value="" placeholder="请输入"
                                                    >
                                                </td>
                                                <td>
                                                    <select class="form-control">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td class="text-center">
                                                    <a href='javascript:void(0);' data-id=""
                                                       class='wt-a wt-info wt-view'>查看</a>
                                                    <a href='javascript:void(0);' data-id=""
                                                       class='wt-a wt-danger wt-delete'>删除</a>
                                                </td>
                                            </tr>
                                            <tr class="wt-table-row">
                                                <td>
                                                    <select class="form-control" name="test20" id="test20_1">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select class="form-control">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" value="" placeholder="请输入">
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" value="" placeholder="请输入">
                                                </td>
                                                <td>
                                                    <select class="form-control">
                                                        <option value="">---请选择---</option>
                                                        <option value="1000000">1000000</option>
                                                        <option value="1000001">1000001</option>
                                                    </select>
                                                </td>
                                                <td class="text-center">
                                                    <a href='javascript:void(0);' data-id=""
                                                       class='wt-a wt-info wt-view'>查看</a>
                                                    <a href='javascript:void(0);' data-id=""
                                                       class='wt-a wt-danger wt-delete'>删除</a>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                            </section>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>

    <br/>

    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-validate">验证表单</button>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
</form>
    <#include "./a/commons/bottom.ftl" />
    <#include "./a/commons/upload.ftl" />
<script type="text/javascript">
    $("document").ready(function () {

        //  日期控件用
        $('.wt-datepicker').datetimepicker({autoclose: true, language: 'zh-CN', minView: 2, format: 'yyyy-mm-dd'});

        //  验证规则可以使用javascript设置
        $("#layerForm").validate({
            rules: {
                test1: {required: true, minlength: 10},
                test20: {required: true},
                test4: {required: true},
                test15: {required: true}
            }
        });

        //  上传控件用
        $("#user_other_documents").WTBlankUploader({
            accept: wtblankuploader_accept,
            createUploadBtn: wtblankuploader_createUploadBtn,
            createUploadItem: wtblankuploader_createUploadItem
        });

        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });

        //  点击验证
        $(".wt-validate").click(function () {
            if (!$("#layerForm").valid()) {
                WT.wt_alert('验证结束，表单验证未通过');
                return;
            }
            WT.wt_alert('验证结束，表单验证通过');
        });

    });
</script>
</body>
</#escape>
</html>
