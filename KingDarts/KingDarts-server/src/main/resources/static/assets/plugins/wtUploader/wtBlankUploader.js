window.WTUP = window.WTUP || {};
var browser=WTUP.browser=function(){var agent=navigator.userAgent.toLowerCase(),opera=window.opera,browser={ie:/(msie\s|trident.*rv:)([\w.]+)/.test(agent),opera:(!!opera&&opera.version),webkit:(agent.indexOf(" applewebkit/")>-1),mac:(agent.indexOf("macintosh")>-1),quirks:(document.compatMode=="BackCompat")};browser.gecko=(navigator.product=="Gecko"&&!browser.webkit&&!browser.opera&&!browser.ie);var version=0;if(browser.ie){var v1=agent.match(/(?:msie\s([\w.]+))/);var v2=agent.match(/(?:trident.*rv:([\w.]+))/);if(v1&&v2&&v1[1]&&v2[1]){version=Math.max(v1[1]*1,v2[1]*1)}else{if(v1&&v1[1]){version=v1[1]*1}else{if(v2&&v2[1]){version=v2[1]*1}else{version=0}}}browser.ie11Compat=document.documentMode==11;browser.ie9Compat=document.documentMode==9;browser.ie8=!!document.documentMode;browser.ie8Compat=document.documentMode==8;browser.ie7Compat=((version==7&&!document.documentMode)||document.documentMode==7);browser.ie6Compat=(version<7||browser.quirks);browser.ie9above=version>8;browser.ie9below=version<9;browser.ie11above=version>10;browser.ie11below=version<11}if(browser.gecko){var geckoRelease=agent.match(/rv:([\d\.]+)/);if(geckoRelease){geckoRelease=geckoRelease[1].split(".");version=geckoRelease[0]*10000+(geckoRelease[1]||0)*100+(geckoRelease[2]||0)*1}}if(/chrome\/(\d+\.\d)/i.test(agent)){browser.chrome=+RegExp["\x241"]}if(/(\d+\.\d)?(?:\.\d)?\s+safari\/?(\d+\.\d+)?/i.test(agent)&&!/chrome/i.test(agent)){browser.safari=+(RegExp["\x241"]||RegExp["\x242"])}if(browser.opera){version=parseFloat(opera.version())}if(browser.webkit){version=parseFloat(agent.match(/ applewebkit\/(\d+)/)[1])}browser.version=version;browser.isCompatible=!browser.mobile&&((browser.ie&&version>=6)||(browser.gecko&&version>=10801)||(browser.opera&&version>=9.5)||(browser.air&&version>=1)||(browser.webkit&&version>=522)||false);return browser}();
var ie = browser.ie,
    webkit = browser.webkit,
    gecko = browser.gecko,
    opera = browser.opera;
var utils=WTUP.utils={each:function(obj,iterator,context){if(obj==null){return}if(obj.length===+obj.length){for(var i=0,l=obj.length;i<l;i++){if(iterator.call(context,obj[i],i,obj)===false){return false}}}else{for(var key in obj){if(obj.hasOwnProperty(key)){if(iterator.call(context,obj[key],key,obj)===false){return false}}}}},makeInstance:function(obj){var noop=new Function();noop.prototype=obj;obj=new noop;noop.prototype=null;return obj},extend:function(t,s,b){if(s){for(var k in s){if(!b||!t.hasOwnProperty(k)){t[k]=s[k]}}}return t},extend2:function(t){var a=arguments;for(var i=1;i<a.length;i++){var x=a[i];for(var k in x){if(!t.hasOwnProperty(k)){t[k]=x[k]}}}return t},inherits:function(subClass,superClass){var oldP=subClass.prototype,newP=utils.makeInstance(superClass.prototype);utils.extend(newP,oldP,true);subClass.prototype=newP;return(newP.constructor=subClass)},bind:function(fn,context){return function(){return fn.apply(context,arguments)}},defer:function(fn,delay,exclusion){var timerID;return function(){if(exclusion){clearTimeout(timerID)}timerID=setTimeout(fn,delay)}},indexOf:function(array,item,start){var index=-1;start=this.isNumber(start)?start:0;this.each(array,function(v,i){if(i>=start&&v===item){index=i;return false}});return index},removeItem:function(array,item){for(var i=0,l=array.length;i<l;i++){if(array[i]===item){array.splice(i,1);i--}}},trim:function(str){return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g,"")},listToMap:function(list){if(!list){return{}}list=utils.isArray(list)?list:list.split(",");for(var i=0,ci,obj={};ci=list[i++];){obj[ci.toUpperCase()]=obj[ci]=1}return obj},unhtml:function(str,reg){return str?str.replace(reg||/[&<">'](?:(amp|lt|quot|gt|#39|nbsp|#\d+);)?/g,function(a,b){if(b){return a}else{return{"<":"&lt;","&":"&amp;",'"':"&quot;",">":"&gt;","'":"&#39;"}[a]}}):""},unhtmlForUrl:function(str,reg){return str?str.replace(reg||/[<">']/g,function(a){return{"<":"&lt;","&":"&amp;",'"':"&quot;",">":"&gt;","'":"&#39;"}[a]}):""},html:function(str){return str?str.replace(/&((g|l|quo)t|amp|#39|nbsp);/g,function(m){return{"&lt;":"<","&amp;":"&","&quot;":'"',"&gt;":">","&#39;":"'","&nbsp;":" "}[m]}):""},cssStyleToDomStyle:function(){var test=document.createElement("div").style,cache={"float":test.cssFloat!=undefined?"cssFloat":test.styleFloat!=undefined?"styleFloat":"float"};return function(cssName){return cache[cssName]||(cache[cssName]=cssName.toLowerCase().replace(/-./g,function(match){return match.charAt(1).toUpperCase()}))}}(),loadFile:function(){var tmpList=[];function getItem(doc,obj){try{for(var i=0,ci;ci=tmpList[i++];){if(ci.doc===doc&&ci.url==(obj.src||obj.href)){return ci}}}catch(e){return null}}return function(doc,obj,fn){var item=getItem(doc,obj);if(item){if(item.ready){fn&&fn()}else{item.funs.push(fn)}return}tmpList.push({doc:doc,url:obj.src||obj.href,funs:[fn]});if(!doc.body){var html=[];for(var p in obj){if(p=="tag"){continue}html.push(p+'="'+obj[p]+'"')}doc.write("<"+obj.tag+" "+html.join(" ")+" ></"+obj.tag+">");return}if(obj.id&&doc.getElementById(obj.id)){return}var element=doc.createElement(obj.tag);delete obj.tag;for(var p in obj){element.setAttribute(p,obj[p])}element.onload=element.onreadystatechange=function(){if(!this.readyState||/loaded|complete/.test(this.readyState)){item=getItem(doc,obj);if(item.funs.length>0){item.ready=1;for(var fi;fi=item.funs.pop();){fi()}}element.onload=element.onreadystatechange=null}};element.onerror=function(){throw Error("The load "+(obj.href||obj.src)+" fails,check the url settings of file ueditor.config.js ")};doc.getElementsByTagName("head")[0].appendChild(element)}}(),isEmptyObject:function(obj){if(obj==null){return true}if(this.isArray(obj)||this.isString(obj)){return obj.length===0}for(var key in obj){if(obj.hasOwnProperty(key)){return false}}return true},fixColor:function(name,value){if(/color/i.test(name)&&/rgba?/.test(value)){var array=value.split(",");if(array.length>3){return""}value="#";for(var i=0,color;color=array[i++];){color=parseInt(color.replace(/[^\d]/gi,""),10).toString(16);value+=color.length==1?"0"+color:color}value=value.toUpperCase()}return value},optCss:function(val){var padding,margin,border;val=val.replace(/(padding|margin|border)\-([^:]+):([^;]+);?/gi,function(str,key,name,val){if(val.split(" ").length==1){switch(key){case"padding":!padding&&(padding={});padding[name]=val;return"";case"margin":!margin&&(margin={});margin[name]=val;return"";case"border":return val=="initial"?"":str}}return str});function opt(obj,name){if(!obj){return""}var t=obj.top,b=obj.bottom,l=obj.left,r=obj.right,val="";if(!t||!l||!b||!r){for(var p in obj){val+=";"+name+"-"+p+":"+obj[p]+";"}}else{val+=";"+name+":"+(t==b&&b==l&&l==r?t:t==b&&l==r?(t+" "+l):l==r?(t+" "+l+" "+b):(t+" "+r+" "+b+" "+l))+";"}return val}val+=opt(padding,"padding")+opt(margin,"margin");return val.replace(/^[ \n\r\t;]*|[ \n\r\t]*$/,"").replace(/;([ \n\r\t]+)|\1;/g,";").replace(/(&((l|g)t|quot|#39))?;{2,}/g,function(a,b){return b?b+";;":";"})},clone:function(source,target){var tmp;
    target=target||{};for(var i in source){if(source.hasOwnProperty(i)){tmp=source[i];if(typeof tmp=="object"){target[i]=utils.isArray(tmp)?[]:{};utils.clone(source[i],target[i])}else{target[i]=tmp}}}return target},transUnitToPx:function(val){if(!/(pt|cm)/.test(val)){return val}var unit;val.replace(/([\d.]+)(\w+)/,function(str,v,u){val=v;unit=u});switch(unit){case"cm":val=parseFloat(val)*25;break;case"pt":val=Math.round(parseFloat(val)*96/72)}return val+(val?"px":"")},domReady:function(){var fnArr=[];function doReady(doc){doc.isReady=true;for(var ci;ci=fnArr.pop();ci()){}}return function(onready,win){win=win||window;var doc=win.document;onready&&fnArr.push(onready);if(doc.readyState==="complete"){doReady(doc)}else{doc.isReady&&doReady(doc);if(browser.ie&&browser.version!=11){(function(){if(doc.isReady){return}try{doc.documentElement.doScroll("left")}catch(error){setTimeout(arguments.callee,0);return}doReady(doc)})();win.attachEvent("onload",function(){doReady(doc)})}else{doc.addEventListener("DOMContentLoaded",function(){doc.removeEventListener("DOMContentLoaded",arguments.callee,false);doReady(doc)},false);win.addEventListener("load",function(){doReady(doc)},false)}}}}(),cssRule:browser.ie&&browser.version!=11?function(key,style,doc){var indexList,index;if(style===undefined||style&&style.nodeType&&style.nodeType==9){doc=style&&style.nodeType&&style.nodeType==9?style:(doc||document);indexList=doc.indexList||(doc.indexList={});index=indexList[key];if(index!==undefined){return doc.styleSheets[index].cssText}return undefined}doc=doc||document;indexList=doc.indexList||(doc.indexList={});index=indexList[key];if(style===""){if(index!==undefined){doc.styleSheets[index].cssText="";delete indexList[key];return true}return false}if(index!==undefined){sheetStyle=doc.styleSheets[index]}else{sheetStyle=doc.createStyleSheet("",index=doc.styleSheets.length);indexList[key]=index}sheetStyle.cssText=style}:function(key,style,doc){var head,node;if(style===undefined||style&&style.nodeType&&style.nodeType==9){doc=style&&style.nodeType&&style.nodeType==9?style:(doc||document);node=doc.getElementById(key);return node?node.innerHTML:undefined}doc=doc||document;node=doc.getElementById(key);if(style===""){if(node){node.parentNode.removeChild(node);return true}return false}if(node){node.innerHTML=style}else{node=doc.createElement("style");node.id=key;node.innerHTML=style;doc.getElementsByTagName("head")[0].appendChild(node)}},sort:function(array,compareFn){compareFn=compareFn||function(item1,item2){return item1.localeCompare(item2)};for(var i=0,len=array.length;i<len;i++){for(var j=i,length=array.length;j<length;j++){if(compareFn(array[i],array[j])>0){var t=array[i];array[i]=array[j];array[j]=t}}}return array},serializeParam:function(json){var strArr=[];for(var i in json){if(i=="method"||i=="timeout"||i=="async"){continue}if(!((typeof json[i]).toLowerCase()=="function"||(typeof json[i]).toLowerCase()=="object")){strArr.push(encodeURIComponent(i)+"="+encodeURIComponent(json[i]))}else{if(utils.isArray(json[i])){for(var j=0;j<json[i].length;j++){strArr.push(encodeURIComponent(i)+"[]="+encodeURIComponent(json[i][j]))}}}}return strArr.join("&")},formatUrl:function(url){var u=url.replace(/&&/g,"&");u=u.replace(/\?&/g,"?");u=u.replace(/&$/g,"");u=u.replace(/&#/g,"#");u=u.replace(/&+/g,"&");return u},isCrossDomainUrl:function(url){var a=document.createElement("a");a.href=url;if(browser.ie){a.href=a.href}return !(a.protocol==location.protocol&&a.hostname==location.hostname&&(a.port==location.port||(a.port=="80"&&location.port=="")||(a.port==""&&location.port=="80")))},clearEmptyAttrs:function(obj){for(var p in obj){if(obj[p]===""){delete obj[p]}}return obj},str2json:function(s){if(!utils.isString(s)){return null}if(window.JSON){return JSON.parse(s)}else{return(new Function("return "+utils.trim(s||"")))()}},json2str:(function(){if(window.JSON){return JSON.stringify}else{var escapeMap={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};function encodeString(source){if(/["\\\x00-\x1f]/.test(source)){source=source.replace(/["\\\x00-\x1f]/g,function(match){var c=escapeMap[match];if(c){return c}c=match.charCodeAt();return"\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16)})}return'"'+source+'"'}function encodeArray(source){var result=["["],l=source.length,preComma,i,item;for(i=0;i<l;i++){item=source[i];switch(typeof item){case"undefined":case"function":case"unknown":break;default:if(preComma){result.push(",")}result.push(utils.json2str(item));preComma=1}}result.push("]");return result.join("")}function pad(source){return source<10?"0"+source:source}function encodeDate(source){return'"'+source.getFullYear()+"-"+pad(source.getMonth()+1)+"-"+pad(source.getDate())+"T"+pad(source.getHours())+":"+pad(source.getMinutes())+":"+pad(source.getSeconds())+'"'}return function(value){switch(typeof value){case"undefined":return"undefined";case"number":return isFinite(value)?String(value):"null";case"string":return encodeString(value);case"boolean":return String(value);
    default:if(value===null){return"null"}else{if(utils.isArray(value)){return encodeArray(value)}else{if(utils.isDate(value)){return encodeDate(value)}else{var result=["{"],encode=utils.json2str,preComma,item;for(var key in value){if(Object.prototype.hasOwnProperty.call(value,key)){item=value[key];switch(typeof item){case"undefined":case"unknown":case"function":break;default:if(preComma){result.push(",")}preComma=1;result.push(encode(key)+":"+encode(item))}}}result.push("}");return result.join("")}}}}}}})()};
utils.each(['String', 'Function', 'Array', 'Number', 'RegExp', 'Object', 'Date'], function (v) {
    WTUP.utils['is' + v] = function (obj) {
        return Object.prototype.toString.apply(obj) == '[object ' + v + ']';
    }
});
WTUP.ajax=function(){var fnStr="XMLHttpRequest()";try{new ActiveXObject("Msxml2.XMLHTTP");fnStr="ActiveXObject('Msxml2.XMLHTTP')"}catch(e){try{new ActiveXObject("Microsoft.XMLHTTP");fnStr="ActiveXObject('Microsoft.XMLHTTP')"}catch(e){}}var creatAjaxRequest=new Function("return new "+fnStr);function json2str(json){var strArr=[];for(var i in json){if(i=="method"||i=="timeout"||i=="async"||i=="dataType"||i=="callback"){continue}if(json[i]==undefined||json[i]==null){continue}if(!((typeof json[i]).toLowerCase()=="function"||(typeof json[i]).toLowerCase()=="object")){strArr.push(encodeURIComponent(i)+"="+encodeURIComponent(json[i]))}else{if(utils.isArray(json[i])){for(var j=0;j<json[i].length;j++){strArr.push(encodeURIComponent(i)+"[]="+encodeURIComponent(json[i][j]))}}}}return strArr.join("&")}function doAjax(url,ajaxOptions){var xhr=creatAjaxRequest(),timeIsOut=false,defaultAjaxOptions={method:"POST",timeout:5000,async:true,data:{},onsuccess:function(){},onerror:function(){}};if(typeof url==="object"){ajaxOptions=url;url=ajaxOptions.url}if(!xhr||!url){return}var ajaxOpts=ajaxOptions?utils.extend(defaultAjaxOptions,ajaxOptions):defaultAjaxOptions;var submitStr=json2str(ajaxOpts);if(!utils.isEmptyObject(ajaxOpts.data)){submitStr+=(submitStr?"&":"")+json2str(ajaxOpts.data)}var timerID=setTimeout(function(){if(xhr.readyState!=4){timeIsOut=true;xhr.abort();clearTimeout(timerID)}},ajaxOpts.timeout);var method=ajaxOpts.method.toUpperCase();var str=url+(url.indexOf("?")==-1?"?":"&")+(method=="POST"?"":submitStr+"&noCache="+ +new Date);xhr.open(method,str,ajaxOpts.async);xhr.onreadystatechange=function(){if(xhr.readyState==4){if(!timeIsOut&&xhr.status==200){ajaxOpts.onsuccess(xhr)}else{ajaxOpts.onerror(xhr)}}};if(method=="POST"){xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");xhr.send(submitStr)}else{xhr.send(null)}}function doJsonp(url,opts){var successhandler=opts.onsuccess||function(){},scr=document.createElement("SCRIPT"),options=opts||{},charset=options["charset"],callbackField=options["jsonp"]||"callback",callbackFnName,timeOut=options["timeOut"]||0,timer,reg=new RegExp("(\\?|&)"+callbackField+"=([^&]*)"),matches;if(utils.isFunction(successhandler)){callbackFnName="bd__editor__"+Math.floor(Math.random()*2147483648).toString(36);window[callbackFnName]=getCallBack(0)}else{if(utils.isString(successhandler)){callbackFnName=successhandler}else{if(matches=reg.exec(url)){callbackFnName=matches[2]}}}url=url.replace(reg,"\x241"+callbackField+"="+callbackFnName);if(url.search(reg)<0){url+=(url.indexOf("?")<0?"?":"&")+callbackField+"="+callbackFnName}var queryStr=json2str(opts);if(!utils.isEmptyObject(opts.data)){queryStr+=(queryStr?"&":"")+json2str(opts.data)}if(queryStr){url=url.replace(/\?/,"?"+queryStr+"&")}scr.onerror=getCallBack(1);if(timeOut){timer=setTimeout(getCallBack(1),timeOut)}createScriptTag(scr,url,charset);function createScriptTag(scr,url,charset){scr.setAttribute("type","text/javascript");scr.setAttribute("defer","defer");charset&&scr.setAttribute("charset",charset);scr.setAttribute("src",url);document.getElementsByTagName("head")[0].appendChild(scr)}function getCallBack(onTimeOut){return function(){try{if(onTimeOut){options.onerror&&options.onerror()}else{try{clearTimeout(timer);successhandler.apply(window,arguments)}catch(e){}}}catch(exception){options.onerror&&options.onerror.call(window,exception)}finally{options.oncomplete&&options.oncomplete.apply(window,arguments);scr.parentNode&&scr.parentNode.removeChild(scr);window[callbackFnName]=null;try{delete window[callbackFnName]}catch(e){}}}}}return{request:function(url,opts){if(opts&&opts.dataType=="jsonp"){doJsonp(url,opts)}else{doAjax(url,opts)}},getJSONP:function(url,data,fn){var opts={"data":data,"oncomplete":fn};doJsonp(url,opts)}}}();

var _getUEBasePath=function(docUrl,confUrl){return _getBasePath(docUrl||self.document.URL||self.location.href,confUrl||_getConfigFilePath())};
var _getConfigFilePath=function(){var configPath=document.getElementsByTagName("script");return configPath[configPath.length-1].src};
var _getBasePath=function(docUrl,confUrl){var basePath=confUrl;if(/^(\/|\\\\)/.test(confUrl)){basePath=/^.+?\w(\/|\\\\)/.exec(docUrl)[0]+confUrl.replace(/^(\/|\\\\)/,"")}else{if(!/^[a-z]+:/i.test(confUrl)){docUrl=docUrl.split("#")[0].split("?")[0].replace(/[^\\\/]+$/,"");basePath=docUrl+""+confUrl}}return _optimizationPath(basePath)};
var _optimizationPath=function(path){var protocol=/^[a-z]+:\/\//.exec(path)[0],tmp=null,res=[];path=path.replace(protocol,"").split("?")[0].split("#")[0];path=path.replace(/\\/g,"/").split(/\//);path[path.length-1]="";while(path.length){if((tmp=path.shift())===".."){res.pop()}else{if(tmp!=="."){res.push(tmp)}}}return protocol+res.join("/")};
WTUP.basePath = _getUEBasePath();
WTUP.flashPath = WTUP.basePath + "script/Uploader.swf";
WTUP.configUrl = WTUP.basePath + "../../../wtuploader/controller?action=config";

WTUP.fileRootPath = WTUP.fileRootPath || null;
WTUP.fileServerUrl = WTUP.fileServerUrl || null;
WTUP.isServerConfigLoaded = false;
WTUP.maxSize = 1024 * 1024 * 5;

(function ($) {

    $.fn.extend({
        'WTBlankUploader': function (option, args, fire) {
            if (/^(preview|fileNames)$/i.test(option)) {  //  操作类型
                return this.each(function () {
                    operate(this, option, args);
                });
            }
            var items = this.each(function () {
                if (this.controller)
                    this.controller.setOption(option, args);
                else if ($.isPlainObject(option))
                    this.controller = new Controller(this, option);
            });
            return items;
        }
    });

    function operate(input, option, args) {
        if ('preview' === option) {
            if (WTUP.isServerConfigLoaded)
                input.controller.preview(args);
            else {

            }

        } else if ('fileNames' == option) {
            if (WTUP.isServerConfigLoaded)
                input.controller.fileNames(args);
            else {

            }
        }
    }


    var Controller = function (input, option) {

        var flashPath = WTUP.flashPath;
        var fileName = $(input).attr('data-wt-upload-name') || "file";
        var previewNames = $(input).attr('data-wt-upload-preview') || "";
        var perviewFileNames = $(input).attr('data-wt-upload-preview-names') || "";
        var isReadonly = $(input).attr('data-wt-readonly') == 'true';

        this.defaultAccept = {
            extensions: 'jpg,jpeg,png,gif,bmp',
            mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
        };
        this.input = input;
        this.option = $.extend(false, {
            // style
            'width': 1,                                                     // number, string 'auto'
            'uploaderId': '',
            // item
            'createUploadBtn': null,                                        //  生成上传按钮
            'createUploadItem': null,                                       //  生成文档元素
            // url
            'baseUrlString': WTUP.basePath,                                 // string
            'checkUrl': '../../../wtuploader/controller?action=checks',     // string
            'uploadUrl': '../../../wtuploader/controller?action=uploads',   // string
            'flashPath': flashPath,                         // string
            // data
            'accept': this.defaultAccept,                   // function
            'fileName': fileName,                           // string
            'previewNames': previewNames.split(","),        // string
            'perviewFileNames': perviewFileNames.split(","),// string
            'minItems': 1,                                  // number
            'maxItems': 20,                                 // number
            'runtimeOrder': 'html5, flash',                 // string
            // event
            'matchHandler': null,                           // function 预留
            'emphasisHandler': null,                        // function 预留
            'createItemHandler': null,                      // function 预留
            'onFileDeleteHandler': null,                    // function 文件删除回调
            'onLoadFinishedHandler': null,                  // function 控件加载完成回调
            'uploadFinishedHandler': null,                  // function 上传文件完成回调
            'isAllowAddFile': null,                         // function 是否允许新增文件，可为空，默认返回true
            'isReadonly': isReadonly,                       //  bool    是否只读
            // behavior
            'async': false,                                 // bool
            // debug
            'onerror': null                                 // function
        }, option);

        if (!WTUP.isServerConfigLoaded) {
            var that = this;
            _loadServerConfig(function () {
                _setup.apply(that, [input]);
            }, that);
        } else {
            _setup.apply(this, [input]);
        }
    };


    var _loadServerConfig = function (callback, controller) {
        var configUrl = WTUP.configUrl;
        var isJsonp = WTUP.utils.isCrossDomainUrl(configUrl);
        var that = this;
        WTUP.ajax.request(configUrl, {
            'method': 'GET',
            'dataType': isJsonp ? 'jsonp' : '',
            'onsuccess': function (r) {
                try {
                    var config = isJsonp ? r : eval("(" + r.responseText + ")");
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
            'onerror': function () {
                console.log('获取配置失败!');
            }
        });
    };

    var _afterServerConfigLoaded = function (controller) {
        var previewArray = controller.option.previewNames || [];
        var perviewFileNamesArray = controller.option.perviewFileNames || [];
        if (previewArray != null && previewArray.length > 0) {
            controller.preview(_buildPerviewArgs(previewArray, perviewFileNamesArray));
        }
        if (controller.option.onLoadFinishedHandler != null){
            controller.option.onLoadFinishedHandler(controller);
        }
    };

    var _buildPerviewArgs = function (previewArray, perviewFileNamesArray) {
        var previewArray = previewArray || [];
        var perviewFileNamesArray = perviewFileNamesArray || [];
        var results = [];
        if (previewArray.length == 1 && previewArray[0] == ''){
            return results;
        }
        for (var i = 0 ; i < previewArray.length ; i ++ ){
            var fileName = perviewFileNamesArray.length > i ? perviewFileNamesArray[i] : previewArray[i];
            if (fileName.length <= 0)
                fileName = previewArray[i];
            var fileExt = '';
            if (fileName.indexOf('.') >= 0) {
                fileExt = fileName.split('.').pop();
            }
            var ext = fileExt;
            var url = previewArray[i];
            results.push({name: fileName, ext: ext, url: url});
        }
        return results;
    }


    // ------- Private Method Here -------------
    var _setup = function (input) {
        var that = this;
        var $input = $(input);
        var uploaderId = "file_" + new Date().getTime() + "_" + parseInt(Math.random() * 1000, 10);
        that.option.uploaderId = uploaderId;
        var $uploadContentView = that.option.createUploadBtn(that, uploaderId);
        $input.append($uploadContentView);

        var _uploader = this.uploader("#" + uploaderId);
        _uploader.controller = that;
        var progressView = $input.find('.upload-progress').first();
        // 当有文件被添加进队列的时候
        _uploader.on( 'fileQueued', function( file ) {
            _uploader.md5File( file ).then(function(val) {
                _checkMd5.apply(that, [_uploader, file ,val]);
            });
        });

        _uploader.on( 'beforeFileQueued', function( file ) {
            var controller = _uploader.controller;
            if (controller.option.isAllowAddFile){
                return controller.option.isAllowAddFile(controller);
            }
            return true;
        });

        var layindex = 0;
        _uploader.on( 'uploadStart', function( file ) {
            progressView.show();
            layindex = layer.load(1,{
          	  shade: [0.1,'#fff'], //0.1透明度的白色背景
          	  time:600*1000}); 
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
            layer.close(layindex);
        });
    };


    var _checkMd5 = function (_uploader, file, val) {
        var that = this;
        $.ajax({
            url: this.option.baseUrlString + this.option.checkUrl,
            data: {md5s: val},
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (resultJSON) {
                try {
                    var result = resultJSON;
                    if (result.code == 1) {
                        _uploader.reset();
                        _error.apply(that, ['上传失败,请检查网络稍候再试']);
                        WT.wt_alert(result.msg || '上传失败,请检查网络稍候再试');
                    }
                    if (result.code == 0 && result.data[val] != null) {
                        _appendFile.apply(that, [result.data[val], file]);
                        _uploader.reset();
                    } else if (result.code == 0) {
                        _uploader.upload(file);
                    } else {
                        _uploader.reset();
                        _error.apply(that, ['上传失败,请检查网络稍候再试']);
                    }
                } catch (ex) {
                    _uploader.reset();
                    _error.apply(that, ['上传失败,请检查网络稍候再试']);
                }
            },
            error: function (result) {
                _uploader.reset();
                _error.apply(that, ['上传失败']);
            }
        });
    };


    var _appendFile = function (fileUrl, file) {
        var fileId = "file_" + new Date().getTime() + "_" + parseInt(Math.random() * 1000, 10);
        if (this.option.uploadFinishedHandler != null) {
            this.option.uploadFinishedHandler(fileUrl, file, fileId, this.input);
        }
        var currentFileExt = (file == null || file.ext == null) ? "" : file.ext;
        var currentFileName = (file == null || file.name == null) ? "" : file.name;
        var minItems = this.option.minItems;
        var maxItems = this.option.maxItems;
        var fileName = this.option.fileName;
        var contentView = $(this.input);
        var fileUrlWithHost = WTUP.fileServerUrl + fileUrl;
        file.url = fileUrl;
        var $item = this.option.createUploadItem(this, contentView, fileId, fileName, fileUrlWithHost, file);

    };


    var _fileExt = function (key) {
        var exts = {
            "folder": "folder",
            "mail": "mail",
            "offline": "offline",
            "weixin-dir": "weixin-dir",
            "xfdownload": "xfdownload",
            "weixin-news": "weixin-news",
            "doc": "doc",
            "docx": "docx",
            "vsd": "vsd",
            "wps": "wps",
            "ppt": "ppt",
            "pptx": "pptx",
            "dps": "dps",
            "msg": "msg",
            "xls": "xls",
            "xlsx": "xlsx",
            "et": "et",
            "pdf": "pdf",
            "txt": "txt",
            "key": "key",
            "rp": "rp",
            "numbers": "numbers",
            "pages": "pages",
            "keynote": "keynote",
            "jpg": "jpg",
            "jpeg": "jpeg",
            "png": "png",
            "gif": "gif",
            "bmp": "bmp",
            "psd": "psd",
            "ai": "ai",
            "eps": "eps",
            "avi": "avi",
            "asf": "asf",
            "mp4": "mp4",
            "mkv": "mkv",
            "mov": "mov",
            "mod": "mod",
            "mpe": "mpe",
            "3gp": "3gp",
            "rmvb": "rmvb",
            "wmv": "wmv",
            "wmf": "wmf",
            "mpg": "mpg",
            "mpeg": "mpeg",
            "rm": "rm",
            "dat": "dat",
            "f4a": "f4a",
            "webm": "webm",
            "swf": "swf",
            "flv": "flv",
            "fla": "fla",
            "mp3": "mp3",
            "wma": "wma",
            "wav": "wav",
            "ogg": "ogg",
            "acc": "acc",
            "m4a": "m4a",
            "wave": "wave",
            "midi": "midi",
            "ape": "ape",
            "aac": "aac",
            "aiff": "aiff",
            "mid": "mid",
            "xmf": "xmf",
            "rtttl": "rtttl",
            "flac": "flac",
            "amr": "amr",
            "ipa": "ipa",
            "apk": "apk",
            "exe": "exe",
            "msi": "msi",
            "bat": "bat",
            "log": "log",
            "xmin": "xmin",
            "htm": "htm",
            "html": "html",
            "c": "c",
            "xml": "xml",
            "link": "link",
            "asp": "asp",
            "chm": "chm",
            "hlp": "hlp",
            "ttf": "ttf",
            "ttc": "ttc",
            "otf": "otf",
            "fon": "fon",
            "bak": "bak",
            "tmp": "tmp",
            "old": "old",
            "zip": "zip",
            "7z": "7z",
            "rar": "rar",
            "iso": "iso",
            "ace": "ace",
            "cab": "cab",
            "uue": "uue",
            "jar": "jar",
            "tar": "tar",
            "dmg": "dmg",
            "document": "document",
            "exec ": "exec ",
            "code ": "code ",
            "image ": "image ",
            "video ": "video ",
            "compress ": "compress ",
            "unknow ": "unknow ",
            "file": "file",
            "filebroken": "filebroken"
        };
        if (key == null || exts[key] == null) {
            return "icon-file";
        }
        return "icon-" + exts[key];
    };


    var _error = function (msg) {
        if ($.isFunction(this.option.onerror)) {
            this.option.onerror.apply(this, [msg]);
        }
    };

    // ------- Public Method Here -------------
    Controller.prototype.setOption = function (option, args) {
        if ($.isPlainObject(option)) {
            this.option = $.extend(false, this.option, option);
        } else if (typeof(option) === 'string') {
            switch (option) {
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

    Controller.prototype.uploader = function (pick) {
        var accept = this.option.accept;
        var runtimeOrder = this.option.runtimeOrder;
        var flashPath = this.option.flashPath;
        var uploadURLString = this.option.baseUrlString + this.option.uploadUrl;
        return WebUploader.create({
            swf: flashPath,
            pick: pick,
            server: uploadURLString,
            accept: accept,
            runtimeOrder: runtimeOrder,
            resize: false,
            compress: false
        });
    };

    Controller.prototype.preview = function (args) {
        if (args != null && args.length > 0) {
            $(this.input).children('.wt-upload-item').each(function () {
                $(this).remove();
            });
            for (var i = 0; i < args.length; i++) {
                var file = args[i];
                var name = file.name;
                var url = file.url;
                var fileName = file.url;
                var fileExt = '';
                if (fileName.indexOf('.') >= 0) {
                    fileExt = fileName.split('.').pop();
                }
                file.ext = fileExt;
                _appendFile.apply(this, [fileName, file]);
            }
        }
    }

    Controller.prototype.fileNames = function (args) {
        var fileName = this.option.fileName;
        var fnsArray = [];
        $("input[name='" + fileName + "']").each(function () {
            fnsArray.push($(this).val());
        });
        args(fnsArray);
    }

})(jQuery);