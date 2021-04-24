package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.ModuleMapper;
import com.dev.dao.PermissionMapper;
import com.dev.dao.RoleMapper;
import com.dev.query.RoleQuery;
import com.dev.utils.AssertUtil;
import com.dev.vo.Module;
import com.dev.vo.Permission;
import com.dev.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/22 13:23
 * @ClassName RoleService
 */
@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private ModuleMapper moduleMapper;


    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleMapper.queryAllRoles(userId);
    }

    /**
     * 1、参数校验
     *      角色名称  非空名称唯一
     * @param role
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名称不能为空");
        /*查询数据库中是否存在相同的角色名称*/
        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        /*判断角色记录是否存在，存在名称不可用*/
        AssertUtil.isTrue(temp!=null,"名称角色已存在，请重新输入");
        /*设置参数的默认值*/
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());

        /*执行操作，判断受影响的行数*/
        AssertUtil.isTrue(roleMapper.insertSelective(role)<1,"角色添加失败");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role){
        AssertUtil.isTrue(role.getId()==null,"系统数据异常，请重试！");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名称不能为空");
        /*查询数据库中是否存在相同的角色名称*/
        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        /*判断角色记录是否存在，存在名称不可用*/
        AssertUtil.isTrue(temp!=null && !(role.getId().equals(temp.getId())),"名称角色已存在，请重新输入");

        role.setUpdateDate(new Date());

        /*执行操作，判断受影响的行数*/
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"角色添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId){
        Role role = selectByPrimaryKey(roleId);
        AssertUtil.isTrue(null==roleId||null==role,"待删除记录不存在");
        AssertUtil.isTrue(roleMapper.deleteRole(role.getId())<1,"角色记录删除失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId, Integer[] mIds) {
        // 非空判断
        AssertUtil.isTrue(roleId==null,"角色不存在，请重新选择");
        // 判断角色存在
        Role role = roleMapper.selectByPrimaryKey(roleId);
        AssertUtil.isTrue(role==null,"角色不存在！");

        // 先判断角色是否拥有资源
        Integer count = permissionMapper.countPermissionByRoleId(roleId);
        // 如果原本有资源数据 ，则先删除
        if (count>0) {
            // 删除指定角色资源
            AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId)!=count,"角色授权失败");
        }

        // 角色添加授权
        // 得到要添加的权限列表
        if (mIds!=null && mIds.length>0){
            List<Permission> list = new ArrayList<Permission>();
            for (Integer mId : mIds) {
                Permission permission = new Permission();
                permission.setModuleId(mId);
                permission.setRoleId(roleId);
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                Module module = moduleMapper.selectByPrimaryKey(mId);
                permission.setAclValue(module.getOptValue());
                list.add(permission);
            }

        AssertUtil.isTrue(permissionMapper.addPermissions(list)!=list.size(),"角色授权失败");
        }
    }
}
