$(function(){	
	$(document).ready(
			function(){  
				initSize();
			}
		 )
});
		 
$(window).resize(
    function () 
    {
    	initSize();
    }
);
		
/**
 * 
 * 统一在这里设置尺寸，在ready和resize时调用
 * add by lq
 * 
 * **/
function initSize(){
	
	$('#dataGrid').datagrid('resize',{
		width:$(window).width(),
		height:$(window).height()*0.9,
	});	
}

/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
    if ($.modalDialog.handler == undefined) {// 避免重复弹出
        var opts = $.extend({
            title : '',
            width : 840,
            height : 680,
            modal : true,
            onClose : function() {
                $.modalDialog.handler = undefined;
                $(this).dialog('destroy');
            },
            onOpen : function() {
            }
        }, options);
        opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
        return $.modalDialog.handler = $('<div/>').dialog(opts);
    }
};

/**
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
    for ( var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};

/**
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
$.stringToList = function(value) {
    if (value != undefined && value != '') {
        var values = [];
        var t = value.split(',');
        for ( var i = 0; i < t.length; i++) {
            values.push('' + t[i]);/* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条启用
 * **/
function progressLoad(){
    $("<div class=\"datagrid-mask\" style=\"position:absolute;z-index: 9999;\"></div>").css({display:"block",width:"100%",height:$("body").height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" style=\"position:absolute;z-index: 9999;\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($("body").outerWidth(true) - 190) / 2,top:$("body").height()-($(window).height()) / 2});
}

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条关闭
 * **/
function progressClose(){
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();
}


/**
 * 底部显示日志
 * @param title
 * @param msg
 */
function showMsg(title,msg){
	$.messager.show({
		title:title,
		msg:msg,
		showType:'slide',
		style:{
			right:'',
			top:'',
			bottom:-document.body.scrollTop-document.documentElement.scrollTop
		}
	});
}
