(function ($) {
    $.fn.extend({
        "wtselect": function (options) {
            options = options || {};
            return this.each(function () {
                if (this.controller)
                    this.controller.options = options;
                else if ($.isPlainObject(options))
                    this.controller = new Controller(this, options);
            });
        }
    });

    var defaultAccept = {
        formatDefaultOption: function () {
            return "<option value=''>--请选择--</option>"
        },
        mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
    };

    var Controller = function (input, options) {
        this.input = input;
        var $input = $(this.input);
        this.options = $.extend({}, {
            formatDefaultOption: options.formatDefaultOption || defaultAccept.formatDefaultOption(),
            formatOptions: options.formatOptions || null,
            valueChangedHandler: options.valueChangedHandler || null,
            cacheable: $input.attr('data-wt-cacheable') == 'false',
            lazyload: $input.attr('data-wt-lazyload') || 'false',
            formatName: $input.attr('data-wt-name'),
            formatValue: $input.attr('data-wt-value'),
            formatParams: $input.attr('data-wt-params') || '',
            formatUrl: $input.attr('data-wt-url') || '',
            paramSelector: $input.attr('data-wt-paramSelector'),
            parentSelector: $input.attr('data-wt-parentSelector'),
            autoValue: $input.attr('data-wt-auto') || '',
            isAutoSet: true,
            paramsProvider: options.paramsProvider || null,
        }, options);
        _setup(this);
    }


    var _setup = function (controller) {
        var $input = $(controller.input);
        var options = controller.options;
        var that = this;

        $input.change(function () {
            $(this).attr('data-wt-select-value', $(this).val());
            if(options.valueChangedHandler != null){
                options.valueChangedHandler($(this));
            }
        });

        if (options.parentSelector != null) {
            //  关联了父组件
            if (options.lazyload == 'false'){
                _resetOptions($input, options);
                _fetchData.apply(that, [controller]);
            }
            $(options.parentSelector).change(function () {
                _resetOptions($input, options);
                _fetchData.apply(that, [controller]);
            });
        } else {
            if (options.lazyload == 'false')
            {
                _resetOptions($input, options);
                _fetchData(controller);
            }

        }

    }

    var _resetOptions = function ($input, options) {
        $input.empty();
        if (options.formatDefaultOption != null) {
            $input.append($(options.formatDefaultOption));
        }
        $input.attr('data-wt-select-value', '');
    }

    var _fetchData = function (controller) {
        var $input = $(controller.input);
        var options = controller.options;
        var url = options.formatUrl;
        var that = this;
        var params = options.formatParams;
        if (options.paramsProvider != null ){
            params = options.paramsProvider($input, options);
        }else if (options.paramSelector != null && options.formatParams != null) {  //  存在参数标签
            params = _formatAttrElement($(options.paramSelector), options.formatParams);
        }
        if (options.cacheable && $.fn.wtselect.cacheData[url + params] != null && $.fn.wtselect.cacheData[url + params] != undefined){
            var resp = $.fn.wtselect.cacheData[url + params];
            _layoutContent.apply(that, [$input, options, resp, that]);
        }
        WT.wt_ajax_formdata_silent(url, params, function (resp) {
            $.fn.wtselect.cacheData[url + params] = resp;
            _layoutContent.apply(that, [$input, options, resp, that]);
        });
    }


    var _layoutContent = function ($input, options, resp, that) {
        if (options.formatName != null && options.formatValue != null) {
            var optionsHtml = _setupOptionsKeyAndValue.apply(that, [resp.data, options]);
            $input.append($(optionsHtml));
        } else if (options.formatOptions != null && resp.data != null && resp.data.length > 0) {
            $input.append($(options.formatOptions(resp.data)));
        } else {
            _errorMsg.apply(that, ['wtselect配置错误,需要定义formatOptions 或 定义data-wt-key及data-wt-value'])
        }
        $input.trigger("change");
    }


    var _setupOptionsKeyAndValue = function (datas, options) {
        var formatName = options.formatName;
        var formatValue = options.formatValue;
        var elementHtml = "";
        for (var index = 0 ; index < datas.length; index ++){
            var key = _formatAttrString(datas[index], formatName);
            var value = _formatAttrString(datas[index], formatValue);
            var checktext = '';
            if (options.isAutoSet && options.autoValue == value){
                checktext = 'selected';
                options.isAutoSet = false;
            }
            elementHtml += "<option value='" + value + "' "+ checktext +" >" + key + "</option>"
        }
        return elementHtml;
    }

    var _formatAttrString = function (obj, string) {
        var keyWarps = string.match(/\{[^\}]+\}/g);
        for (var keyIndex =0 ; keyIndex < keyWarps.length; keyIndex ++){
            var keyWarp = keyWarps[keyIndex];
            var key = keyWarp.substring(1, keyWarp.length - 1);
            var value = (obj[key] == null || obj[key] == undefined) ? "" : obj[key];
            string = string.replace(keyWarp, value);
        }
        return string;
    }

    var _formatAttrElement = function ($element, string) {
        var keyWarps = string.match(/\{[^\}]+\}/g);
        for (var keyIndex =0 ; keyIndex < keyWarps.length; keyIndex ++){
            var keyWarp = keyWarps[keyIndex];
            var key = keyWarp.substring(1, keyWarp.length - 1);
            var value = ($element.attr(key) == null || $element.attr(key) == undefined) ? "" : $element.attr(key);
            string = string.replace(keyWarp, value);
        }
        return string;
    }

    $.fn.wtselect.cacheData = {};

    var _infoMsg = function (text) {
        console.log(text);
    }
    var _errorMsg = function (text) {
        console.error(text);
    }

})(jQuery);