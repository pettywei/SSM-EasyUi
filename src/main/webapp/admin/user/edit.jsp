<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

	var selectRoleIds;
	
    $(function() {
    	
    	// 初始化角色
    	selectRoleIds='${user.roleIds}';
    	initTree();
    	
    	$('#editForm').form({
        	url : contextPath+'/user/insertOrUpdate.do',
            onSubmit : function() {
            	getChecked();
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
    
    function initTree() {
        $("#jsontree").tree({
            url: contextPath+'/role/getTree.do',
            method: 'get',
            animate: true,
            checkbox:true,
            onLoadSuccess: function (node, data) {
               	if (selectRoleIds != null && selectRoleIds != ''){
               		var idArray = selectRoleIds.split(",");
               		for (var i=0;i<idArray.length;i++){
               			var node = $('#jsontree').tree('find', idArray[i]);
               			if (node != undefined && node != null){
               				$('#jsontree').tree('check', node.target);
               			}
               		} 
               	} 
            }
        });

    }
	
	function getChecked(){
        var nodes = $('#jsontree').tree('getChecked');
        var roleIds = '';
        var roleId = '';
        for(var i=0; i<nodes.length; i++){
        	roleId = nodes[i].id + ',';
        	if (roleIds == ''){
        		roleIds = roleId;
        	}else{
        		roleIds = roleIds + roleId;
        	}
        }
        if (roleIds != ''){
        	roleIds = roleIds.substring(0,roleIds.lastIndexOf(",",roleIds.length - 1));
    	}
        $("#roleIds").val(roleIds);
    }
	
    
</script>
<div style="padding: 3px;">
        <form id="editForm"  method="post" novalidate>
        <input type="hidden" name="id" value="${user.id}" />
        <input type="hidden" name="roleIds" id="roleIds" value="${user.roleIds}"　/>
        	<div>
            <table class="grid">              
               <tr>               
	                <td>帐号</td>               
	                <td><input name="loginName" type="text" value="${user.loginName}" class="easyui-validatebox textbox" data-options="required:true" /></td>
               </tr>  
               <tr>
               		<td>正式名称</td>
	                <td><input name="realName" type="text" value="${user.realName}" class="easyui-validatebox textbox" data-options="required:true" /></td>
               </tr>
               <tr>
	                <td>状态:</td>
	                <td>
	                    <select class="easyui-combobox" name="status" data-options="required:true,editable:false">
	       					<option value="0" <c:if test="${user.status == 0}">selected="selected" </c:if> >启用</option>
	       					<option value="1" <c:if test="${user.status == 1}">selected="selected" </c:if> >停用</option>
	        			</select>
	                </td>
	           </tr>      
            </table>
            </div>
        </form>
        <div style="margin-top:10px">
        	<p>所属角色</p>
    		<ul id="jsontree" class="easyui-tree" />
    	</div>
</div>