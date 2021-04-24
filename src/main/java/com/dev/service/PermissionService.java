package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.PermissionMapper;
import com.dev.vo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/23 8:48
 * @ClassName PermissionService
 */
@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;


    /**
     * 通过查询用户拥有的角色，角色拥有的资源 得到用户拥有的资源列表（资源权限码）
     * @param userId
     * @return
     */
    public List<String> queryUserHasRoleHasPermission(int userId) {
        return permissionMapper.queryUserHasRoleHasPermission(userId);
    }
}
