package ssm.system.service;


import java.util.List;

import ssm.system.entity.Role;

/**
 * 角色业务操作接口类
 * @author huangmengwei
 *
 */
public interface RoleService extends BaseService<Role, Long> {
	
    
    /**
     * 根据多个id查询列表
     * @param ids
     * @return
     */
    List<Role> selectListByIds(List<Long> ids);
    
    /**
     * 查询有效的列表 
     * @return
     */
    List<Role> selectVaildList();
}
