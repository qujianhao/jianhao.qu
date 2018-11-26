(function($) {
    'use strict';

    // ajax请求
    function wt_ajax_form(urlString, formId, success, failure) {
        $.ajax({
            url : urlString,
            data : $("#" + formId).serialize(),
            type : 'post',
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (success) {
                        success(data);
                    }
                } else {
                    if (failure) {
                        failure(data);
                    } else if (data.code == 1) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg(data.msg);
                    }
                }
            },
            error : function(data) {
                if (failure) {
                    failure(data);
                }
            }
        });
    }

    // ajax请求
    function wt_ajax_formdata(urlString, formData, success, failure) {
        wt_ajax_formdata_any(urlString, formData, success, failure, 'post');
    }
    // ajax请求
    function wt_ajax_formdata_get(urlString, formData, success, failure) {
        wt_ajax_formdata_any(urlString, formData, success, failure, 'get');
    }

    // ajax请求
    function wt_ajax_formdata_any(urlString, formData, success, failure , method) {
        var layerIndex = layer.load(0);
        $.ajax({
            url : urlString,
            data : formData,
            type : method,
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (success) {
                        success(data);
                    }
                    setTimeout(function() {
                        layer.close(layerIndex);
                    }, 500);
                } else {
                    if (failure) {
                        failure(data);
                        layer.close(layerIndex);
                    } else if (data.code == 1) { // 失败
                        layer.close(layerIndex);
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.close(layerIndex);
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.close(layerIndex);
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg(data.msg);
                    }
                    setTimeout(function() {
                        layer.close(layerIndex);
                    }, 1000);
                }
            },
            error : function(data) {
                setTimeout(function() {
                    layer.close(layerIndex);
                }, 1000);
            }
        });
    }

    // ajax请求
    function wt_ajax_formdata_silent(urlString, formData, success, failure ) {
        $.ajax({
            url : urlString,
            data : formData,
            type : 'post',
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (success) {
                        success(data);
                    }
                } else {
                    if (failure) {
                        failure(data);
                    } else if (data.code == 1) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg('请求失败: ' + data.msg);
                    }
                }
            },
            error : function(data) {
                layer.msg('发生错误: ' + data);
            }
        });
    }

    // ajax请求
    function wt_ajax_jsonobject(urlString, jsonObject, success, failure) {
        $.ajax({
            url : urlString,
            data : JSON.stringify(jsonObject),
            type : 'post',
            cache : false,
            dataType : 'json',
            contentType: "application/json",
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (success) {
                        success(data);
                    }
                } else {
                    if (failure) {
                        failure(data);
                    } else if (data.code == 1) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg(data.msg);
                    }
                }
            },
            error : function(data) {
                if (failure) {
                    failure(data);
                }
            }
        });
    }


    // ajax请求
    function wt_ajax_jsondata(urlString, jsonData, success, failure) {
        $.ajax({
            url : urlString,
            data : jsonData,
            type : 'post',
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (success) {
                        success(data);
                    }
                } else {
                    if (failure) {
                        failure(data);
                    } else if (data.code == 1) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg(data.msg);
                    }
                }
            },
            error : function(data) {
                if (failure) {
                    failure(data);
                }
            }
        });
    }

    function wt_ajax_jsondata_method(options){
        var opt_urlString = options.urlString;
        var opt_jsonData = options.jsonData;
        var opt_success = options.success;
        var opt_failure = options.failure;
        var delay = options.delay || 0;
        var opt_method = options.method || "POST";

        $.ajax({
            url : opt_urlString,
            data : opt_jsonData,
            type : opt_method,
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.code == 0) { // 成功
                    if (opt_success) {
                        setTimeout(opt_success(data), delay);
                    }
                } else {
                    if (opt_failure) {
                        setTimeout(opt_failure(data), delay);
                    } else if (data.code == 1) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 2) { // 失败
                        layer.msg(data.msg);
                    } else if (data.code == 1000) { // 未登录
                        layer.msg("请先登录");
                    } else { // 其他异常
                        layer.msg(data.msg);
                    }
                }
            },
            error : function(data) {
                if (opt_failure) {
                    setTimeout(opt_failure(data), delay);
                }
            }
        });
    }

    //	去掉字符串前后的空格
    function wt_trim(str){
        return str.replace(/(^\s*)|(\s*$)/g, '');
    };


    var reg_cn = /[\u4e00-\u9fa5]/;
    var reg_id_card = /(^\d{15}$)|(^\d{17}([0-9]|X|x)$)/;
    var reg_mobile =  /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    var reg_restaurant_name = /[\u4e00-\u9fa5a-zA-Z0-9_]{4,15}$/;
    var reg_password = /[a-zA-Z0-9_]{4,18}$/;
    var reg_email = /^[0-9\w\_\-\.]+@[\w\-\.]+(\.\w+)+$/;
    var reg_username = /[a-zA-Z0-9_]{4,15}$/;

    function wt_is_email(email){
        if (reg_email.test(email)){
            return true;
        }
        return false;
    }

    function wt_is_mobile(mobile){
        if (reg_mobile.test(mobile)){
            return true;
        }
        return false;
    }
    //	判断是否为空
    function wt_is_empty(str){
        return (str == null || wt_trim(str).length <= 0);
    }

    //	弹出警告框
    function wt_alert(content,func) {
        layer.alert(content,{scrollbar: false},func);
    }

    //	询问框
    function wt_confirm(title,doneFunc,cancelFunc){
        layer.confirm( title, {
            btn: ['确定','取消'] //按钮
        }, function(index){
            if (doneFunc != null) {
                doneFunc();
            }
            layer.close(index);
        }, function(){
            if (cancelFunc != null) {
                cancelFunc();
            }
        });
    }

    //	关闭弹出曾
    function wt_close() {
        parent.layer.close(parent.layer.getFrameIndex(window.name));
    }

    function wt_serializeJSONObject(formId){
        var formData = $("#" + formId ).serializeArray();
        var formJSON = new Object();
        for (var index = 0; index < formData.length; index++) {
            var item = formData[index];
            var name = item.name;
            var value = item.value;

            formJSON[name] = value;
        }
        return formJSON;
    }

    function wt_open_layer(url,params,title,o){
        title = title || "详情";
        var opt = o || {}
        opt.area = opt.area || ['90%', '90%'];
        layer.open({
            type: 2,
            title: title,
            shadeClose: false,
            shade: 0.5,
            scrollbar:false,
            area: opt.area,
            content: getUrl(url, params)
        });
    }

    function wt_open_layer_html(domId, params, title) {
        title = title || "详情";
        layer.open({
            type: 1,
            title: title,
            shadeClose: false,
            shade: 0.5,
            scrollbar: false,
            area: params,
            content: $('#' + domId)
        });
    }

    function wt_open_fullscreen(url,params,title){
        if (params != null && params != undefined)
            url = getUrl(url, params)
        var index = layer.open({
            type: 2,
            closeBtn: 0,
            title: false,
            content: url
        });
        layer.full(index);
    }

    function wt_set_navtitle(title){
        var parentTitle = WT.wt_ss_get(WT.Constants.PARENT_TITLE) || "";
        $("#wt-content-header").text(parentTitle + " " + title);
    }

    function wt_format_publish(cellvalue, options, rowObject) {
        if (cellvalue == 1) {
            return "<font style=\"color:#1ab394;font-weight: bold;\">启用</font>";
        } else if (cellvalue == 0) {
            return "<font style=\"color:#ed5565;font-weight: bold;\">停用</font>";
        } else {
            return "-";
        }
    }

    function wt_jqtable(urlString, colNames, colModels, params, serialzeFormData){
        if (serialzeFormData == null)
            serialzeFormData = WT.wt_serializeJSONObject("frameForm");
        wt_jqtable_params(urlString, colNames, colModels, $.extend({}, params), serialzeFormData);
    }

    function wt_jqtable_multi(urlString, colNames, colModels, params, serialzeFormData){
        if (serialzeFormData == null)
            serialzeFormData = WT.wt_serializeJSONObject("frameForm");
        wt_jqtable_params(urlString, colNames, colModels, $.extend({multiselect: true}, params), serialzeFormData);
    }

    function wt_jqtable_celledit(urlString, colNames, colModels,params, serialzeFormData){
        if (serialzeFormData == null)
            serialzeFormData = WT.wt_serializeJSONObject("frameForm");
        wt_jqtable_params(urlString, colNames, colModels, params, serialzeFormData);
    }

    function wt_jqtable_scroll(urlString, colNames, colModels, serialzeFormData){
        if (serialzeFormData == null)
            serialzeFormData = WT.wt_serializeJSONObject("frameForm");
        wt_jqtable_params(urlString, colNames, colModels, {autowidth: false, shrinkToFit: false, autoScroll: true, multiselect: false}, serialzeFormData);
    }


    function wt_jqtable_selectids(tableName){
        tableName = tableName || '#wt_table_list';
        var ids = $(tableName).jqGrid('getGridParam', 'selarrrow');
        if (ids == null || ids.length <= 0){
            return [];
        }
        var device_ids = [];
        console.log('ids -> ', ids);
        for (var i = 0 ; i < ids.length ; i ++ ){
            var rowData = $(tableName).jqGrid("getRowData", ids[i]);
            console.log('rowData -> ', rowData)
            device_ids.push(rowData.device_id);
        }
        return device_ids;
    }

    function wt_jqtable_rowdata(rowId, tableName){
        tableName = tableName || '#wt_table_list';
        return $(tableName).jqGrid("getRowData", rowId);
    }



    function wt_jqtable_btns(rowId){
        var result = "";
        if ($("#batchViewBtn").length > 0 )
            result += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-info wt-view'>查看</a>";
        if ($("#batchEditBtn").length > 0 )
            result += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-info wt-edit'>编辑</a>";
        if ($("#batchEditBtn").length > 0 )
            result += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-info wt-state'>发布</a>";
        if ($("#batchDeleteBtn").length > 0 )
            result += "<a href='javascript:void(0);' data-rowId='"+rowId+"' class='wt-a wt-danger wt-delete'>删除</a>";
        return result;
    }

    function wt_jqtable_params(urlString, colNames, colModels, params, serialzeFormData){
        params = $.extend({
            postData: serialzeFormData,
            url: urlString,
            colNames: colNames,
            colModel: colModels,
            autoencode: true,
            mtype : 'POST',
            datatype: "json",
            pager: "#wt_pager_list",
            height: 500,
            autowidth: true,
            shrinkToFit: true,
            autoScroll: false,
            rowNum: 20,
            rowList: [20, 50, 100],
            viewrecords: true,
            multiselect: false
        }, params);
        $("#wt_table_list").jqGrid(params);
    }

    function wt_reload_jqtable(owner, tableId){
        tableId = tableId || "wt_table_list";
        owner.$("#" + tableId).trigger('reloadGrid');
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

    function wt_time2str(timestamp){
        return new Date(parseFloat(timestamp)).toLocaleString();
    }

    function wt_ss_set(key, value){
        localStorage.setItem(key, value);
    }

    function wt_ss_get(key){
        return localStorage.getItem(key);
    }

    function wt_has_view(prem){
        return ($("#PREM_" + prem + "_VIEW").length > 0);
    }
    function wt_has_edit(prem){
        return ($("#PREM_" + prem + "_EDIT").length > 0);
    }
    function wt_has_add(prem){
        return ($("#PREM_" + prem + "_ADD").length > 0);
    }
    function wt_has_delete(prem){
        return ($("#PREM_" + prem + "_DELETE").length > 0);
    }
    function wt_formatMoney(number, places, symbol, thousand, decimal) {
        number = number || 0;
        places = !isNaN(places = Math.abs(places)) ? places : 2;
        symbol = symbol !== undefined ? symbol : "";
        thousand = thousand || ",";
        decimal = decimal || ".";
        var j = 0;
        var negative = number < 0 ? "-" : "",
            i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
            j = (j = i.length) > 3 ? j % 3 : 0;
        return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
    }
    function wt_DX(n) {
    	 
        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
          return "数据非法";
        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
          n += "00";
        var p = n.indexOf('.');
        if (p >= 0)
          n = n.substring(0, p) + n.substr(p+1, 2);
          unit = unit.substr(unit.length - n.length);
        for (var i=0; i < n.length; i++)
          str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
        return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
    }
    function wt_download(url, data, method){
        if( url && data ){
            data = typeof data == 'string' ? data : $.param(data);
            var inputs = '';
            $.each(data.split('&'), function(){
                var pair = this.split('=');
                inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
            }); // request发送请求
            $('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
        };
    };
    
    function formatDate(time) {
        var date = new Date(time);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + date.toTimeString().substr(0, 8);
    }

    window['WT'] = window['WT'] || {};
    window['WT']['wt_ajax_form'] = wt_ajax_form;
    window['WT']['wt_ajax_formdata'] = wt_ajax_formdata;
    window['WT']['wt_ajax_formdata_get'] = wt_ajax_formdata_get;
    window['WT']['wt_ajax_formdata_silent'] = wt_ajax_formdata_silent;
    window['WT']['wt_ajax_jsondata'] = wt_ajax_jsondata;
    window['WT']['wt_ajax_jsondata_method'] = wt_ajax_jsondata_method;
    window['WT']['wt_ajax_jsonobject'] = wt_ajax_jsonobject;
    window['WT']['wt_is_empty'] = wt_is_empty;
    window['WT']['formatDate'] = formatDate;

    window['WT']['wt_alert'] = wt_alert;
    window['WT']['wt_confirm'] = wt_confirm;
    window['WT']['wt_close'] = wt_close;
    window['WT']['wt_serializeJSONObject'] = wt_serializeJSONObject;
    window['WT']['wt_open_layer'] = wt_open_layer;
    window['WT']['wt_open_layer_html'] = wt_open_layer_html;
    window['WT']['wt_open_fullscreen'] = wt_open_fullscreen;
    window['WT']['wt_set_navtitle'] = wt_set_navtitle;

    window['WT']['wt_format_publish'] = wt_format_publish;
    window['WT']['wt_is_email'] = wt_is_email;
    window['WT']['wt_is_mobile'] = wt_is_mobile;
    window['WT']['wt_jqtable'] = wt_jqtable;
    window['WT']['wt_jqtable_multi'] = wt_jqtable_multi;
    window['WT']['wt_jqtable_celledit'] = wt_jqtable_celledit;
    window['WT']['wt_reload_jqtable'] = wt_reload_jqtable;
    window['WT']['wt_jqtable_scroll'] = wt_jqtable_scroll;
    window['WT']['wt_jqtable_selectids'] = wt_jqtable_selectids;
    window['WT']['wt_jqtable_rowdata'] = wt_jqtable_rowdata;
    window['WT']['wt_time2str'] = wt_time2str;
    window['WT']['wt_ss_set'] = wt_ss_set;
    window['WT']['wt_ss_get'] = wt_ss_get;
    window['WT']['wt_has_view'] = wt_has_view;
    window['WT']['wt_has_edit'] = wt_has_edit;
    window['WT']['wt_has_add'] = wt_has_add;
    window['WT']['wt_has_delete'] = wt_has_delete;
    window['WT']['wt_formatMoney'] = wt_formatMoney;
    window['WT']['wt_DX'] = wt_DX;
    window['WT']['wt_download'] = wt_download;
    
    window['WT']['Constants'] = {
        PARENT_TITLE : 'PARENT_TITLE'
    };

})(jQuery);

//货币化数字
Number.prototype.formatMoney = function (places, symbol, thousand, decimal) {
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "$";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var number = this,
        negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};
//数组包含对象
Array.prototype.contains = function ( needle ) {
	for (i in this) {
		if (this[i] == needle) return true;
	}
	return false;
}
//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2015-07-02 08:09:04.423   
//(new Date()).Format("yyyy-M-d H:m:s.S")      ==> 2015-7-2 8:9:4.18   
Date.prototype.format = function(fmt)   
{ 
	var o = {   
			"M+" : this.getMonth()+1,                 //月份   
			"d+" : this.getDate(),                    //日   
			"H+" : this.getHours(),                   //小时   
			"m+" : this.getMinutes(),                 //分   
			"s+" : this.getSeconds(),                 //秒   
			"q+" : Math.floor((this.getMonth()+3)/3), //季度   
			"S"  : this.getMilliseconds()             //毫秒   
	};   
	if(/(y+)/.test(fmt))   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
		if(new RegExp("("+ k +")").test(fmt))   
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;   
} 
//格式化浮点数
function fomatFloat(src,pos){
	return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}
//比较时间大小
function compareDate(beginDate,endDate){
	if(!beginDate || !endDate){
		return true;
	}
	var d1 = new Date(beginDate.replace(/\-/g, "\/"));  
	var d2 = new Date(endDate.replace(/\-/g, "\/"));  

	if(beginDate!=""&&endDate!=""&&d1 >=d2){
		WT.wt_alert('开始时间不能大于结束时间！');
		//alert("开始时间不能大于结束时间！");  
		return false;  
	}
	return true;
}
//生成从minNum到maxNum的随机数
function randomNum(minNum,maxNum){ 
    switch(arguments.length){ 
        case 1: 
            return parseInt(Math.random()*minNum+1,10); 
        break; 
        case 2: 
            return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10); 
        break; 
            default: 
                return 0; 
            break; 
    } 
}
//替换全部
String.prototype.replaceAll = function(rgExp, replaceText){
    return this.replace(new RegExp(rgExp,'gm'), replaceText);
};
