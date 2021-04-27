package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerMapper;
import com.dev.vo.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/27 20:11
 * @ClassName CustomerService
 */
@Service
public class CustomerService extends BaseService<Customer,Integer> {
    @Resource
    private CustomerMapper customerMapper;
}
