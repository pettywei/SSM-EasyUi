<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
    <%@ include file="../common.jsp" %>
    <script type="text/javascript">
    	
    	function add() {
	        parent.$.modalDialog({
	            title : '新增',
	            width : 450,
	            height : 350,
	            href : contextPath + '/menu/add.do',
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
	            href : contextPath + '/menu/edit.do?id=' + id,
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
        				url : contextPath + '/menu/remove.do',
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
			$('#treeGrid').treegrid({
	            url:contextPath + "/menu/getData.do",
	            queryParams:{
	            	date:new Date()
	            }
	        });
		}
	    
    </script>
</head>
<body class="easyui-layout">
   	<table id="treeGrid" class="easyui-treegrid" data-options="
   				url:'${pageContext.request.contextPath}/menu/getData.do',
   				striped: true,
   				rownumbers: true,
   				fit: true,  
                pagination: false,
                singleSelect: true,
                idField:'id',
                treeField:'text',
                toolbar:'#toolbar',  
                onLoadSuccess:function(data){
                	loadLinkBtn();
                }
                ">
    	<thead>  
        	<tr>  
	            <th field="text" width="200">菜单名称</th>
	            <th field="url" width="150">导航地址</th>
	            <th field="iconCls" width="100">图标</th>
	            <th field="attributes" width="50" formatter="fmtStatus">状态</th>  
	            <th field="id" width="120" formatter="fmtBtn">操作</th>
        	</tr>  
    	</thead>  
   	</table>
	<div id="toolbar">
        <table cellpadding="5">
          <tr>
               <td><a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add">新增</a></td>
           </tr>
       </table>
    </div>
</body>
</html>