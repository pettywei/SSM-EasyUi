package ssm.system.service;


import ssm.system.entity.User;

/**
 * 用户业务操作接口类
 * @author huangmengwei
 *
 */
public interface UserService extends BaseService<User, Long> {
	
    /**
     * 根据登录名查询
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);
    
    
}
