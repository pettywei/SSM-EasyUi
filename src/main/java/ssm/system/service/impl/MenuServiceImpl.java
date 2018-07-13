package ssm.system.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ssm.system.entity.Menu;
import ssm.system.mapper.MenuMapper;
import ssm.system.service.MenuService;


@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu, Long> implements MenuService {

    @Override
    public List<Menu> selectListByIds(List<Long> ids) {
        return mapper.selectListByIds(ids);
    }

    @Override
    public List<Menu> selectListByMap(Map<String, Object> map) {
        return mapper.selectListByMap(map);
    }


}
