package ssm.system.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import ssm.system.entity.User;
import ssm.system.service.UserService;
import ssm.system.util.Common;

public class MyRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	
	/**
     * 为当前登陆的用户授予角色和权限
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
     * 对前登陆的用户进行身份认证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    // 获取前台的登录名   
		String loginName = (String) token.getPrincipal(); 	
        
		// 根据登录名从数据库中查询出信息    
		User user = userService.selectByLoginName(loginName);
		
		if( user == null ) {
		    //账号或者密码输入错误
           throw new UnknownAccountException();
		}
		
		if( user.getStatus() == Common.OFF ) {
		    // 账号已被停用
            throw new LockedAccountException();
		}
		
		Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        
        //设置User到Session
        session.setAttribute(Common.SESSION_USER, user);
		
		//把从数据库中查询出来的信息放到authcInfo中返回给Shiro
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
                user.getLoginName(), user.getPassword(), "MyRealm");
        return authcInfo;
	}


}
