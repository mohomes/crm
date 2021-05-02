package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.query.CustomerQuery;
import com.dev.vo.Customer;

import java.util.List;
import java.util.Map;

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

    // 查询客户贡献数据
    List<Map<String,Object>> queryCustomerContributionByParams(CustomerQuery customerQuery);
    // 查询客户构成
    List<Map<String,Object>> countCustomerMake();
}