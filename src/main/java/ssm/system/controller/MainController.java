package ssm.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;









import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import ssm.system.entity.Menu;
import ssm.system.entity.Role;
import ssm.system.entity.Tree;
import ssm.system.entity.User;
import ssm.system.service.MenuService;
import ssm.system.service.RoleService;
import ssm.system.util.Common;
import ssm.system.util.DESUtil;
import ssm.system.util.MethodUtils;
import ssm.system.util.StringEscapeEditor;
import ssm.system.util.TreeUtils;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("/")
public class MainController {
    
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
        * 防止XSS攻击
        */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }
    
    /**
     * 权限用户登入
     * @param loginName
     * @param password
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request) {
        
        String loginName = user.getLoginName();
        String desUserPwd = user.getPassword();
        //进行DES解密
        try {
            user.setPassword(DESUtil.decryption(desUserPwd,"desssmEasyui"));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        
        /*判断输入验证码是否正确*/
        String inputcode = request.getParameter("verificationcode");//获取用户输入验证码
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);//获取session中验证码  
        if( code==null || !code.equals(inputcode)){
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "验证码错误");
            return "login"; //如果认证失败，则跳会登录页面并提示错误信息
        }
        
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, MethodUtils.md5(user.getPassword()));
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号输入错误！");
            return "login"; 
        } catch (LockedAccountException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号已被禁用！");
            return "login"; 
        } catch (ExcessiveAttemptsException e) {  
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "登录失败连续超过5次，账号锁定中，请于3分钟后尝试！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "账号或者密码输入错误！");
            return "login";
        } catch (AuthenticationException e) { 
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", e.getMessage());
            return "login";
        }
        
        User curUser = (User) session.getAttribute(Common.SESSION_USER);
        List<Menu> menuList = new ArrayList<Menu>();
                
        String ids = curUser.getRoleIds();
        if(StringUtils.isBlank(ids)) {
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "该用户没有绑定角色，请联系管理员！");
            return "login"; 
        }
        
        List<Long> roleIds = new ArrayList<Long>();
        if(ids.indexOf(",")>-1){
            String[] _ids = ids.split(",");
            for(int i=0;i<_ids.length;i++){
                roleIds.add(Long.parseLong(_ids[i]));
            }
        } else {
            roleIds.add(Long.parseLong(ids));
        }
        //获取所属角色
        List<Role> roles = roleService.selectListByIds(roleIds);
        //菜单ids，使用线程安全CopyOnWriteArrayList进行操作
        List<Long> menuIds = new CopyOnWriteArrayList<Long>();
        for(Role role : roles){
            String r_mId = role.getMenuIds();
            if(r_mId.indexOf(",")>-1){
                String[] _ids = r_mId.split(",");
                for(int i=0;i<_ids.length;i++){
                    menuIds.add(Long.parseLong(_ids[i]));
                }
            } else {
                menuIds.add(Long.parseLong(r_mId));
            }
        }
        if(menuIds==null || menuIds.size()==0){
            model.addAttribute("user", user);
            model.addAttribute("errorInfo", "该用户没有绑定菜单，请联系管理员！");
            return "login"; 
        }
        // 判断父指标是否存在
        for(Long mId : menuIds){
            //查询父指标
            Menu m = menuService.selectByPrimaryKey(mId);
            Long pid = m.getParentId();
            if(!menuIds.contains(pid)){
                menuIds.add(pid);
            }
        }
        
        menuList = menuService.selectListByIds(menuIds);
        
        // 格式化菜单树
        List<Tree> menuTree = TreeUtils.formatMenuTree(menuList);
        
        // 设置session到menus
        session.setAttribute(Common.SESSION_MENUS,JSON.toJSONString(menuTree));//转化为json数据
        
        //记录所有菜单链接
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status",Common.ON);
        List<Menu> list = menuService.selectListByMap(map);
        session.setAttribute(Common.SESSION_ALLMENUS, JSON.toJSONString(list));
        
        return "redirect:/admin/main.jsp";
    }
    
    
    /**
     * 安全退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(Common.SESSION_MENUS);
        session.removeAttribute(Common.SESSION_USER);
        return "redirect:/login.jsp";
    }

    
}
