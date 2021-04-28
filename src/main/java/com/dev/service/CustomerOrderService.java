package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerOrderMapper;
import com.dev.query.CustomerOrderQuery;
import com.dev.vo.Customer;
import com.dev.vo.CustomerOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/29 4:26
 * @ClassName CustomerOrderService
 */
@Service
public class CustomerOrderService extends BaseService<CustomerOrder,Integer> {
    @Resource
    private CustomerOrderMapper customerOrderMapper;

    public Map<String,Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerOrderQuery.getPage(),customerOrderQuery.getLimit());
        PageInfo<CustomerOrder> info = new PageInfo<>(customerOrderMapper.selectByParams(customerOrderQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }

    public Map<String, Object> queryOrderDetailByOrderId(Integer orderId) {
        return customerOrderMapper.queryOrderDetailByOrderId(orderId);
    }
}
