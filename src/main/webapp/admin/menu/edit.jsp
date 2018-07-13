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
        	<input type="hidden" name="id" value="${menu.id}">
	   		<input type="hidden" name="parentId" value="${menu.parentId}">
            <table cellpadding="5">
               <tr>
                    <td>菜单名称:</td>
                    <td><input class="easyui-validatebox required" type="text" value="${menu.name}" name="name" /></td>
                </tr>
                <tr>
                    <td>导航地址:</td>
                    <td><input class="easyui-validatebox" type="text" value="${menu.url}" name="url" /></td>
                </tr>
                <tr>
                    <td>父级菜单:</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" value="${parentMenu.name}"  disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td>菜单图标:</td>
                    <td><input class="easyui-validatebox" type="text" name="icon" value="${menu.icon}" /></td>
                </tr>
                <tr>
                    <td>序列号:</td>
                    <td><input class="easyui-numberspinner" name="sequence" value="${menu.sequence}" data-options="min:1,increment:1, editable:false" /></td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td>
                        <select class="easyui-combobox" name="status" data-options="required:true,editable:false">
           					<option value="0" <c:if test="${menu.status == 0}">selected="selected" </c:if> >启用</option>
           					<option value="1" <c:if test="${menu.status == 1}">selected="selected" </c:if> >停用</option>
	           			</select>
                    </td>
                </tr>
            </table>
        </form>
</div>