<!DOCTYPE html>
<html lang="cn">
<head>
	<title>公告</title>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.3.2.js"></script>
    <style type="text/css">
    #bg{
		position:fixed;
		width:100%;
		height:100%;
		}
    </style>
</head>
<#escape x as x?html>
<body>
  <img id="bg" src="${base}/assets/dist/img/welcome.png" onclick="gotopage()">
</body>

<!-- <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script src="${base}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script> -->
<script type="text/javascript">
	function gotopage(){
		wx.miniProgram.reLaunch({url: '/pages/index'})
	}
</script>
</#escape>
</html>
