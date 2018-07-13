package ssm.system.service;

import java.util.List;
import java.util.Map;

import ssm.system.entity.Menu;

/**
 * 菜单业务操作接口类
 * @author huangmengwei
 *
 */
public interface MenuService extends BaseService<Menu, Long> {
    

    /**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<Menu> selectListByIds(List<Long> ids);
    
    /**
     * 根据条件查询列表
     * @param status
     * @return
     */
    List<Menu> selectListByMap(Map<String, Object> map);
}
