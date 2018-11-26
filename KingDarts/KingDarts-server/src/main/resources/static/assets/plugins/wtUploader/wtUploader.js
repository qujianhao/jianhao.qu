(function ($) {

    $.fn.extend({
        'WTUploader': function(option, args, fire){
            if (/^(preview|fileNames)$/i.test(option)) {  //  操作类型
                return this.each(function(){
                    operate(this, option, args );
                });
            }
            var items = this.each(function(){
                if(this.controller)
                    this.controller.setOption(option, args);
                else if($.isPlainObject(option))
                    this.controller = new Controller(this, option);
            });
            return items;
        }
    });

    function operate(input, option, args){
        if ('preview' === option){
            if (WTUP.isServerConfigLoaded)
                input.controller.preview(args);
            else{

            }

        }else if ('fileNames' == option){
            if (WTUP.isServerConfigLoaded)
                input.controller.fileNames(args);
            else{

            }
        }
    }


    var Controller = function(input, option){

        var flashPath = WTUP.flashPath;
        var fileName = $(input).attr('data-wt-upload-name') || "file";
        var previewNames = $(input).attr('data-wt-upload-preview') || "";

        this.defaultAccept = {extensions: 'jpg,jpeg,png,gif,bmp',mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'};
        this.input = input;
        this.option = $.extend(false, {
            // style
            'width': 1,                                     // number, string 'auto'
            // url
            'imgUrlString': '',                             // string
            'baseUrlString': WTUP.basePath,                            // string
            'checkUrl': '../../../wtuploader/controller?action=checks',                     // string
            'uploadUrl': '../../../wtuploader/controller?action=uploads',               // string
            'flashPath': flashPath,                         // string
            // data
            'accept': this.defaultAccept,                   // function
            'fileName': fileName,                           // string
            'previewNames': previewNames,                   // string
            'minItems': 1,                                  // number
            'maxItems': 20,                                 // number
            'runtimeOrder' : 'html5, flash',                 // string
            // event
            'matchHandler': null,                           // function
            'emphasisHandler': null,                        // function
            'createItemHandler': null,                      // function
            'onFileDeleteHandler': null,                  // function
            'onLoadFinishedHandler': null,                          // function
            'uploadFinishedHandler': null,                  // function
            // behavior
            'async': false,                                 // bool
            // debug
            'onerror': null                                 // function
        }, option);

        if (!WTUP.isServerConfigLoaded) {
            var that = this;
            _loadServerConfig(function(){
                _setup.apply(that, [input]);
            },that);
        }else {
            _setup.apply(this, [input]);
        }
    };


    var _loadServerConfig = function(callback, controller){
        var configUrl = WTUP.configUrl;
        var isJsonp = WTUP.utils.isCrossDomainUrl(configUrl);
        var that = this;
        WTUP.ajax.request(configUrl,{
            'method': 'GET',
            'dataType': isJsonp ? 'jsonp':'',
            'onsuccess':function(r){
                try {
                    var config = isJsonp ? r:eval("("+r.responseText+")");
                    WTUP.fileRootPath = config.fileRootPath || WTUP.fileRootPath;
                    WTUP.fileServerUrl = config.fileServerUrl || WTUP.fileServerUrl;
                    WTUP.isServerConfigLoaded = true;
                    WTUP.maxSize = config.maxSize || WTUP.maxSize;
                    callback();
                    _afterServerConfigLoaded.apply(that, [controller]);
                } catch (e) {
                    console.log('获取配置失败', e);
                }
            },
            'onerror':function(){
                console.log('获取配置失败!');
            }
        });
    };

    var _afterServerConfigLoaded = function (controller) {
        if (controller.option.previewNames != null
            && controller.option.previewNames.length > 0){
            var previewArray = controller.option.previewNames.split(',');
            controller.preview(previewArray);
        }
    };


    // ------- Private Method Here -------------
    var _setup = function(input){
    	
        var $uploadContentView = $("<div class=\"btn-wt-upload\"></div>");
        $(input).append($uploadContentView);

        var that = this;
        var _uploader = this.uploader();
        _uploader.addButton({
            id: $uploadContentView
        });

        var progressContentView = $(input).find('.webuploader-pick').first();
        progressContentView.append("<p class=\"upload-progress\" ></p>");
        var progressView = $(input).find('.upload-progress').first();

        // 当有文件被添加进队列的时候
        _uploader.on( 'fileQueued', function( file ) {
            _uploader.md5File( file ).then(function(val) {
                _checkMd5.apply(that, [_uploader, file ,val]);
            });
        });

        _uploader.on( 'uploadStart', function( file ) {
            progressView.show();
        });

        // 文件上传过程中创建进度条实时显示。
        _uploader.on( 'uploadProgress', function( file, percentage ) {
            var progressVal = Math.min(percentage * 100, 100);
            progressView.html(progressVal + '%');
            console.log(progressVal + '%')
        });

        _uploader.on( 'uploadError', function( file ) {
        	
        });

        _uploader.on('uploadSuccess', function(file ,resp){
            _uploader.md5File( file ).then(function(val) {
                try {
                    if (file.size > WTUP.maxSize){
                        WT.wt_alert('上传单文件需要小于' + Math.floor(WTUP.maxSize / 1024 / 1024 * 10) / 10 + 'M');
                        _uploader.reset();
                        return;
                    }
                    var result = resp;
                    if (result.url == null || result.url == undefined){
                        WT.wt_alert(result.state || '上传发生错误，请稍后再试或联系管理员');
                        _uploader.reset();
                    }else{
                        _appendFile.apply(that, [result.url, file]);
                        _uploader.reset();
                    }
                }catch (ex){
                    _uploader.reset();
                }
            });
        });

        _uploader.on( 'uploadComplete', function( file ) {
            progressView.hide();
        });

        if (this.option.previewNames != null && this.option.previewNames.length > 0){
            var previewArray = this.option.previewNames.split(',');
            this.preview(previewArray);
        }

        if (this.option.onLoadFinishedHandler != null){
            this.option.onLoadFinishedHandler();
        }

    };


    var _checkMd5 = function (_uploader, file, val) {
        var that = this;
        $.ajax({
            url : this.option.baseUrlString + this.option.checkUrl,
            data : {md5s : val},
            type : 'post',
            cache : false,
            dataType : 'json',
            success : function(resultJSON) {
                try {
                    var result = resultJSON;
                    if (result.code == 1) {
                        _uploader.reset();
                        _error.apply(that, ['上传失败,请检查网络稍候再试']);
                        WT.wt_alert(result.msg || '上传失败,请检查网络稍候再试');
                    }
                    if(result.code == 0 && result.data[val] != null){
                        _appendFile.apply(that, [result.data[val], file]);
                        _uploader.reset();
                    }else if (result.code == 0) {
                        _uploader.upload(file);
                    }else{
                        _uploader.reset();
                        _error.apply(that, ['上传失败,请检查网络稍候再试']);
                    }
                }catch (ex){
                    _uploader.reset();
                    _error.apply(that, ['上传失败,请检查网络稍候再试']);
                }
            },
            error : function(result) {
                _uploader.reset();
                _error.apply(that, ['上传失败']);
            }
        });
    };


    var _appendFile = function (imgSrc, file) {

        if( this.option.uploadFinishedHandler != null){
            this.option.uploadFinishedHandler(imgSrc, this.input);
        }

        var currentFileExt = (file == null || file.ext == null) ? "" : file.ext;
        var currentFileName = (file == null || file.name == null) ? "" : file.name;

        var minItems = this.option.minItems;
        var maxItems = this.option.maxItems;

        var fileName = this.option.fileName;
        var contentView = $(this.input);
        var src = WTUP.fileServerUrl +  imgSrc;
        var fileId = "file_" + new Date().getTime() + "_" + parseInt(Math.random() * 1000 , 10);
        var displayItem = '<img src="'+src+'">';
        if (this.defaultAccept.extensions.indexOf(currentFileExt) < 0){
            var file_ext_cls = _fileExt.apply(this, [currentFileExt]);
            displayItem = '<div class="filetypeicon"><i class="file-ico '+file_ext_cls+'"></i></div><p>'+currentFileExt+'</p><p>'+currentFileName+'</p>';
        }
        var $item = $('<div class="upload-item" id="'+fileId+'"><div class="upload-div-img">' + displayItem +
            '</div><div class="upload-div-panel">' +
            '<span class="cancel">删除</span><span class="next">后移</span>' +
            '<span class="prev">前移</span>' +
            '<input type="hidden" name="'+fileName+'" value="'+imgSrc+'"></div></div>');
        contentView.append($item);


        $item.on( 'mouseenter', function() {
            $(this).find(".upload-div-panel").first().stop().animate({height: 30});
        }).on( 'mouseleave', function() {
            $(this).find(".upload-div-panel").first().stop().animate({height: 0});
        });

        var that = this.option;
        $("#" + fileId).find(".cancel").click(function(){
            $item.remove();
            if (contentView.children(".upload-item").size() < maxItems) {
                contentView.find(".btn-wt-upload").first().show();
            }
            if (that.onFileDeleteHandler != null){
                that.onFileDeleteHandler(imgSrc);
            }
        });
        $("#" + fileId).find(".next").click(function(){
            if ($item.next(".upload-item").attr("id") != null ) {
                $item.insertAfter($item.next(".upload-item"));
            }
        });
        $("#" + fileId).find(".prev").click(function(){
            if ($item.prev(".upload-item").attr("id") != null ) {
                $item.insertBefore($item.prev(".upload-item"));
            }
        });

        if (contentView.children(".upload-item").size() >= maxItems) {
            contentView.find(".btn-wt-upload").first().hide();
        }

    };


    var _fileExt = function (key) {
        var exts = {"folder":"folder","mail":"mail","offline":"offline","weixin-dir":"weixin-dir","xfdownload":"xfdownload","weixin-news":"weixin-news",
            "doc":"doc","docx":"docx","vsd":"vsd","wps":"wps","ppt":"ppt","pptx":"pptx","dps":"dps","msg":"msg","xls":"xls","xlsx":"xlsx","et":"et",
            "pdf":"pdf","txt":"txt","key":"key","rp":"rp","numbers":"numbers","pages":"pages","keynote":"keynote",
            "jpg":"jpg","jpeg":"jpeg","png":"png","gif":"gif","bmp":"bmp","psd":"psd","ai":"ai","eps":"eps",
            "avi":"avi","asf":"asf","mp4":"mp4","mkv":"mkv","mov":"mov","mod":"mod","mpe":"mpe","3gp":"3gp","rmvb":"rmvb","wmv":"wmv","wmf":"wmf","mpg":"mpg",
            "mpeg":"mpeg","rm":"rm","dat":"dat","f4a":"f4a","webm":"webm","swf":"swf","flv":"flv","fla":"fla","mp3":"mp3","wma":"wma","wav":"wav","ogg":"ogg",
            "acc":"acc","m4a":"m4a","wave":"wave","midi":"midi","ape":"ape","aac":"aac","aiff":"aiff","mid":"mid","xmf":"xmf","rtttl":"rtttl","flac":"flac",
            "amr":"amr","ipa":"ipa","apk":"apk","exe":"exe","msi":"msi","bat":"bat","log":"log","xmin":"xmin","htm":"htm","html":"html","c":"c","xml":"xml",
            "link":"link","asp":"asp","chm":"chm","hlp":"hlp","ttf":"ttf","ttc":"ttc","otf":"otf","fon":"fon","bak":"bak","tmp":"tmp","old":"old","zip":"zip",
            "7z":"7z","rar":"rar","iso":"iso","ace":"ace","cab":"cab","uue":"uue","jar":"jar","tar":"tar","dmg":"dmg","document":"document","exec ":"exec ",
            "code ":"code ","image ":"image ","video ":"video ","compress ":"compress ","unknow ":"unknow ","file":"file","filebroken":"filebroken"};
        if (key == null || exts[key] == null){
            return "icon-file";
        }
        return "icon-" + exts[key];
    };


    var _error = function(msg){
        if($.isFunction(this.option.onerror)){
            this.option.onerror.apply(this, [msg]);
        }
    };

    // ------- Public Method Here -------------
    Controller.prototype.setOption = function(option, args){
        if ($.isPlainObject(option)) {
            this.option = $.extend(false, this.option, option);
        } else if(typeof(option) === 'string'){
            switch(option){
                case 'preview':
                    this.preview(args);
                    break;
                case 'fileNames':
                    this.fileNames(args);
                    break;
                default:
                    _error.apply(this, ['未知参数！']);
                    return;
            }
        } else {
            _error.apply(this, ['未知参数类型！']);
            return;
        }
    };

    Controller.prototype.uploader = function(){
        var accept = this.option.accept;
        var runtimeOrder = this.option.runtimeOrder;
        var flashPath = this.option.flashPath;
        var uploadURLString = this.option.baseUrlString + this.option.uploadUrl;
        return WebUploader.create({
            swf: flashPath,
            server: uploadURLString,
            accept: accept,
            runtimeOrder : runtimeOrder,
            resize: false,
            compress : false
        });
    };

    Controller.prototype.preview = function (args) {
        if( args != null && args.length > 0){
            $(this.input).children('.upload-item').each(function () {
                $(this).remove();
            });
            for (var i = 0 ;  i < args.length ; i ++){
                var fileName = args[i];
                var fileExt = '';
                if (fileName.indexOf('.') >= 0){
                    fileExt = fileName.split('.').pop();
                }
                _appendFile.apply(this, [fileName, {"ext" : fileExt}]);
            }
        }else {
            $(this.input).children('.upload-item').each(function () {
                $(this).remove();
            });
            $(this.input).find(".btn-wt-upload").first().show();
        }
    }

    Controller.prototype.fileNames = function (args) {
        var fileName = this.option.fileName;
        var fnsArray = [];
        $("input[name='"+fileName+"']").each(function () {
            fnsArray.push($(this).val());
        });
        args(fnsArray);
    }

})(jQuery);