package com.dev.base;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.base
 * @Description
 * @date 2021/4/18 22:11
 * @ClassName BaseMapper
 */
public interface BaseMapper<T,ID> {
    public Integer insertSelective(T entity) throws DataAccessException;

    public Integer insertHasKey(T entity) throws DataAccessException;

    public Integer insertBatch(List<T> entities) throws DataAccessException;

    public T selectByPrimaryKey(ID id) throws DataAccessException;

    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;

    public Integer deleteByPrimaryKey(ID id) throws DataAccessException;

    /*批量删除*/
    public Integer deleteBatch(ID[] ids ) throws DataAccessException;
}
