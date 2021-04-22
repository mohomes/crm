package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {
    /**
     * 通过用户名查询用户实体
     * @param userName
     * @return
     */
    User queryUserByName(String userName);

    /**
     * 查询所有的销售人员
     * @return
     */
    List<Map<String,Object>> queryAllSales();
}