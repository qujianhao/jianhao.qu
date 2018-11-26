(function ($) {
    $.fn.extend({
        "wtorgpicker": function (options, args) {
            if (/^(refresh)$/i.test(options)) {  //  操作类型
                return this.each(function(){
                    operate(this.controller, options, args );
                });
            }
            options = options || {};
            return this.each(function () {
                if (this.controller)
                    this.controller.setOptions(options);
                else if ($.isPlainObject(options))
                    this.controller = new Controller(this, options);
            });
        }
    });

    function operate(controller, options, args){
        if ('refresh' === options){
            var $input = $(controller.input);
            var options = controller.options;
            var that = this;
            _reset($input, options);
            _fetchData.apply(that, [controller]);
        }
    }

    var defaultAccept = {
        formatDefaultOption: function () {
            return "<option value=''>--请选择--</option>"
        }
    };

    var Controller = function (input, options) {
        this.input = input;
        var $input = $(this.input);
        this.options = $.extend({}, {
            formatDefaultOption: options.formatDefaultOption || defaultAccept.formatDefaultOption(),
            formatOptions: options.formatOptions || null,
            isReversal: $input.attr('data-wt-reversal') == 'true',
            cacheable: $input.attr('data-wt-cacheable') == 'false',
            isReadonly: $input.attr('data-wt-readonly'),
            formatName: $input.attr('data-wt-name') || 'wt_orgpicker',
            formatValue: $input.attr('data-wt-value'),
            formatParams: $input.attr('data-wt-params') || '',
            formatUrl: $input.attr('data-wt-url') || '',
            paramSelector: $input.attr('data-wt-paramSelector'),
            parentSelector: $input.attr('data-wt-parentSelector'),
        }, options);
        _setup(this);
    }

    var _setup = function (controller) {
        var $input = $(controller.input);
        var options = controller.options;
        var that = this;

        $input.click(function () {
           //   点击
            WT.wt_open_layer(options.formatUrl , {area: ['750px', '480px']}, '选择机构');
        });

        _reset($input, options);
        _fetchData.apply(that, [controller]);
    }

    var _reset = function ($input, options) {
        //$input.empty();
    }

    var _fetchData = function (controller) {
        //var $input = $(controller.input);
        //var options = controller.options;
        //var url = options.formatUrl;
        //var that = this;
        //var params = options.formatParams;
        //if (options.paramSelector != null && options.formatParams != null) {  //  存在参数标签
        //    params = _formatAttrElement($(options.paramSelector), options.formatParams);
        //}
        //WT.wt_ajax_formdata_silent(url, params, function (resp) {
        //    if (resp.data != null) {
        //        var $elementTable = options.isReversal ? _layoutReversalTables.apply(that, [resp.data, controller]) : _layoutTables.apply(that, [resp.data, controller]);
        //        $input.append($elementTable);
        //    } else {
        //        _errorMsg.apply(that, ['wtorgpicker请求失败'])
        //    }
        //});
    }
    var _layoutReversalTables = function (data, controller) {
        var $input = $(controller.input);
        var formatName = controller.options.formatName;
        var parentHeight = 50 + data.colList.length * 35;
        var $elementTable = $('<table class="wt-table wt-orgpicker"></table>');
        var $headerTr = $('<tr class="wt-table-header"><th>' + data.title + '</th></tr>');
        var readonlyText = controller.options.isReadonly ? 'disabled' : '';
        var colSize = data.rowList.length;
        for (var colIndex in data.rowList) {
            var colData = data.rowList[colIndex];
            $headerTr.append($('<th>' + colData.name + '</th>'))
        }
        $elementTable.append($headerTr);
        for (var rowIndex in data.colList) {
            var $rowTr = $('<tr class="wt-table-row"></tr>');
            var rowData = data.colList[rowIndex];
            $rowTr.append($('<td><input type="hidden" name="' + formatName + '" value="'+ rowData.key +'" >' + rowData.name + '</td>'));
            for (var i = 0; i < colSize; i++) {
                var key = rowData.key;
                var colValue = data.tableData[key].value;
                var rowValue = data.rowList[i].value;
                var checkStr = (parseInt(colValue) & parseInt(rowValue)) > 0 ? 'checked' : '';
                if (controller.options.isReadonly){
                    if ((parseInt(colValue) & parseInt(rowValue)) > 0 && controller.options.isReadonly){
                        $rowTr.append($('<td><i class="fa fa-check-square"></i></td>'));
                    }else{
                        $rowTr.append($('<td><input type="checkbox" value="'+formatName + '_' + rowValue + '" ' + checkStr + ' ' + readonlyText + ' ></td>'));
                    }
                }else{
                    $rowTr.append($('<td><input type="checkbox" name="'+formatName + '_' + key+'" value="' + rowValue + '" ' + checkStr + ' ' + readonlyText + ' ></td>'));
                }

            }
            $elementTable.append($rowTr);
        }
        if ($input.parent() != null) {
            $input.parent().height( parentHeight + 'px');
        }
        return $elementTable;
    }

    var _layoutTables = function (data, controller) {
        var $input = $(controller.input);
        var formatName = controller.options.formatName;
        var parentHeight = 50 + data.rowList.length * 35;
        var $elementTable = $('<table class="wt-table wt-orgpicker"></table>');
        var $headerTr = $('<tr class="wt-table-header"><th>' + data.title + '</th></tr>');
        var readonlyText = controller.options.isReadonly ? 'disabled' : '';
        var colSize = data.colList.length;
        for (var colIndex in data.colList) {
            var colData = data.colList[colIndex];
            $headerTr.append($('<th><input type="hidden" name="' + formatName + '" value="'+ colData.key +'" >' + colData.name + '</th>'))
        }
        $elementTable.append($headerTr);
        for (var rowIndex in data.rowList) {
            var $rowTr = $('<tr class="wt-table-row"></tr>');
            var rowData = data.rowList[rowIndex];
            $rowTr.append($('<td>' + rowData.name + '</td>'));
            for (var i = 0; i < colSize; i++) {
                var key = data.colList[i].key;
                var colValue = data.tableData[key].value;
                var rowValue = data.rowList[rowIndex].value;
                var checkStr = (parseInt(colValue) & parseInt(rowValue)) > 0 ? 'checked' : '';
                if (controller.options.isReadonly){
                    if ((parseInt(colValue) & parseInt(rowValue)) > 0 && controller.options.isReadonly){
                        $rowTr.append($('<td><i class="fa fa-check-square"></i></td>'));
                    }else{
                        $rowTr.append($('<td><input type="checkbox" value="'+formatName + '_' + rowValue + '" ' + checkStr + ' ' + readonlyText + ' ></td>'));
                    }
                }else{
                    $rowTr.append($('<td><input type="checkbox" name="'+formatName + '_' + key+'" value="' + rowValue + '" ' + checkStr + ' ' + readonlyText + ' ></td>'));
                }
            }
            $elementTable.append($rowTr);
        }
        if ($input.parent() != null) {
            $input.parent().height( parentHeight + 'px');
        }
        return $elementTable;
    }

    var _formatAttrString = function (obj, string) {
        var keyWarps = string.match(/\{[^\}]+\}/g);
        for (var keyIndex in keyWarps) {
            var keyWarp = keyWarps[keyIndex];
            var key = keyWarp.substring(1, keyWarp.length - 1);
            var value = (obj[key] == null || obj[key] == undefined) ? "" : obj[key];
            string = string.replace(keyWarp, value);
        }
        return string;
    }

    var _formatAttrElement = function ($element, string) {
        var keyWarps = string.match(/\{[^\}]+\}/g);
        for (var keyIndex in keyWarps) {
            var keyWarp = keyWarps[keyIndex];
            var key = keyWarp.substring(1, keyWarp.length - 1);
            var value = ($element.attr(key) == null || $element.attr(key) == undefined) ? "" : $element.attr(key);
            string = string.replace(keyWarp, value);
        }
        return string;
    }

    $.fn.wtorgpicker.cacheData = {};

    var _infoMsg = function (text) {
        console.log(text);
    }
    var _errorMsg = function (text) {
        console.error(text);
    }

})(jQuery);