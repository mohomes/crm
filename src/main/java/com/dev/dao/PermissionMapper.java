package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    // 通过角色id查询角色数量
    Integer countPermissionByRoleId(Integer roleId);

    // 通过角色id删除资源
    Integer deletePermissionByRoleId(Integer roleId);

    // 给角色添加资源
    int addPermissions(List<Permission> list);

    // 查询角色拥有的所有资源ID的集合
    List<Integer> queryRoleHasModuleByRoleId(Integer roleId);

    List<String> queryUserHasRoleHasPermission(Integer userId);

    Integer countPermissionByModuleId(Integer id);

    Integer deletePermissionByModuleId(Integer id);
}