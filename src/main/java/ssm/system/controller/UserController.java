package ssm.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ssm.system.entity.User;
import ssm.system.entity.PageInfo;
import ssm.system.service.UserService;
import ssm.system.util.ControllerUtils;
import ssm.system.util.MethodUtils;
import ssm.system.util.ResultUtils;
import ssm.system.util.ShiroUtils;


/**
 * 用户控制器
 * @author huangmengwei
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
	@Resource
    private UserService userService;
	
	
	/**
     * 列表页
     * @return
     */
    @RequestMapping("/list")
    public String list(){
        return "/admin/user/list";
    }
    
	/**
     * 获取分页数据
     * @param page
     * @param rows
     * @param user
     * @param response
     */
    @RequestMapping("/getData")
    public void getData(Integer page, Integer rows,
            User user, HttpServletResponse response){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("loginName", user.getLoginName());
        condition.put("realName", user.getRealName());
        condition.put("status", user.getStatus());
        pageInfo.setCondition(condition);
        userService.selectPageByCondition(pageInfo);
        ControllerUtils.writeJson(pageInfo, response);
    }

    /**
     * 添加页
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "/admin/user/edit";
    }
    
    /**
     * 编辑页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id){
        User user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "/admin/user/edit";
    }

    
    /**
     * 插入或更新
     * @param user
     * @param response
     */
    @RequestMapping("/insertOrUpdate")
    public void insertOrUpdate(User user, HttpServletResponse response){
        if(StringUtils.isBlank(user.getPassword())){
            // 默认密码123
            user.setPassword(MethodUtils.md5("123"));
        }
        if(userService.insertOrUpdateSelective(user)){
            ResultUtils.renderSuccess("操作成功！", response);
        } else {
            ResultUtils.renderFail("操作失败！", response);
        }
        
    }
    
    /**
     * 删除
     * @param id
     * @param response
     */
    @RequestMapping("/remove")
    public void remove(Long id, HttpServletResponse response){
        if(userService.deleteByPrimaryKey(id)){
            ResultUtils.renderSuccess("删除成功！", response);
        } else {
            ResultUtils.renderFail("删除失败！", response);
        }
        
    }

	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @param newPassword
	 * @param response
	 */
	@RequestMapping("/updatePassword")
	public void updatePassword(String password, String newPassword,
	        HttpServletResponse response){
	    User user = ShiroUtils.getCurUser();
	    if(user.getPassword().equals(MethodUtils.md5(password))){
	        user.setPassword(MethodUtils.md5(newPassword));
	        userService.updateByPrimaryKey(user);
	        ResultUtils.renderSuccess("修改成功", response);
	    } else {
	        ResultUtils.renderFail("修改失败", response);
	    }
	}

	
}
