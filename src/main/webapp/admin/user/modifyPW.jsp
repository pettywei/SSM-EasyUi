<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>修改密码</title>

	<%@ include file="../common.jsp" %>

    <script type="text/javascript">
	
	function submitForm(){
        $('#myfm').form('submit',{
            url: '${pageContext.request.contextPath}/user/updatePassword.do',
            onSubmit: function(){
            	if ($(this).form('validate')){
            		var password = $("#password").val();
            		var newPassword = $("#newPassword").val();
            		var newPasswordRepeat = $("#newPasswordRepeat").val();
            		if (password == newPassword){
            			parent.msgShow('失败','新旧密码相同','error');
            			return false;
            		}
            		if (newPassword != newPasswordRepeat){
            			parent.msgShow('失败','两次输入的新密码不一致','error');
            			return false;
            		}
            		return true;
            	}else{
            		return false;
            	}
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result.iserror){
                	parent.msgShow('失败',result.msg,'error');
                } else {
                	parent.msgShow('成功',result.msg,'info');
                	parent.closeTab('修改密码');
                }
            }
        });
	}
		
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" border="false" style="text-align:center;">
	<div style="padding:10px 20px" >
	   <div>修改密码</div>
	   <form id="myfm" method="post" novalidate>
	   	   <table cellpadding="5" align="center">
                <tr>
                    <td>旧密码:</td>
                    <td><input id="password" class="easyui-validatebox textbox" type="password" name="password" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>新密码:</td>
                    <td>
                    	<input id="newPassword" class="easyui-validatebox textbox" type="password" name="newPassword" 
                    		data-options="required:true,validType:'password'" />
                    </td>
                </tr>
                <tr>
                    <td>重复新密码:</td>
                    <td>
                    	<input id="newPasswordRepeat" class="easyui-validatebox textbox" type="password" name="newPasswordRepeat" 
                    		data-options="required:true" validType="same['newPassword']" />
                    </td>
                </tr>
            </table>
	   </form>
    </div>
    	<hr>
		<div style="padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
    	</div>
    </div>
</body>
</html>