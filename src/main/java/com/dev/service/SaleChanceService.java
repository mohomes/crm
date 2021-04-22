package com.dev.service;

import com.dev.base.BaseMapper;
import com.dev.base.BaseService;
import com.dev.dao.SaleChanceMapper;
import com.dev.enums.DevResult;
import com.dev.enums.StateStatus;
import com.dev.query.SaleChanceQuery;
import com.dev.utils.AssertUtil;
import com.dev.utils.PhoneUtil;
import com.dev.vo.SaleChance;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.plaf.nimbus.State;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/19 22:11
 * @ClassName SaleChanceService
 */
@Service
public class SaleChanceService extends BaseService<SaleChance,Integer> {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    public Map<String,Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getLimit());
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加营销记录
     *  1、参数校验
     *   customerName 客户名称  非空
     *   linkMan     联系人    非空
     *   linkPhone   联系号码   非空 手机号码格式正确
     *   2、设置相关参数的默认值
     *    createMan 创建人 当前登录用户
     *      assignMan 指派人
     *       如果未设置指派人（默认）
     *           分配状态 state 未分配 0= 未分配 1=已分配
     *           未分配 0 assignTime指派时间 设置为null devResult 0=未开发(0=未开发，1=开发中，2=开发陈宫，3=开发失败)
     *           已分配 1 assignTime指派时间 设置当前系统时间  devResult 1 开发中
     *             isValid 0=有效 1=失效
     * @param saleChance
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addSaleChance(SaleChance saleChance){
        checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        /*设置相关属性默认值*/
        saleChance.setIsValid(1);

        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        /*判断是否设置了指派人*/
        if (StringUtils.isBlank(saleChance.getAssignMan())) {
            //为空未设置指派人
            saleChance.setAssignTime(null);
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
        }else{
            // 设置指派人
            saleChance.setState(StateStatus.STATED.getType());
            // 添加营销操作开发状态未知？ 字段赋值重复。。。傻逼了
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            saleChance.setAssignTime(new Date());
        }
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance)!=1,"添加营销操作失败");
    }

    /*校验参数是否为空 及格式*/
    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone) ,"联系号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone) ,"联系号码格式不正确");
    }

    /**
     * 更新营销机会
     * 1、参数校验
     *      customerName 客户名称  非空
     *      linkMan     联系人    非空
     *      linkPhone   联系号码   非空 手机号码格式正确
     *  2、设置相关参数的默认值
     *    updateDate 更新时间 设置为当前系统时间
     *    assignMan 指派人
     *      原始数据未设置
*              修改后未设置
*               不需要操作
*               修改后已设置
*                  assignTime 指派时间  设置成系统当前时间
*                  分配状态  1=已分配
*                  开发状态  1=开发中
     *      原始数据已设置
     *    修改后未设置
     *          assignTime 指派时间  设置为null
     *          分配状态  0=未分配
     *          开发状态  0=未开发
     *
     *     修改后已设置
     *          判断修改前后是否是同一个指派人
     *              如果是，则不需要操作
     *              如果不是，则需要更新 assignTime 指派时间  设置成当前系统时间
     *   3、执行更新操作，判断受影响的行数
     * @param saleChance
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChance(SaleChance saleChance){
        // 营销机会ID  非空  数据库中对应的记录存在
        AssertUtil.isTrue(null==saleChance.getId(),"待更新记录不存在");
        SaleChance chance = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        // 判断数据库中对应的记录存在
        AssertUtil.isTrue(null==chance,"待更新记录不存在");
        // 参数校验
        checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());

        /**/
        saleChance.setUpdateDate(new Date());
        //判断原始数据是否存在
        if (StringUtils.isBlank(chance.getAssignMan())) {// 不存在
            //判断修改后的值是否存在
            if (!StringUtils.isBlank(saleChance.getAssignMan())){
                // assignTime 指派时间 设置为系统当前时间
                saleChance.setAssignTime(new Date());
                saleChance.setState(StateStatus.STATED.getType());
                saleChance.setDevResult(DevResult.DEVING.getStatus());
            }
        }else{
            // 存在
            // 判断修改后的值是否存在
            if (StringUtils.isBlank(saleChance.getAssignMan())) { // 修改前有值 修改后无值
                //  assignTime 指派时间  设置为null
                //     *          分配状态  0=未分配
                //     *          开发状态  0=未开发
                saleChance.setAssignTime(null);
                saleChance.setState(StateStatus.UNSTATE.getType());
                saleChance.setDevResult(DevResult.UNDEV.getStatus());
            }else{  // 修改前有值 修改有值
                if (!saleChance.getAssignMan().equals(chance.getAssignMan())) {
                    // 更新指派时间
                    saleChance.setAssignTime(new Date());
                }else{
                    // 设置指派时间为修改前的时间
                    saleChance.setAssignTime(chance.getAssignTime());
                }
            }

        }
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance)!=1,"更新营销机会失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance(Integer[] ids){
        /*判断ID是否为空*/
        AssertUtil.isTrue(null==ids || ids.length<1 ,"待删除记录不存在");
        AssertUtil.isTrue(saleChanceMapper.deleteBatch(ids) !=ids.length,"营销数据删除失败");
    }

    /**
     * 更新营销机会的开发状态
     * @param id
     * @param devResult
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChanceDevResult(Integer id,Integer devResult){
        AssertUtil.isTrue(null==id ,"待更新记录不存在");
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==saleChance,"待更新记录不存在");
        saleChance.setDevResult(devResult);
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance)!=1,"开发状态更新失败");
    }
}
