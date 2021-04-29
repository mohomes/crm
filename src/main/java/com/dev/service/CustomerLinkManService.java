package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerLinkManMapper;
import com.dev.vo.CustomerLinkMan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/29 20:03
 * @ClassName CustomerLinkManService
 */
@Service
public class CustomerLinkManService extends BaseService<CustomerLinkMan,Integer> {

    @Resource
    private CustomerLinkManMapper customerLinkManMapper;


}
