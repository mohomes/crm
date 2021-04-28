package com.dev.controller;

import com.dev.base.BaseController;
import com.dev.query.OrderDetailsQuery;
import com.dev.service.OrderDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.controller
 * @Description
 * @date 2021/4/29 5:27
 * @ClassName OrderDetailsController
 */
@RequestMapping("order_detail")
@Controller
public class OrderDetailsController extends BaseController {
    @Resource
    private OrderDetailsService orderDetailsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery){
        return orderDetailsService.queryOrderDetailByParams(orderDetailsQuery);
    }
}
