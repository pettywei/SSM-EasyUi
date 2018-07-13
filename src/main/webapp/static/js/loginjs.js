$(function() {
	/*点击图片刷新验证码*/
	$('#kaptchaImage').click(function () {
    	$(this).hide().attr('src', contextPath+'/code/captcha-image.do').fadeIn();
	});
	var date=new Date();
 	var curyear=date.getFullYear();
	$('#copy').html('&copy;'+curyear+' 闽ICP备15002759号-1');
	geterror();
	/**
	 * 判断浏览器小于 等于 IE8
	 */
	checkBrowser();
})
/****************************************
 * 刷新更换验证码
 *@param
 *@return  
 ****************************************/ 
function changeCode() {  //刷新
	$('#kaptchaImage').hide().attr('src', contextPath+'/code/captcha-image.do').fadeIn();  	  
}
//表单空值验证
function checkForm() {
	var loginName = $("#loginName").val();
	var password = $("#password").val();
	if (loginName == null || loginName == "") {
		$.messager.alert("提示", "用户名不能为空！", "info");
		return false;
	}
	if (password == null || password == "") {
		$.messager.alert("提示", "密码不能为空！", "info");
		return false;
	}
	return true;
}

/***
 * 获取系统的当前IE浏览器版本
 * @returns {___anonymous578_605}
 */
function getExplorerInfo() {
    var explorer = window.navigator.userAgent.toLowerCase();
    //ie 
    if (explorer.indexOf("msie") >= 0) {
        var ver = explorer.match(/msie ([\d.]+)/)[1];
        return { type: "IE", version: ver };
    }
}

function checkBrowser(){
	var DEFAULT_VERSION = "8.0";
	var ua = navigator.userAgent.toLowerCase();
	var isIE = ua.indexOf("msie")>-1;
	var safariVersion=null;
	if(isIE){
	    safariVersion =getExplorerInfo().version;
	    if(safariVersion <= DEFAULT_VERSION ){
	        window.location.href= contextPath+"/Browser.jsp"; 
	    }else{
	        return;
	    }
	}else{
	    return;
	}
}

//获取后台返回的错误信息
function geterror() {
	var errorInfo = $("#error").val();
	if (!(errorInfo == null || errorInfo == '')) {
		$.messager.alert("提示", errorInfo, "info");
	}
}

//DES加密
function encryptByDES(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}

//表单提交
function doSubmit(){
	var pwd=$("#password").val();
	if(pwd!=null&&pwd!=''){
		$("#password").val(encryptByDES(pwd,'desssmEasyui'));
	}
	return true;
}