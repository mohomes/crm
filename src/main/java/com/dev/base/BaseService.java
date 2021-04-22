package com.dev.base;

import com.dev.dao.UserMapper;
import com.dev.utils.AssertUtil;
import com.dev.vo.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.base
 * @Description
 * @date 2021/4/18 22:05
 * @ClassName BaseService
 */

public abstract class BaseService<T,ID> {

    @Autowired
    private BaseMapper<T,ID> baseMapper;

    public Integer insertSelective(T entity) throws DataAccessException{
        return baseMapper.insertSelective(entity);
    }

    /**
     * 添加记录返回主键
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public ID insertHasKey(T entity) throws DataAccessException{
        baseMapper.insertHasKey(entity);
        try {
            return (ID) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量添加
     * @param entities
     * @return
     * @throws DataAccessException
     */
    public Integer insertBatch(List<T> entities) throws DataAccessException{
        return baseMapper.insertBatch(entities);
    }

    /**
     * 根据id 查询详情
     * @param id
     * @return
     * @throws DataAccessException
     */
    public T selectByPrimaryKey(ID id)throws DataAccessException{
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 条件查询数据
     * @param baseQuery
     * @return
     * @throws DataAccessException
     */
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException{
        return  baseMapper.selectByParams(baseQuery);
    }

    /**
     * 更新数据详情
     * @param entity
     * @return
     * @throws DataAccessException
     */
    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException{
        return baseMapper.updateByPrimaryKeySelective(entity);
    }


    /**
     * 查询数据表格对应数据
     * @param baseQuery
     * @return
     */
    public Map<String,Object> queryByParamsForTable(BaseQuery baseQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        PageInfo<T> info = new PageInfo<>(selectByParams(baseQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }



}
