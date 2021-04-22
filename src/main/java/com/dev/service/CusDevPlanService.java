package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CusDevPlanMapper;
import com.dev.dao.SaleChanceMapper;
import com.dev.query.CusDevPlanQuery;
import com.dev.query.SaleChanceQuery;
import com.dev.utils.AssertUtil;
import com.dev.vo.CusDevPlan;
import com.dev.vo.SaleChance;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/21 17:24
 * @ClassName CusDevPlanService
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan,Integer> {

    @Resource
    private CusDevPlanMapper cusDevPlanMapper;

    @Resource
    private SaleChanceMapper saleChanceMapper;

    public Map<String,Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(cusDevPlanQuery.getPage(),cusDevPlanQuery.getLimit());
        PageInfo<CusDevPlan> pageInfo = new PageInfo<>(cusDevPlanMapper.selectByParams(cusDevPlanQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     *  添加客户开发计划项数据
     *    1、参数校验
     *          营销机会ID  非空
     *          计划项内容 非空
     *          计划时间 非空
     *    2、设置参数的默认值
     *      是否有效
     *      创建时间
     *      更新时间
     * @param cusDevPlan
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCusDevPlan(CusDevPlan cusDevPlan){
        /**
         * 参数校验
         */
        checkCusDevPlanParams(cusDevPlan);

        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());

        AssertUtil.isTrue(cusDevPlanMapper.insertSelective(cusDevPlan)!=1,"计划项数据添加失败");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCusDevPlan(CusDevPlan cusDevPlan){
        AssertUtil.isTrue(null==cusDevPlan.getId()|| cusDevPlanMapper.selectByPrimaryKey(cusDevPlan.getId())==null,
                "数据异常，请重试！");
        checkCusDevPlanParams(cusDevPlan);
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan)!=1,"计划项数据更新失败");
    }

    private void checkCusDevPlanParams(CusDevPlan cusDevPlan) {
        Integer sid = cusDevPlan.getSaleChanceId();
        AssertUtil.isTrue(null==sid||saleChanceMapper.selectByPrimaryKey(sid)==null,"数据异常，请重试！");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()),"计划向不能为空");
        AssertUtil.isTrue(null==cusDevPlan.getPlanDate(),"计划时间不能为空");
    }

    /**
     * 判断
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCusDevPlan(Integer id){
        AssertUtil.isTrue(null==id ,"待删除记录不存在");
        CusDevPlan cusDevPlan = cusDevPlanMapper.selectByPrimaryKey(id);
        cusDevPlan.setIsValid(0);
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan)!=1,"计划项数据删除失败");
    }


}
