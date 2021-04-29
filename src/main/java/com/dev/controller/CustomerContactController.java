package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.query.CustomerContactQuery;
import com.dev.service.CustomerContactService;
import com.dev.service.CustomerService;
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
 * @date 2021/4/29 20:07
 * @ClassName CustomerContactController
 */
@Controller
@RequestMapping("contact")
public class CustomerContactController extends BaseController {

    @Resource
    private CustomerContactService customerContactService;

    @Resource
    private CustomerService customerService;
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerContactByCusId(CustomerContactQuery customerContactQuery){
        return customerContactService.queryByParamsForTable(customerContactQuery);
    }


    @RequestMapping("toCustomerContactPage")
    public String toCustomerContactPage(Integer cusId, Model model){
        model.addAttribute("customer",customerService.selectByPrimaryKey(cusId));
        return "customer/customer_contact";
    }
}
