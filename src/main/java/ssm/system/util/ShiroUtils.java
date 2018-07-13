package ssm.system.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import ssm.system.entity.User;


/**
 * shiro工具类
 */
public class ShiroUtils {
	
	/**
     * 获取当前认证实体的登录名称
     * @return
     */
    public static String getUsername() {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null) return (String)subject.getPrincipal();
        throw new RuntimeException("user's name is null.");
    }
    
    public static User getCurUser() {
        Subject subject = SecurityUtils.getSubject();
        Object obj = subject.getSession().getAttribute("currentUser");
        if(obj==null){
            return new User();
        } else {
            User user = (User)obj;
            return user;
        }
    }
}

