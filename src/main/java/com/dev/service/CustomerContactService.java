package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerContactMapper;
import com.dev.vo.CustomerContact;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/29 20:02
 * @ClassName CustomerContactService
 */
@Service
public class CustomerContactService extends BaseService<CustomerContact,Integer> {
    @Resource
    private CustomerContactMapper customerContactMapper;


}
