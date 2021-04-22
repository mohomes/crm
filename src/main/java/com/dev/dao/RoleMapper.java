package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    // 查询所有的角色列表
    List<Map<String,Object>> queryAllRoles(Integer userId);

    Role selectByRoleName(String roleName);

    // 逻辑删除
    int deleteRole(Integer id);
}