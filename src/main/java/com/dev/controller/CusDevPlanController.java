package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.CusDevPlanQuery;
import com.dev.service.CusDevPlanService;
import com.dev.service.SaleChanceService;
import com.dev.utils.LoginUserUtil;
import com.dev.vo.CusDevPlan;
import com.dev.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/21 13:11
 * @ClassName CusDevPlanController
 */
@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String toIndex(){
        return "cusDevPlan/cus_dev_plan";
    }

    /**
     * 打开计划项 开发与详情页面
     * @param id
     * @return
     */
    @RequestMapping("toCusDevPlanPage/{id}")
    public String toCusDevPlanPage(@PathVariable("id")Integer id, HttpServletRequest request){
        SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);
        // 将saleChance设置到request作用域中
        request.setAttribute("saleChance",saleChance);
        return "cusDevPlan/cus_dev_plan_data";
    }


    /**
     * 列表查询
     * @param cusDevPlanQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery){
        return  cusDevPlanService.queryCusDevPlanByParams(cusDevPlanQuery);
    }


    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.addCusDevPlan(cusDevPlan);
        return success("计划项添加成功");
    }

    @RequestMapping("toAddOrUpdateCusPlanPage/{saleChanceId}/{id}")
    public String toAddOrUpdateCusDevPlan(HttpServletRequest request,
                                          @PathVariable("saleChanceId") Integer saleChanceId,
                                          @PathVariable("id") Integer id){
        request.setAttribute("sid",saleChanceId);
        CusDevPlan cusDevPlan = cusDevPlanService.selectByPrimaryKey(id);
        request.setAttribute("cusDevPlan",cusDevPlan);
        return "cusDevPlan/add_update";
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功");
    }

    @PostMapping("delete/{id}")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(@PathVariable("id")Integer id){
        cusDevPlanService.deleteCusDevPlan(id);
        return success("计划项删除成功");
    }

    @PostMapping("updateSaleChanceDevResult/{id}/{devResult}")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(@PathVariable("id")Integer id,
                                                @PathVariable("devResult")Integer devResult){
        saleChanceService.updateSaleChanceDevResult(id,devResult);
        return success("开发状态更新成功");
    }




}
