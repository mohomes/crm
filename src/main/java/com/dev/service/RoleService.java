package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.RoleMapper;
import com.dev.query.RoleQuery;
import com.dev.utils.AssertUtil;
import com.dev.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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





}
