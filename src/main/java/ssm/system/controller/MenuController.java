package ssm.system.controller;


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

import ssm.system.entity.Menu;
import ssm.system.service.MenuService;
import ssm.system.util.ControllerUtils;
import ssm.system.util.ResultUtils;
import ssm.system.util.TreeUtils;


@Controller
@RequestMapping("/menu")
public class MenuController {
    
    protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MenuService menuService;
	
	
	/**
	 * 列表页
	 * @return
	 */
	@RequestMapping("/list")
    public String list(){
        return "/admin/menu/list";
    }

	/**
     * 获取数据
     * @param menu
     * @param response
     */
    @RequestMapping("/getData")
    public void getData(Menu menu, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", menu.getParentId());
        map.put("status", menu.getStatus());
        List<Menu> list = menuService.selectListByMap(map);
        ControllerUtils.writeJson(TreeUtils.formatMenuTree(list), response);
    }
    
    
    /**
     * 添加页
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        return "/admin/menu/add";
    }
    
    /**
     * 编辑页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Model model, Long id){
        Menu menu = menuService.selectByPrimaryKey(id);
        model.addAttribute("menu", menu);
        
        Long pid = menu.getParentId();
        if( pid!= null && pid != 0) {
            Menu parentMenu = menuService.selectByPrimaryKey(pid);
            model.addAttribute("parentMenu",parentMenu);
        }
        return "/admin/menu/edit";
    }
    
    /**
     * 插入或更新
     * @param menu
     * @param response
     */
    @RequestMapping("/insertOrUpdate")
    public void insertOrUpdate(Menu menu, HttpServletResponse response){
        if(menu.getParentId()==null){
            menu.setParentId(0L);
        }
        if(menuService.insertOrUpdateSelective(menu)){
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
        if(menuService.deleteByPrimaryKey(id)){
            ResultUtils.renderSuccess("删除成功！", response);
        } else {
            ResultUtils.renderFail("删除失败！", response);
        }
        
    }

}
