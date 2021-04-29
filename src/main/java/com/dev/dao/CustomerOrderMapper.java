package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.CustomerOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<CustomerOrder,Integer> {

    Map<String, Object> queryOrderDetailByOrderId(Integer orderId);

    CustomerOrder queryLossCustomerByCusId(Integer cusId);
}