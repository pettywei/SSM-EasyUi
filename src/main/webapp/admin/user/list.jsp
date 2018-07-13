<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <%@ include file="../common.jsp" %>
    <script type="text/javascript">
    
    function add() {
        parent.$.modalDialog({
            title : '新增',
            width : 450,
            height : 350,
            href : contextPath + '/user/add.do',
            buttons : [ {
                text : '确定',
                iconCls : 'icon-ok',
                handler : function() {
                	//需要重新调用search方法，所以先预定义好
                    parent.$.modalDialog.operner_search = search;
                    var f = parent.$.modalDialog.handler.find('#editForm');
                    f.submit();
                }
            } ]
        });
    }

	function edit(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : 450,
            height : 350,
            href : contextPath + '/user/edit.do?id='+id,
            buttons : [ {
                text : '确定',
                iconCls : 'icon-ok',
                handler : function() {
                  	//需要重新调用search方法，所以先预定义好
                    parent.$.modalDialog.operner_search = search;
                    var f = parent.$.modalDialog.handler.find('#editForm');
                    f.submit();
                }
            } ]
        });
    }
	
	function remove(id){
        $.messager.confirm('提示','确定删除该指标吗?', 
        function(r){
            if (r){           		
        		$.ajax( {
    				url : contextPath + '/user/remove.do',
    				dataType:'json',
    				data : {id : id},
    				success : function(result) {
    					parent.msgShow('提示',result.msg,'info');
    					search();
    				},
    				error : function(result) {
    					parent.msgShow('错误',result.msg,'error');
    				}
    			});
            }
        });
        
	}
	
    function search(){
		var loginName = $("#loginNameS").val();
		var realName = $("#realNameS").val();
		var status = $("#statusS").combobox("getValue");
		$('#dataGrid').datagrid({
            url:contextPath + "/user/getData.do",
            queryParams:{
            	loginName:loginName,
            	realName:realName,
            	status:status,
            	date:new Date()
            }
        });
	}
    </script>
</head>
<body class="easyui-layout">
   	<table id="dataGrid" class="easyui-datagrid" data-options="
   				url:'${pageContext.request.contextPath}/user/getData.do',
   				striped: true,
   				rownumbers: true,
   				fit: true,  
                pagination: true,
                singleSelect: true,  
                sortName:'id',
                sortOrder:'asc',  
                toolbar:'#toolbar',
                onLoadSuccess:function(data){
                	loadLinkBtn();
                }
                ">
    	<thead>  
        	<tr>  
	            <th field="loginName" width="200">帐号</th>
	            <th field="realName" width="200">正式名称</th>
	            <th field="status" width="50" formatter="fmtStatus">状态</th>
	            <th field="id" width="120" formatter="fmtBtn">操作</th>
        	</tr>  
    	</thead>  
   	</table>
    <div id="toolbar">
         <form id="fm" method="post" novalidate>
            <table cellpadding="5">
              <tr>
                   <td>帐号:</td>
                   <td><input class="easyui-validatebox textbox" type="text" name="loginNameS" id="loginNameS" /></td>
                   <td>正式名称:</td>
                   <td><input class="easyui-validatebox textbox" type="text" name="realNameS" id="realNameS" /></td>
                   <td>状态:</td>
                   <td>
                        <select class="easyui-combobox" name="statusS" id="statusS" data-options="editable:false">
                        	<option value="">全部</option>
	           				<option value="0">启用</option>
	           				<option value="1">停用</option>
	           			</select>
                   </td>
                   <td>&nbsp;&nbsp;</td>
                   <td><a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">查找</a></td>
                   <td><a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add">新增</a></td>
               </tr>
           </table>
           </form>
    </div>

</body>
</html>