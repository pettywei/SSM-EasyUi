package ssm.system.service;


import ssm.system.entity.PageInfo;


/**
 * 通用Service
 * @author huangmengwei
 *
 * @param <T>   实体
 * @param <PK>  主键
 */
public interface BaseService<T, PK> {

    /**
     * 
     * 查询（根据主键ID查询）
     * 
     **/
    T selectByPrimaryKey ( Long id );

    /**
     * 
     * 删除（根据主键ID删除）
     * 
     **/
    boolean deleteByPrimaryKey ( Long id );

    /**
     * 
     * 添加
     * 
     **/
    boolean insert( T entity );

    /**
     * 
     * 添加 （匹配有值的字段）
     * 
     **/
    boolean insertSelective( T entity );

    /**
     * 
     * 修改 （匹配有值的字段）
     * 
     **/
    boolean updateByPrimaryKeySelective( T entity );

    /**
     * 
     * 修改（根据主键ID修改）
     * 
     **/
    boolean updateByPrimaryKey ( T entity );
    
    /**
     * 添加或修改
     * 主键为空时添加，不为空时修改
     * @param entity
     * @return
     */
    boolean insertOrUpdateSelective ( T entity );
    
    /**
     * 分页查询
     * @param page
     * @return
     */
    void selectPageByCondition( PageInfo page );
}
