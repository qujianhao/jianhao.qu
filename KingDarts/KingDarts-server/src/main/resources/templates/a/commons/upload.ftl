<link rel="stylesheet" href="${base}/assets/plugins/wtUploader/css/wtUploader.css">
<script src="${base}/assets/plugins/wtUploader/script/webuploader.nolog.js"></script>
<script src="${base}/assets/plugins/wtUploader/wtBlankUploader.js"></script>
<script src="${base}/assets/plugins/wtUploader/wtUploader.js"></script>
<style>
    .wt-document-uploader{position: relative; cursor: pointer;margin-top: 8px;}
    .wt-document-uploader.wt-hidden{display: hidden;}
    .wt-document-uploader.wt-ib{display: inline-block;}
    .wt-document-uploader-item .wt-upload-blank-index{}
    .wt-document-uploader-item .wt-upload-blank-view{color: deepskyblue;cursor:pointer;margin-right: 16px;}
    .wt-document-uploader-item .wt-upload-blank-file{margin: 4px 24px 4px 0;display: inline-block;}
    .wt-document-uploader-item .wt-upload-blank-delete{color: red;cursor:pointer;}
</style>
<script>
    function wtblankuploader_accept(){
        return {};
    }
    function wtblankuploader_createUploadBtn(controller, btnId) {
        var hideCss = controller.option.isReadonly ? 'wt-hidden' : 'wt-ib';
        var $btn = $("<div id='fileList_" + btnId + "' class='fileList' style=''></div><div id='" + btnId + "' class='wt-document-uploader "+hideCss+"' >点击上传附件</div>");
        return $btn;
    }
    function wtblankuploader_createUploadItem(controller, contentView, fileId, fileName, fileUrlWithHost, file) {
        var itemSize = contentView.find(".wt-upload-item").size();
        var itemIndex = itemSize + 1;
        var indexItemString = "<span class='wt-upload-blank-index'>" + itemIndex + "、 " + "</span>";
        var delItemString = controller.option.isReadonly ? '' : "<a data-id='" + fileId + "' class='wt-upload-blank-delete' >删除</a>";
        var $item = $("<div id='item_" + fileId + "' class='wt-upload-item wt-document-uploader-item'>" +
                "<a data-id='" + fileId + "' class='wt-upload-blank-view' >查看</a>" +
                "<div class='wt-upload-blank-file'>" + indexItemString + file.name + "</div>" +
                "<input type='hidden' name='" + fileName + "' value='" + file.url + "'>" +
                "<input type='hidden' name='" + fileName + "_names' value='" + file.name + "'>" +
                delItemString +
                "</div>");
        contentView.children(".fileList").append($item);
        //  删除文件,并编号
        if (!controller.option.isReadonly){
            $("#item_" + fileId).find(".wt-upload-blank-delete").click(function () {
                var $parentItem = $item.parent();
                $item.remove();
                $parentItem.children(".wt-upload-item").each(function (index) {
                    $(this).find(".wt-upload-blank-index").text( (index + 1 ) + "、 ");
                });
            });
        }
        $("#item_" + fileId).find(".wt-upload-blank-view").click(function () {
            window.open(fileUrlWithHost);
        });
        return $item;
    }
    function wtblankuploader_isAllowAddFile(controller){
        var minItemsNum = controller.option.minItems;
        var maxItemsNum = controller.option.maxItems;
        var uploaderId = controller.option.uploaderId;
        var size = $("#fileList_" + uploaderId).find(".wt-upload-item").size();
        if (size >= maxItemsNum){
            WT.wt_alert('最多可以添加' + maxItemsNum + '个文件');
            return false;
        }
        return true;
    }

</script>