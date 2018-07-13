package ssm.system.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import ssm.system.entity.PageInfo;
import ssm.system.mapper.BaseMapper;
import ssm.system.service.BaseService;
import ssm.system.util.MethodUtils;

/**
 * 
 * @author huangmengwei
 *
 * @param <M>   通用Mapper
 * @param <T>   实体
 * @param <PK>  主键
 */
public class BaseServiceImpl<M extends BaseMapper<T, PK>, T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    protected M mapper;
    
    
    /**
     * 判断数据库操作是否成功
     *
     * @param result
     *            数据库操作返回影响条数
     * @return boolean
     */
    protected boolean retBool(int result) {
        return result >= 1;
    }
    
    @Override
    public T selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return retBool(mapper.deleteByPrimaryKey(id));
    }

    @Override
    public boolean insert(T entity) {
        return retBool(mapper.insert(entity));
    }

    @Override
    public boolean insertSelective(T entity) {
        return retBool(mapper.insertSelective(entity));
    }

    @Override
    public boolean updateByPrimaryKeySelective(T entity) {
        return retBool(mapper.updateByPrimaryKeySelective(entity));
    }

    @Override
    public boolean updateByPrimaryKey(T entity) {
        return retBool(mapper.updateByPrimaryKey(entity));
    }

    @Override
    public boolean insertOrUpdateSelective(T entity) {
        // 主键的字段设置为id
        Object oid = MethodUtils.getFieldValue(entity, "id");
        if( oid==null || oid.toString().length() == 0){
            return retBool(mapper.insert(entity));
        } else {
            return retBool(mapper.updateByPrimaryKeySelective(entity));
        }
        
    }
    
    @Override
    public void selectPageByCondition(PageInfo page) {
        page.setRows(mapper.selectListByCondition(page));
        page.setTotal(mapper.selectCountByCondition(page));
    }


}
