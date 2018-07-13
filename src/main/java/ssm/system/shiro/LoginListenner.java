package ssm.system.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import ssm.system.entity.User;
import ssm.system.util.Common;


/** 
 *  
 * @ClassName: LoginListenner 
 * @Description: 登录监听类-处理同一时间只允许账号，单地点登录 
 * @author  
 * @date 
 *  
 */  
public class LoginListenner implements HttpSessionAttributeListener {  
    /** 
     * 用于存放账号和session对应关系的map 
     */  
    private Map<String, HttpSession> map = new HashMap<String, HttpSession>();  
  
    /** 
     * 当向session中放入数据触发 
     */  
    public void attributeAdded(HttpSessionBindingEvent event) {  
        String name = event.getName();  
  
        if (name.equals(Common.SESSION_USER)) {  
            User user = (User) event.getValue();  
            if (map.get(user.getLoginName()) != null) {  
                HttpSession session = map.get(user.getLoginName());  
                session.removeAttribute(user.getLoginName());  
                session.invalidate();  
            }  
            map.put(user.getLoginName(), event.getSession());  
        }  
  
    }  
    /** 
     * 当向session中移除数据触发 
     */  
    public void attributeRemoved(HttpSessionBindingEvent event) {  
        String name = event.getName();  
  
        if (name.equals(Common.SESSION_USER)) {  
            User user = (User) event.getValue();  
            map.remove(user.getLoginName());  
  
        }  
    }  
  
    public void attributeReplaced(HttpSessionBindingEvent event) {  
  
    }  
  
    public Map<String, HttpSession> getMap() {  
        return map;  
    }  
  
    public void setMap(Map<String, HttpSession> map) {  
        this.map = map;  
    }  
  
}  
