package ssm.system.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import ssm.system.entity.Role;
import ssm.system.mapper.RoleMapper;
import ssm.system.service.RoleService;


@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role, Long> implements RoleService{

    @Override
    public List<Role> selectListByIds(List<Long> ids) {
        return mapper.selectListByIds(ids);
    }

    @Override
    public List<Role> selectVaildList() {
        return mapper.selectVaildList();
    }

	
}
