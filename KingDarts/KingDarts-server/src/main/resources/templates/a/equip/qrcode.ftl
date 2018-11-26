<!DOCTYPE html>
<html lang="cn">
<head>
<#include "./a/commons/top.ftl" />
</head>
<#escape x as x?html>
<body >
        <div id="qrcode" style="margin:0 auto; width:200px; height:200px; border:0px solid #F00; line-height:250px;"></div>

    <div class="wt-layer-footer">
        <div class="col-sm-12 wt-layer-footer-content wt-btns-center">
            <button type="button" class="btn btn-sm btn-default wt-download">下载</button>
            <button type="button" class="btn btn-sm btn-default wt-close">关闭</button>
        </div>
    </div>
<#include "./a/commons/bottom.ftl" />

 <script src="${base}/assets/plugins/qrcode/qrcode.js"></script>
<script type="text/javascript">

    $("document").ready(function () {
        //  设置页面标题
        WT.wt_set_navtitle('二维码');
        //  关闭
        $(".wt-close").click(function () {
            WT.wt_close();
        });
        $(".wt-download").click(function () {
        	download("${equno}.jpg")
        });
        var qrcode = new QRCode(document.getElementById("qrcode"), {
        	width : 200,
        	height : 200
        });
        qrcode.makeCode("${qrcodeurl}");
        
    });
    
    function download(imgname) {
        var img = $('#qrcode img').attr("src");
        var alink = document.createElement("a");
        alink.href = img;
        alink.download = imgname;
        alink.click();
    }
       
</script>
</body>
</#escape>
</html>
