package ssm.system.mapper;


import ssm.system.entity.User;

/**
 * 用户数据库操作接口
 * @author huangmengwei
 *
 */
public interface UserMapper extends BaseMapper<User, Long>{
	
    /**
     * 根据登录名查询
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);
}
