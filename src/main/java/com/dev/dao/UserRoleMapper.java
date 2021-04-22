package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    /**
     * 根据用户id
     * 查询用户角色记录
     * @param userId
     * @return
     */
    int countUserRoleByUserId(Integer userId);

    int deleteUserRoleByUserId(Integer userId);
}