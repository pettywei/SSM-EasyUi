package ssm.system.mapper;


import java.util.List;
import java.util.Map;

import ssm.system.entity.Menu;

/**
 * 菜单数据库操作接口
 * @author huangmengwei
 *
 */
public interface MenuMapper extends BaseMapper<Menu, Long>{
    
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
