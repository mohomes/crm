package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.UserMapper;
import com.dev.dao.UserRoleMapper;
import com.dev.model.UserModel;
import com.dev.utils.AssertUtil;
import com.dev.utils.Md5Util;
import com.dev.utils.PhoneUtil;
import com.dev.utils.UserIDBase64;
import com.dev.vo.User;
import com.dev.vo.UserRole;
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
 * @Description 用户登录验证
 * @date 2021/4/19 6:23
 * @ClassName UserService
 */
@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;


    /**
     * 用户登录操作
     * @param userName
     * @param userPwd
     */
    public UserModel userLogin(String userName,String userPwd){
        checkLoginParams(userName,userPwd);
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户不存在");
        // 判断加密的密码是否正确
        checkUserPwd(userPwd,user.getUserPwd());

        // 返回构建用户对象
        return buildUserInfo(user);
    }

    /**
     * 更新用户密码 用户更新和删除时加上事务
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param repeatPwd
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId,String oldPwd,String newPwd,String repeatPwd){
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null,"待更新记录不存在");
        // 参数校验
        checkPassWordParams(user,oldPwd,newPwd,repeatPwd);
        // 设置新密码
        user.setUserPwd(Md5Util.encode(newPwd));
        // 执行更新 判断受影响行数
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改密码失败");
    }

    private void checkPassWordParams(User user, String oldPwd, String newPwd, String repeatPwd) {
        // 原始密码非空校验
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码不能为空");
        // 判断原始密码与数据库中的密码是否一致
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)),"原始密码不正确");
        // 新密码非空校验
        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空");
        // 新密码和重复密码比较
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能和原始密码相同");
        // 确认密码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空");
        // 确认密码和新密码要一致
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),"确认密码和新密码不一致");
    }

    /**
     * 构建需要返回给客户端的对象
     * @param user
     */
    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        userModel.setTrueName(user.getTrueName());
        userModel.setUserName(user.getUserName());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return userModel;
    }

    private void checkUserPwd(String userPwd, String userPwd1) {
        String encode = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd1.equals(encode),"用户密码不正确");
    }

    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空");
    }

    /**
     * 查询所有的销售人员
     * @return
     */
    public List<Map<String,Object>> queryAllSales(){
        return userMapper.queryAllSales();
    }

    /**
     * 1、参数校验
     *      用户名 唯一性
     *      邮箱 email  非空
     *      手机号 非空 格式正确
     *  2、设置参数的默认值
     *      默认密码 123456
     *      isValid
     *      createDate
     *      updateDate
     *   3、执行操作 判断受影响行数
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user){
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),null);
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        AssertUtil.isTrue(userMapper.insertSelective(user)<1,"添加用户失败");

        /**
         * 用户id
         * 角色id
         */
        relationUserRole(user.getId(),user.getRoleIds());
    }

    private void relationUserRole(Integer userId, String roleIds) {
        // 根据用户id查询角色记录
        int count =userRoleMapper.countUserRoleByUserId(userId);
        // 判断用户角色记录是否存在
        if (count>0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId)!=count,"用户角色分配失败");
        }
        //
        if (StringUtils.isNotBlank(roleIds)) {
            // 将用户角色数据设置到集合中 执行批量添加
            List<UserRole> list = new ArrayList<>();
            String[] split = roleIds.split(",");
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(userId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                list.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.insertBatch(list)!=list.size(),"用户角色分配失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user){
        AssertUtil.isTrue(null==user.getId(),"待更新记录不存在");
        AssertUtil.isTrue(null==userMapper.selectByPrimaryKey(user.getId()),"待更新记录不存在");
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),user.getId());
        user.setUpdateDate(new Date());
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)!=1,"用户信息更新失败");
        relationUserRole(user.getId(),user.getRoleIds());
    }

    private void checkUserParams(String userName, String email, String phone,Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        // 如果用户对象为空 表示用户名称可用
        // 数据添加操作 数据库中无数据 只能通过名称查到数据 表示用户名被占用
        // 数据修改操作 数据库中有相应的记录 通过用户名查询到数据 可能是记录本身 也可能是别的数据
        // 如果用户名存在 且与当前修改记录不是同一个  则表示其他记录占用了该用户名 用户名不可用
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null!=user && !user.getId().equals(userId) ,"用户名已存在，请重新输入");
        AssertUtil.isTrue(StringUtils.isBlank(email),"用户邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号码格式不正确");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids){
        AssertUtil.isTrue(null==ids || ids.length==0,"待删除记录不存在");
        AssertUtil.isTrue(userMapper.deleteBatch(ids)!=ids.length,"用户数据删除失败" );
    }

    /**
     * 用户角色关联
     *  添加操作
     *      原始角色不存在
     *       1、不添加新的角色 不操作中间表
     *      2、添加新的角色  给指定绑定相关的角色记录
     *  更新操作
     *      原始角色不存在
     *           1、不添加新的角色 不操作中间表
     *           2、添加新的角色  给指定绑定相关的角色记录
     *
     *      原始角色存在
     *      1、添加新的角色记录  判断已有的角色信息不添加  没有的角色信息添加
     *      2、清空所有的角色记录 删除用户绑定的角色信息
     *      3、移除部分角色记录 删除不存在的角色记录 存在的角色记录保留
     *      4、移除部分角色   添加新的角色  将不存在的角色记录删除 存在的角色记录保留 添加新的角色记录
     *
     *
     *      如何分配角色？
     *      判断角色记录是否存在
     *      将用户原有的角色记录删除 添加新的角色记录
     *
     *  删除操作
     *      删除指定用户绑定的角色记录
     */

}
