package com.dev.dao;

import com.dev.base.BaseMapper;
import com.dev.vo.Customer;

public interface CustomerMapper extends BaseMapper<Customer,Integer> {

    Customer queryCustomerName(String name);
}