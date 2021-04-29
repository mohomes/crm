package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.query.CustomerContactQuery;
import com.dev.query.CustomerLinkManQuery;
import com.dev.query.CustomerQuery;
import com.dev.service.CustomerLinkManService;
import com.dev.service.CustomerService;
import com.dev.vo.CustomerLinkMan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/29 20:06
 * @ClassName CustomerLinkManController
 */
@Controller
@RequestMapping("linkman")
public class CustomerLinkManController extends BaseController {

    @Resource
    private CustomerLinkManService customerLinkManService;

    @Resource
    private CustomerService customerService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerLinkManByCusId(CustomerLinkManQuery customerLinkManQuery){
        return customerLinkManService.queryByParamsForTable(customerLinkManQuery);
    }

    @RequestMapping("toCustomerLinkPage")
    public String toCustomerLinkPage(Integer cusId, Model model){
        model.addAttribute("customer",customerService.selectByPrimaryKey(cusId));
        return "customer/customer_linkman";
    }

}
