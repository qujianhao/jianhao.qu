<!doctype  html>
<html>
   <head>
	  <head>
    <title>微信安全支付</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<META HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=UTF-8">
</head>
<body>
	</br></br></br></br>
	<div align="center">    
		正在支付，请等待....
		<img src="http://ts.solaridc.com:55818/mobile/shop/images/loading.gif"/>
	</div>
	<script type="text/javascript" src="http://static.solaridc.com/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript">
	    callpay();
		//调用微信JS api 支付
		function jsApiCall2()
		{
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest',
				{
				"appId":"$!appId",
				"timeStamp":"$!timeStamp",
				"signType":"$!signType",
				"package":"$!package",
				"nonceStr":"$!nonceStr",
				"paySign":"$!paySign"
				},
				function(res){
					//WeixinJSBridge.log(res.err_msg);
					//alert(res.err_code+res.err_desc+res.err_msg);
					if(res.err_msg == "get_brand_wcpay_request:ok" ) {
					 setTimeout(" window.location.href = '$!redirectUrl'", 10000 );
					}
				}
			);
		}
		
		//调用微信JS api 支付
		function jsApiCall()
		{
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest',
				{
			   "appId":"wxcccc8abc4b42abab",
				"timeStamp":"1530094588",
				"signType":"MD5",
				"package":"prepay_id=wx271816288301070cff5941bd3288055254",
				"nonceStr":"65ffb9699f20d82d160e05f70ef8926d",
				"paySign":"AF44BF3D85F34171F3B59824748C4E4C"
				},
				function(res){
					//WeixinJSBridge.log(res.err_msg);
					//alert(res.err_code+res.err_desc+res.err_msg);
					if(res.err_msg == "get_brand_wcpay_request:ok" ) {
					 setTimeout(" window.location.href = '$!redirectUrl'", 10000 );
					}
				}
			);
		}


		function callpay()
		{
			if (typeof WeixinJSBridge == "undefined"){
			    if( document.addEventListener ){
			        document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
			    }else if (document.attachEvent){
			        document.attachEvent('WeixinJSBridgeReady', jsApiCall); 
			        document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
			    }
			}else{
			    jsApiCall();
			}
		}
	</script>
</body>
</html>