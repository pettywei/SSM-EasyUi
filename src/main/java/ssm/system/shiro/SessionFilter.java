package ssm.system.shiro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import ssm.system.util.Common;
import ssm.system.util.ControllerUtils;

/** 
 * 登录过滤 
 *  
 */  
public class SessionFilter extends OncePerRequestFilter {  
  
    @Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
  
  
        // 请求的uri  
        String uri = request.getRequestURI();  
        String path = request.getContextPath();
        //如果登陆uri为登陆页面或者项目名称，则判断session是否存在，存在的话自动转到首页
        if ( uri.indexOf("login.jsp") > 0 || (uri.endsWith(path+"/")) ) {  
            // 执行过滤  
            // 从session中获取登录者实体  
            Object obj = request.getSession().getAttribute(Common.SESSION_USER);  
            if (null != obj) {  
                String loginPage = path+"/admin/main.jsp";
                ControllerUtils.locationHref(request, response, loginPage);
                return;
            } 
        } else if( uri.indexOf("list.do")>-1 ){
            
            // 请求的uri  
            uri = uri.replace(path, "");
            
            boolean isMenu = false;
            Object allMenus = request.getSession().getAttribute(Common.SESSION_ALLMENUS);
            // 判断是否菜单Url
            if(allMenus != null){
                if( allMenus.toString().indexOf(uri) > 0 ){
                    isMenu = true;
                }
            } else {
                //未登录url
                String page = path+"/login.jsp";
                ControllerUtils.locationHref(request, response, page);
                return;
            }
            
            if(isMenu) {
                // 仅过滤菜单权限
                Object menus = request.getSession().getAttribute(Common.SESSION_MENUS);
                if(menus != null){
                    
                    if(  menus.toString().indexOf(uri) < 0 ) {
                        //非法uri
                        String page = path+"/500.jsp";
                        ControllerUtils.locationHref(request, response, page);
                        return;
                    }
                }
            }
            
        } 
        
        // 如果不执行过滤，则继续  
        filterChain.doFilter(request, response);  
         
    }
  
}  
