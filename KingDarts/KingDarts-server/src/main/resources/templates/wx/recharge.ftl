<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>凯帝狮</title>
    <script>
    (function init () {
        const docEle = document.documentElement;
        const resizeEvt = "orientationchange" in window ? "orientationchange" : "resize";
        const fontSize = function () {
            const clientWidth = docEle.clientWidth;
            if (!clientWidth) return;
            docEle.style.fontSize = 20 * (clientWidth / 375) + "px";
        }
        fontSize();
        window.addEventListener(resizeEvt, fontSize, false);
    })();
    </script>
    <link rel="stylesheet" href="${base}/assets/dist/css/recharge.css">
    <script src="${base}/assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<body>
<!-- start: 头部 -->
<!-- <div class="header">
    <div class="icon-back">
        <img src="./return.png">
    </div>
    <span class="title">凯蒂狮</span>
</div> -->
<!-- end: 头部 -->
<!-- start: 用户信息 -->
<div class="user">
    <img class="user-avatar" src="${member.headimgurl }" alt="">
    <span class="user-name">${member.nickname }</span>
    <span class="user-account"><span style="color: #575757;">积分：</span>${member.points }</span>
</div>
<!-- end: 用户信息 -->
<!-- end: 充值 -->
<div class="charge">
    <div class="charge-title">
        充值
    </div>
    <div class="charge-list">
    	<#list reclist as rec>
    		<div data-v="${rec.money?string('0.00')}" data-g="${rec.give_game_balance}" class="charge-item">
	            <div class="charge-item-cost">￥ ${rec.money?string('0.00')}</div>
	            <div class="charge-item-preferential">赠送￥${rec.give_game_balance?string('0.00')}</div>
	        </div>
		</#list>
    </div>
    <input type="hidden" id="balance" name="balance" value="${member.balance + member.give_balance + member.coupon_balance}">
    <div class="account">
        余额点：${member.balance + member.give_balance + member.coupon_balance}
    </div>
</div>
<!-- end: 充值 -->
<!-- start: 按钮组 -->

<div class="actions" style="margin-top: -20px;">
	<button class="btn btn-primary" id="respay">充值</button>
	<#if order_no??>
    <button class="btn btn-primary" id="booked">包机游戏</button>
    <button class="btn btn-outline" id="single">余额点游戏</button>
    </#if>
</div>
<!-- end: 按钮组 -->
<!-- start: 提示 -->
<div class="tips">飞镖禁止对人投射，文明游戏，注意安全。</div>
<!-- end: 提示 -->

<!-- start: 弹层-->
<div style="display: none;" class="modal" id="toast">
    <div class="modal-mask"></div>
    <div class="modal-body">
        <div class="modal-body-header">提示</div>
        <div class="modal-body-content">确定充值吗？</div>
        <div class="modal-body-footer">
            <div class="like-btn cancel" >取消</div>
            <div style="color: #3063fd;" class="like-btn" id="wxpay">确定</div>
        </div>
    </div>
</div>
<!-- end: 弹层-->
<!-- start: 弹层-->
<div style="display: none;" class="modal" id="tip">
    <div class="modal-mask"></div>
    <div class="modal-body">
        <div class="modal-body-header">提示</div>
        <div class="modal-body-content">余额不足，请充值</div>
        <div class="modal-body-footer">
            <div style="color: #3063fd;" class="like-btn cancel">确定</div>
        </div>
    </div>
</div>
<!-- end: 弹层-->
</body>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script src="${base}/assets/dist/js/longConnect.js"></script>
	<script type="text/javascript">

	if("${errorMsg}"){
		alert("${errorMsg}")
	}
	
	function tip(msg){
		$("#tip .modal-body-content").html(msg);
		$("#tip").show();
	}
	
	var ispaying = false;
	$(document).ready(function(){ 
		$(".charge-list .charge-item:first").addClass("active");
		$(".charge-list .charge-item").click(function () {
			$(this).addClass("active").siblings().removeClass("active");
	    });
	    $('.cancel').click(function () {
	        $('#toast').hide();
	        $('#tip').hide();
	    })
	    
	    $('#respay').click(function () {
	    	if(ispaying){
	        	$("#tip").show();
	        }else{
	        	$("#toast").show();
	        }
	    })
	    
	    var consumeing = false;
	    $("#booked").click(function(){
	    	var balance = $("#balance").val();
			if(balance&&balance<5){
				tip("余额不足，请先充值")
				//alert("余额不足，请先充值");
				return;
			}
			if(consumeing) return;
			consumeing = true;
			$.post("${base}/wx/user/consume",{orderNo:"${order_no }",type:"booked"},function(result){
				consumeing = false;
				if(result&&result.code==0){
					var bal = result.data.balance+result.data.give_balance+result.data.coupon_balance ; 
					$("#balance").val(bal);
					$(".account").text("游戏点"+bal);
					tip("包机成功")
					//alert("支付成功")
				}else{
					alert(result.msg)
				}
			});
		});
		$("#single").click(function(){
			var balance = $("#balance").val();
			if(balance&&balance=='0'){
				tip("余额不足，请先充值")
				//alert("余额不足，请先充值");
				return;
			}
			if(consumeing) return;
			consumeing = true;
			$.post("${base}/wx/user/consume",{orderNo:"${order_no }",type:"single"},function(result){
				consumeing = false;
				if(result&&result.code==0){
					var bal = result.data.balance+result.data.give_balance+result.data.coupon_balance ; 
					$("#balance").val(bal);
					$(".account").text("游戏点"+bal);
					tip("支付成功");
					//alert("支付成功")
				}else{
					alert(result.msg)
				}
			});
		});

		var lftPayId = 0;//查询支付成功通知支付id
	    $("#wxpay").click(function(){
	   		var $div = $(".active")
	   		var money = $div.attr("data-v");
	       	var give_game_balance = $div.attr("data-g");
	       	if(!money || !give_game_balance){
	       		tip("充值金额错误！")
	       		//alert('充值金额错误！')
	       		return;
	       	}
	       	
	       	var params = {totalMoney:money,game_balance:money,give_game_balance:give_game_balance,order_no:'${order_no}',pay_equno:'${pay_equno}'}
	       	$('#toast').hide();
	   		$.post("${base}/wx/fltpay/pay",params,function(res){
	   		    if(res.code==0){
	   		    	lftPayId = res.data.lftPayId;
	   		    	var pay_info = JSON.parse(res.data.pay_info);
	   		    	onBridgeReady(pay_info)
	   		    }else{
	   		    	tip("调用支付失败,"+res.msg)
	   		    	//alert("调用支付失败")
	   		    }
	   		});
	    });
	    
	    function onBridgeReady(pay_info){
	    	WeixinJSBridge.invoke(
        		'getBrandWCPayRequest',  pay_info,
        		function(res){
        			// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
        			if(res.err_msg == "get_brand_wcpay_request:ok" ) {
        				//alert("请等待充值状态通知");
        				getPayBack();
        			}
        		}
        	);  
        }
	    
	    function getPayBack(){
	    	tip("请等待支付结果确认")
	    	ispaying = true;
			longConnect.getConnect("${base}/wx/fltpay/notice",{lftPayId:lftPayId},function(m){
				ispaying = false;
				console.log(m)
				if(m.data){
					if(m.data.trade_status=='success'){
						tip("充值成功")
						$(".account").text("余额点："+m.data.balance)
						//alert("充值成功");
						//$("#balance").val(m.data.balance);
					}else if(m.data.trade_status=='fail'){
						tip("充值失败")
						//alert("充值失败");
					}else if(m.data.trade_status=='timeout'){
						tip("充值超时")
						//alert("充值超时");
					}
				}
			},3)
	    }
	    
	});

    wx.config({
        debug: false,
        appId: '${appId}',
        timestamp: '${timestamp}',
        nonceStr: '${nonceStr }',
        signature: '${signature}',
        jsApiList: [
            'checkJsApi',
            'scanQRCode',
            'chooseWXPay',
            'getBrandWCPayRequest'
        ]
    });

    wx.ready(function () {
    	
        if (typeof WeixinJSBridge == "undefined"){
        	if( document.addEventListener ){
        		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        	}else if (document.attachEvent){
        		document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
        		document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        	}
        }else{
        	//onBridgeReady();
        }
        
    });
    
    wx.error(function (res) {
        alert(res.errMsg);
    });
    
    if("${isNewUser}"){
    	$("#tip .modal-body-content").html("请查收凯帝狮免费送您的${give_game_balance}个游戏点，快来开启游戏之旅吧");
		$("#tip").show();
		//alert("请查收凯帝狮免费送您的20个游戏点，快来开启游戏之旅吧");
	}
</script>
</html>