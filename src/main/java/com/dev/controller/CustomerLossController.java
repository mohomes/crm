package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.CustomerLossQuery;
import com.dev.service.CustomerLossService;
import com.dev.service.CustomerService;
import org.springframework.stereotype.Component;
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
 * @date 2021/4/30 21:12
 * @ClassName CustomerLossController
 */
@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index(){
        return "customerLoss/customer_loss";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerLossForTable(CustomerLossQuery customerLossQuery){
        return customerLossService.queryByParamsForTable(customerLossQuery);
    }


    @RequestMapping("openCustomerLossPage")
    public String openCustomerLossPage(Integer id, HttpServletRequest request){
        request.setAttribute("customerLoss",customerLossService.selectByPrimaryKey(id));
        return "customerLoss/addOrInfo";
    }

    /*更新客户的流失状态*/
    @PostMapping("updateCustomerLossState")
    @ResponseBody
    public ResultInfo updateCustomerLossState(Integer id,String lossReason){
        customerLossService.updateCustomerLossState(id,lossReason);
        return success("确认流失成功");
    }
}
