package ssm.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ssm.system.entity.PageInfo;
import ssm.system.entity.Role;
import ssm.system.entity.Tree;
import ssm.system.service.RoleService;
import ssm.system.util.ControllerUtils;
import ssm.system.util.ResultUtils;


@Controller
@RequestMapping("/role")
public class RoleController {
    
    protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private RoleService roleService;

	
	/**
     * 列表页
     * @return
     */
    @RequestMapping("/list")
    public String list(){
        return "/admin/role/list";
    }
    
	/**
     * 获取分页数据
     * @param page
     * @param rows
     * @param role
     * @param response
     */
    @RequestMapping("/getData")
    public void getData(Integer page, Integer rows,
            Role role, HttpServletResponse response){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("name", role.getName());
        condition.put("status", role.getStatus());
        pageInfo.setCondition(condition);
        roleService.selectPageByCondition(pageInfo);
        ControllerUtils.writeJson(pageInfo, response);
    }
    

	/**
	 * 获取角色列表，以树形式
	 * @param response
	 */
	@RequestMapping("/getTree")
	public void getTree(HttpServletResponse response){
	    List<Tree> trees = new ArrayList<Tree>();
	    List<Role> roles = roleService.selectVaildList();
	    for(Role role : roles){
	        Tree tree = new Tree();
	        tree.setId(role.getId());
	        tree.setText(role.getName());
	        trees.add(tree);
	    }
	    ControllerUtils.writeJson(trees, response);
	}
	
	
	/**
     * 添加页
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "/admin/role/edit";
    }
    
    /**
     * 编辑页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id){
        Role role = roleService.selectByPrimaryKey(id);
        model.addAttribute("role", role);
        return "/admin/role/edit";
    }

    
    /**
     * 插入或更新
     * @param role
     * @param response
     */
    @RequestMapping("/insertOrUpdate")
    public void insertOrUpdate(Role role, HttpServletResponse response){
        if(roleService.insertOrUpdateSelective(role)){
            ResultUtils.renderSuccess("操作成功，请重新登录更新菜单！", response);
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
        if(roleService.deleteByPrimaryKey(id)){
            ResultUtils.renderSuccess("删除成功！", response);
        } else {
            ResultUtils.renderFail("删除失败！", response);
        }
        
    }


}
