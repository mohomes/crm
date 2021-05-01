package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.CustomerReprieveQuery;
import com.dev.service.CustomerReprieveService;
import com.dev.utils.AssertUtil;
import com.dev.vo.CustomerReprieve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/5/1 4:57
 * @ClassName CustomerReprieveController
 */
@Controller
@RequestMapping("customer_reprieve")
public class CustomerReprieveController extends BaseController {

    @Resource
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryReprieveForTable(CustomerReprieveQuery customerReprieve){
        return customerReprieveService.queryByParamsForTable(customerReprieve);
    }

    /*添加暂缓操作
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.addCustomerReprieve(customerReprieve);
        return success("添加暂缓措施成功");
    }

    /**
     * 更新暂缓数据
     * @param customerReprieve
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.updateCustomerReprieve(customerReprieve);
        return success("修改暂缓措施成功");
    }

    @RequestMapping("toAddOrUpdateCustomerReprPage")
    public String toAddOrUpdateCustomerReprPage(Integer lossId,Integer id, HttpServletRequest request){
        AssertUtil.isTrue(null==lossId ,"记录不存在");
        request.setAttribute("lossId",lossId);
        if(null!=id){
            request.setAttribute("customerRep",customerReprieveService.selectByPrimaryKey(id));
        }
        return "customerLoss/rep_add_update";
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomerRep(Integer id){
        customerReprieveService.deleteById(id);
        return success("暂缓信息删除成功");
    }
}
