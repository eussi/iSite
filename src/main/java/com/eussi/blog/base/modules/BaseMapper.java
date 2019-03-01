package com.eussi.blog.base.modules;


import java.io.Serializable;
import java.util.List;

/**
 * DAO基类
 */
public interface BaseMapper<T> {

	/**
	 * 根据ID获取PO实例
	 * 
	 * @param id
	 * @return 返回相应的持久化PO实例
	 */
    public T selectByPrimaryKey(Serializable id);

	/**
	 * 保存PO
	 * 
	 * @param entity
	 */
    public int insert(T entity);

    /**
     * 根据非空字段保存PO
     *
     * @param entity
     */
    public int insertSelective(T entity);

	/**
	 * 根据id删除PO
	 * 
	 * @param id
	 */
    public int deleteByPrimaryKey(Serializable id);

	/**
	 * 更新PO非空值
	 * 
	 * @param entity
	 */
    public int updateByPrimaryKeySelective(T entity);

    /**
     * 更新PO
     *
     * @param entity
     */
    public int updateByPrimaryKey(T entity);

    /**
     * 根据查询条件得到总条数
     *
     * @param entity
     */
    public long getTotalCount(T entity);

    /**
     * 根据查询条件查询得到数据
     *
     * @param entity
     */
    public List<T> findAllByQuery(T entity);;

}
