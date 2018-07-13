package ssm.system.service.impl;


import org.springframework.stereotype.Service;

import ssm.system.entity.User;
import ssm.system.mapper.UserMapper;
import ssm.system.service.UserService;


@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, Long> implements UserService{

    @Override
    public User selectByLoginName(String loginName) {
        return mapper.selectByLoginName(loginName);
    }
	

    
}
