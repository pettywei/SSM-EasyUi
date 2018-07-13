
/**
 * 格式化时间
 * @param value
 * @param format
 * @returns
 */
function fmtDatebox(value, format) {  
	if (value == null || value == '') {  
	   return '';  
	}  
	var dt;  
	if (value instanceof Date) {  
	   dt = value;  
	} else {  
	   dt = new Date(value);  
	}  
	return dt.format(format); //扩展的Date的format方法(上述插件实现)  
} 

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (format) {
var o = {  
      "M+": this.getMonth() + 1, // month  
      "d+": this.getDate(), // day  
      "h+": this.getHours(), // hour  
      "m+": this.getMinutes(), // minute  
      "s+": this.getSeconds(), // second  
      "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
      "S": this.getMilliseconds()  
      // millisecond  
  }  
  if (/(y+)/.test(format))  
      format = format.replace(RegExp.$1, (this.getFullYear() + "")  
          .substr(4 - RegExp.$1.length));  
  for (var k in o)  
      if (new RegExp("(" + k + ")").test(format))  
          format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
  return format;  
}

/**
 * 格式化指标等级
 * @param value
 */
function fmtLevel(value){
	if (value == 1){  
        return '一级指标';  
    } else if (value == 2) {  
        return '二级指标';  
    } else if (value == 3) {  
        return '三级指标';  
    } else {
    	return '未知';
    }
}

/**
 * 格式化是否
 * @param value
 * @returns {String}
 */
function fmtIsNot(value){  
    if (value == 0){  
        return '否';  
    } else if (value == 1) {  
        return '是';  
    } else {
    	return '未知';
    }
}

/**
 * 格式化状态
 * @param value
 * @returns {String}
 */
function fmtStatus(value){
	if (value == 0){  
        return '启用';  
    } else if (value == 1) {  
        return '停用';  
    } else {
    	return '未知';
    }
}

function fmtType(value){
	if (value == 1){  
        return '单选项';  
    } else if (value == 2) {  
        return '多选项';  
    } else if (value == 3) {  
        return '范围项';  
    } else {
    	return '未知';
    }
}

/**
 * 格式化按钮
 * @param value
 * @returns {String}
 */
function fmtBtn(value){
	var str = '';
	str += '<a href="javascript:edit(\''+value+'\');" class="easyui-linkbutton-edit"></a>';
	str += '<a href="javascript:remove(\''+value+'\');" class="easyui-linkbutton-remove"></a>';
	return str;
}
function loadLinkBtn(){
	$('.easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit-new'});
    $('.easyui-linkbutton-remove').linkbutton({text:'删除',plain:true,iconCls:'icon-remove-new'});
}