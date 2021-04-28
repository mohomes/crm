package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.OrderDetailsMapper;
import com.dev.query.OrderDetailsQuery;
import com.dev.vo.CustomerOrder;
import com.dev.vo.OrderDetails;
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
 * @date 2021/4/29 5:27
 * @ClassName OrderDetailsService
 */
@Service
public class OrderDetailsService extends BaseService<OrderDetails,Integer> {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    public Map<String, Object> queryOrderDetailByParams(OrderDetailsQuery orderDetailsQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(orderDetailsQuery.getPage(),orderDetailsQuery.getLimit());
        PageInfo<OrderDetails> info = new PageInfo<>(orderDetailsMapper.selectByParams(orderDetailsQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }
}
