package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerLossMapper;
import com.dev.dao.CustomerReprieveMapper;
import com.dev.utils.AssertUtil;
import com.dev.vo.CustomerReprieve;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/5/1 4:55
 * @ClassName CustomerReprieve
 */
@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve,Integer> {

    @Resource
    private CustomerReprieveMapper customerReprieveMapper;

    @Resource
    private CustomerLossMapper customerLossMapper;

    /**
     * 参数校验
     * 设置默认值
     * 查询受影响的行数
     * 添加暂缓措施
     * @param customerReprieve
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerReprieve(CustomerReprieve customerReprieve){
        checkParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerReprieveMapper.insertSelective(customerReprieve)<1,"添加暂缓措施失败");
    }

    private void checkParams(Integer lossId, String measure) {
        AssertUtil.isTrue(null==lossId || customerLossMapper.selectByPrimaryKey(lossId)==null,"流失客户信息不存在");
        AssertUtil.isTrue(StringUtils.isBlank(measure),"暂缓措施不能为空");
    }

    /**
     * 修改暂缓措施
     * @param customerReprieve
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerReprieve(CustomerReprieve customerReprieve){
        AssertUtil.isTrue(null==customerReprieve.getId() || customerReprieveMapper.selectByPrimaryKey(customerReprieve.getId())==null,"待更新记录不存在");
        checkParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1,"修改暂缓措施失败");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        AssertUtil.isTrue(null==id,"待删除记录不存在");
        CustomerReprieve reprieve = customerReprieveMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==reprieve,"待删除记录不存在");
        reprieve.setUpdateDate(new Date());
        reprieve.setIsValid(0);
        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(reprieve)<1,"删除暂缓数据失败");
    }
}
