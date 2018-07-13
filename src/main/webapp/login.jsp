<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理系统</title>
<%@ include file="../admin/common.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/tripledes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mode-ecb.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/loginjs.js"></script>

<style>
			
html,body {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	height: 100%;
}
.box {
	
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#a0c5d8', endColorstr='#a0c5e8'); /*  IE */
	background-image:linear-gradient(bottom, #a0c5d8 0%, #a0c5e8 100%);
	background-image:-o-linear-gradient(bottom, #a0c5d8 0%, #a0c5e8 100%);
	background-image:-moz-linear-gradient(bottom, #a0c5d8 0%, #a0c5e8 100%);
	background-image:-webkit-linear-gradient(bottom, #a0c5d8 0%, #a0c5e8 100%);
	background-image:-ms-linear-gradient(bottom, #a0c5d8 0%, #a0c5e8 100%);
	
	
	margin: 0 auto;
	position: relative;
	width: 100%;
	height: 100%;
	
	
}

.login-box {
	
	width: 100%;
	max-width:500px;
	height: 400px;
	position: absolute;
	top: 50%;

	margin-top: -200px;
	/*设置负值，为要定位子盒子的一半高度*/

	-webkit-border-radius: 0 0 20px 20px;
	-moz-border-radius: 0 0 20px 20px;
	border-radius: 0 0 20px 20px;


	
}
@media screen and (min-width:500px){
	.login-box {
		left: 50%;
		/*设置负值，为要定位子盒子的一半宽度*/
		margin-left: -250px;
		
	}
}	

.form {
	
	width: 100%;
	max-width:350px;
	height: 300px;
	margin: 25px auto 0px auto;
	padding-top: 25px;
}	
.login-content {
	
	z-index: -1;
	height: 290px;
	width: 100%;
	max-width:500px;
	background-color: #f8f8f8;
	float: left;
/*
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
*/
}		
	
	
.input-group {
	margin: 0px 0px 30px 0px !important;
	
}
.form-control,
.input-group {
	height: 40px;
}

.form-group {
	margin-bottom: 0px !important;
}
.login-title {
	padding: 20px 10px;
	background-color: rgba(0, 0, 0, .6);
	text-align: center;
}
.login-title h1 {
	margin-top: 10px !important;
	font-size: 35px;
	color: white;
	text-shadow: 3px 4px 3px #333;
	font-weight: bold;
}
.login-title strong {
	color: #fff;
}

.link p {
	line-height: 20px;
	margin-top: 30px;
}
.btn-sm {
	padding: 8px 24px !important;
	font-size: 16px !important;
}

</style>
		
</head>
<body>
		<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1>后台管理系统</h1>
			</div>
			<div class="login-content ">
			<div class="form" >
			<form name="form" id="form" action="${pageContext.request.contextPath}/login.do" 
					method="post" onsubmit="return checkForm()">
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" id="loginName" name="loginName" value="${user.loginName }" class="form-control" placeholder="用户名" />
							
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input type="password" id="password" name="password" value="${user.password }" class="form-control" placeholder="密码" />
							
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-4">
						<div class="input-group" style="width:160px">
							<input type="text" id="verificationcode" name="verificationcode" class="form-control" placeholder="验证码 " style="width:80px">
							<img src="${pageContext.request.contextPath}/code/captcha-image.do" id="kaptchaImage" 
							style="width: 80px;height:40px;"/>
						</div>
					</div>
				</div>
				

				<div class="form-group form-actions" style="margin-left: 220px;">
					<div class="col-xs-5 ">
						<button type="submit" onclick="return doSubmit();" class="btn btn-sm btn-primary" style="margin-top: 0px;position: absolute;"><span class="glyphicon glyphicon-off" ></span> 登录</button>
					</div>
				</div>
				<div class="form-group" >
					
				</div>
			</form>
	         <div style="text-align:center;margin-top: 210px;position: absolute;margin-left: 40px;"> 
	         	<span id='copy'></span>
	         </div>
			</div>
			<input type="hidden" value="${errorInfo }" id="error" name="error"></input>
		</div>
	</div>
</div>
		
</body>
</html>