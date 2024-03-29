package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.base.ResultInfo;
import com.dev.query.CustomerQuery;
import com.dev.service.CustomerService;
import com.dev.vo.Customer;
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
 * @date 2021/4/27 20:12
 * @ClassName CustomerController
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerByParams(CustomerQuery customerQuery){
        return customerService.queryCustomerByParams(customerQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }

    /**
     * 添加客户信息
     * @param customer
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return success("客户信息添加成功");
    }

    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return success("更新数据信息成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer id){
        customerService.deleteCustomer(id);
        return success("删除成功");
    }

    @RequestMapping("toAddOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer id, HttpServletRequest request){
        if (null!=id){
            Customer customer = customerService.selectByPrimaryKey(id);
            request.setAttribute("customer",customer);
        }
        return "customer/add_update";
    }

    @RequestMapping("toCustomerOrderPage")
    public String toCustomerOrderPage(Integer customerId,HttpServletRequest request){
        request.setAttribute("customer",customerService.selectByPrimaryKey(customerId));
        return "customer/customer_order";
    }

    @RequestMapping("queryContribute")
    @ResponseBody
    public Map<String,Object> queryCustomerContributionByParams(CustomerQuery customerQuery){
        return customerService.queryCustomerContributionByParams(customerQuery);
    }

    /**
     * 折线图
     * @return
     */
    @RequestMapping("countCustomerMake")
    @ResponseBody
    public Map<String,Object> countCustomerMake(){
        return customerService.countCustomerMake();
    }

    @RequestMapping("countCustomerMake02")
    @ResponseBody
    public Map<String,Object> countCustomerMake02(){
        return customerService.countCustomerMake02();
    }
}
