<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理系统</title>
    <!-- 导入共通样式页面 -->
	<%@ include file="common.jsp"%>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/nav.js"></script>
	
	<script type="text/javascript">
	// 菜单数据
	var json = ${sessionScope.menus};
	var _menus ={"menus":json};
        $(function() {
        	// 修改密码
            $('#modifyPW').click(function() {
            	var url = '${pageContext.request.contextPath}/admin/user/modifyPW.jsp';
            	addTab('修改密码',url);
            })
			// 安全退出
            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (data){
                	if (data){
                		 location.href = '${pageContext.request.contextPath}/logout.do'
                	}
                });
                
            })
        });
		
    </script>

</head>
  
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

	<noscript>
	<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	    <img src="${pageContext.request.contextPath}/static/common/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div>
	</noscript>
	
	<!-- 顶部 -->
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(${pageContext.request.contextPath}/static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        	欢迎您，${sessionScope.currentUser.realName} 
        	&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="modifyPW">修改密码</a>
        	&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="loginOut">安全退出</a>
        </span>
        <span style="padding-left:10px; font-size: 16px; ">
        	<img src="${pageContext.request.contextPath}/static/images/blocks.gif" width="20" height="20" align="absmiddle" />
        	后台管理系统
        </span>
    </div>
    
    <!-- 底部 -->
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">后台管理系统</div>
    </div>
    
    <!--  导航 -->
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false"></div>
    </div>
    
    <!-- 面板 -->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " style="background: #e7f7ff;" >
				<h1>欢迎登陆后台管理系统！</h1>
			</div>
		</div>
    </div>
	
	<!-- 面板右键菜单功能 -->
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	

</body>
</html>
