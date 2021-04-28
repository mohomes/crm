package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.query.CustomerOrderQuery;
import com.dev.service.CustomerOrderService;
import com.dev.vo.CustomerOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @date 2021/4/29 4:35
 * @ClassName CustomerOrderController
 */
@Controller
@RequestMapping("order")
public class CustomerOrderController extends BaseController {
    @Resource
    private CustomerOrderService customerOrderService;

    /**
     * 多条件查询数据表格
     * @param customerOrderQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery){
        return customerOrderService.queryCustomerOrderByParams(customerOrderQuery);
    }

    @RequestMapping("toOrderDetailPage")
    public String toOrderDetailPage(Integer orderId, HttpServletRequest request){
        Map<String,Object> map = customerOrderService.queryOrderDetailByOrderId(orderId);
        request.setAttribute("customerOrder",map);
        return "customer/customer_order_detail";
    }
}
