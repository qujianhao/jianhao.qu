/**
 * 身份证号码验证
 *
 */
/*function isIdCardNo(num) {

	var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
	var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for(i=0;i<intStrLen;i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}

	if (intStrLen == 18) {
		//check date
		var date8 = idNumber.substring(6,14);
		if (isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for(i=0;i<17;i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	}
	else{        //length is 15
		//check date
		var date6 = idNumber.substring(6,12);
		if (isDate6(date6) == false) {

			return false;
		}
	}
	return true;

}

// 身份证号码验证   
jQuery.validator.addMethod("idcardno", function(value, element) {
	return this.optional(element) || isIdCardNo(value);   
}, "请正确输入身份证号码");

//字母数字
jQuery.validator.addMethod("alnum", function(value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");

// 邮政编码验证
jQuery.validator.addMethod("zipcode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写邮政编码");

// 汉字
jQuery.validator.addMethod("chcharacter", function(value, element) {
	var tel = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (tel.test(value));
}, "请输入汉字");

//字符最小长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
	var length = value.length;
	for ( var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element) || (length >= param);
}, $.validator.format("长度不能小于{0}!"));

//字符最大长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMaxLength", function(value, element, param) {
	var length = value.length;
	for ( var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element) || (length <= param);
}, $.validator.format("长度不能大于{0}!"));

//字符验证
jQuery.validator.addMethod("string", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "不允许包含特殊符号!");

//电话号码验证
jQuery.validator.addMethod("phone", function(value, element) {
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误!");


//必须以特定字符串开头验证
jQuery.validator.addMethod("begin", function(value, element, param) {
	var begin = new RegExp("^" + param);
	return this.optional(element) || (begin.test(value));
}, $.validator.format("必须以 {0} 开头!"));

//验证两次输入值是否不相同
jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
	return value != $(param).val();
}, $.validator.format("两次输入不能相同!"));

//验证值不允许与特定值等于
jQuery.validator.addMethod("notEqual", function(value, element, param) {
	return value != param;
}, $.validator.format("输入值不允许为{0}!"));

//验证值必须大于特定值(不能等于)
jQuery.validator.addMethod("gt", function(value, element, param) {
	return value > param;
}, $.validator.format("输入值必须大于{0}!"));
//电子邮箱
jQuery.validator.addMethod("email", function(value, element) {
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	return this.optional(element) || (reg.test(value)); 
}, "请输入正确的邮箱");

//手机校验
jQuery.validator.addMethod("mobile",function(value, element,param){
	var reg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
	console.log("==== "+reg.test(value))
	return reg.test(value); 
},"请填写正确的手机号码");*/




//自定义validate验证输入的数字小数点位数不能大于两位
jQuery.validator.addMethod("decimalNum",function(value, element,param){
	var returnVal = true;
	inputZ=value;
	var ArrMen= inputZ.split(".");    //截取字符串
	if(ArrMen.length==2){
		if(ArrMen[1].length>param){    //判断小数点后面的字符串长度
			returnVal = false;
			return false;
		}
	}
	return returnVal;
},"小数点后最多为两位");
jQuery.validator.addMethod("positiveNum",function(value, element,param){
	var returnVal = true;
	inputZ=value;
	if(inputZ - 0 <= 0){
		return false
	}
	return returnVal;
},"金额需要大于0");