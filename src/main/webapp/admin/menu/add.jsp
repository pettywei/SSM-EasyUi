<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

    $(function() {
      	
        $('#editForm').form({
        	url : contextPath+'/menu/insertOrUpdate.do',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                	parent.msgShow('提示', result.msg, 'info');
                    parent.$.modalDialog.handler.dialog('close');
                    parent.$.modalDialog.operner_search();
                } else {
                    parent.msgShow('错误', result.msg, 'error');
                }
            }
        });       
    });
    
    
</script>
<div style="padding: 3px;">
        <form id="editForm" method="post" novalidate>
            <table cellpadding="5">
               <tr>
                    <td>菜单名称:</td>
                    <td><input class="easyui-validatebox required" type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>导航地址:</td>
                    <td><input class="easyui-validatebox" type="text" name="url" /></td>
                </tr>
                <tr>
                    <td>父级菜单(不填，默认为父菜单):</td>
                    <td>
                    	<select class="easyui-combotree" url="${pageContext.request.contextPath}/menu/getData.do?status=0&parentId=0" name="parentId" style="width:156px;"/>
                    </td>
                </tr>
                <tr>
                    <td>菜单图标:</td>
                    <td><input class="easyui-validatebox" type="text" name="icon" value="icon-nav" /></td>
                </tr>
                <tr>
                    <td>序列号:</td>
                    <td><input class="easyui-numberspinner" name="sequence" value="1" data-options="min:1,increment:1, editable:false"></input></td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td>
                        <select class="easyui-combobox" name="status" data-options="required:true,editable:false">
           					<option value="0" selected="selected">启用</option>
           					<option value="1">停用</option>
	           			</select>
                    </td>
                </tr>
            </table>
            
        </form>
</div>