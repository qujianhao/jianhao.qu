var longConnect = (function(){
	var selfOpt = {s:""};
	var onCompressBack  = function(){};
	var ispolling = false;
	var maxnum;
	var pollingnum = 0;
	var postajax;
	var unique;
	var longurl;

	function abort(){
		if(postajax){
			postajax.abort();
		}
	}

	function getConnect(url,opt,compressBack,num){
		selfOpt = opt;
		longurl = url;
		abort();
		onCompressBack = compressBack || function(){};
		maxnum = num || 0;
		pollingnum = 0;
//		if( unique === undefined ){
			unique = new longPolling();
//		}
		return unique;
	}

	function longPolling() {
		if(ispolling) return
		ispolling = true;
		
		console.log(maxnum)
		console.log(pollingnum)
		if(maxnum > 0) pollingnum++;
		selfOpt.timed = new Date().getTime();
		countTime();
		postajax = $.ajax({
			url: longurl,
			data: selfOpt,
			dataType: "json",
			timeout: 30000,
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				//console.log("error")
				ispolling = false;
				if(maxnum > 0 && maxnum < pollingnum){
					alert("状态获取超时");
					return;
				}
				setTimeout(function() {
					longPolling();
				}, 1000);
			},
			success: function (m, textStatus) {
				//console.log("success")
				onCompressBack(m);
				ispolling = false;
				/*if (textStatus == "success") { // 请求成功
					if(m.code==1){
						longPolling();
					}else{
						setTimeout(function() {
							longPolling();
						}, 1000);
					}
				}*/
			}
		});
	};
	
	function countTime(){
		var htime = $(".htime").val();
		var str = "";
		if(htime){
			var date3 = new Date().getTime() - htime;
			//计算出相差天数
			var days=Math.floor(date3/(24*3600*1000))
			 
			//计算出小时数
			var leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
			var hours=Math.floor(leave1/(3600*1000))
			//计算相差分钟数
			var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
			var minutes=Math.floor(leave2/(60*1000))
			//计算相差秒数
			var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
			var seconds=Math.round(leave3/1000)
			
			if(days>0) str += days+"天 ";
			if(hours>0) str += hours+"小时";
			if(minutes>0||str!="") str += minutes+"分钟 ";
			str += seconds+"秒 ";
			//console.log(days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒")
			//console.log(new Date().getTime() - htime)
		}
		$(".top").val(str)
	}

	return {
		getConnect : getConnect,
		abort : abort
	}
})();