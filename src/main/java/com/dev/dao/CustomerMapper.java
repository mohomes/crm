package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.Customer;

import java.util.List;

public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    Customer queryCustomerName(String name);

    /**
     * 查询待流失的客户列表
     * @return
     */
    List<Customer> queryLossCustomer();

    /**
     * 批量更新客户状态
     * @param lossCusIds
     * @return
     */
    int updateStateByCusIds(List<Integer> lossCusIds);
}