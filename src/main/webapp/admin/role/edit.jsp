<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

	var selectMenuIds;
	
    $(function() {
    	
    	// 初始化菜单
    	selectMenuIds='${role.menuIds}';
    	initTree();
    	
    	$('#editForm').form({
        	url : contextPath+'/role/insertOrUpdate.do',
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
            url: contextPath+'/menu/getData.do?status=0',
            method: 'get',
            animate: true,
            checkbox:true,
            onLoadSuccess: function (node, data) {
               	if (selectMenuIds != null && selectMenuIds != ''){
               		var idArray = selectMenuIds.split(",");
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
        var menuIds = '';
        var menuId = '';
        for(var i=0; i<nodes.length; i++){
        	menuId = nodes[i].id + ',';
        	if (menuIds == ''){
        		menuIds = menuId;
        	}else{
        		menuIds = menuIds + menuId;
        	}
        }
        if (menuIds != ''){
        	menuIds = menuIds.substring(0,menuIds.lastIndexOf(",",menuIds.length - 1));
    	}
        $("#menuIds").val(menuIds);
    }
	
    
</script>
<div style="padding: 3px;">
        <form id="editForm"  method="post" novalidate>
        <input type="hidden" name="id" value="${role.id}" />
        <input type="hidden" name="menuIds" id="menuIds" value="${role.menuIds}"　/>
        	<div>
            <table class="grid">              
               <tr>               
	                <td>角色名称</td>               
	                <td><input name="name" type="text" value="${role.name}" class="easyui-validatebox textbox" data-options="required:true" /></td>
               </tr>  
               <tr>
	                <td>状态:</td>
	                <td>
	                    <select class="easyui-combobox" name="status" data-options="required:true,editable:false">
	       					<option value="0" <c:if test="${role.status == 0}">selected="selected" </c:if> >启用</option>
	       					<option value="1" <c:if test="${role.status == 1}">selected="selected" </c:if> >停用</option>
	        			</select>
	                </td>
	           </tr>      
            </table>
            </div>
        </form>
        <div style="margin-top:10px">
        	<p>所属菜单</p>
    		<ul id="jsontree" class="easyui-tree" />
    	</div>
</div>