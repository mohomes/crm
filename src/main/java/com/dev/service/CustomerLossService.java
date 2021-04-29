package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerLossMapper;
import com.dev.vo.CustomerLoss;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/4/30 5:33
 * @ClassName CustomerLoss
 */
@Service
public class CustomerLossService extends BaseService<CustomerLoss,Integer> {
    @Resource
    private CustomerLossMapper customerLossMapper;
}
