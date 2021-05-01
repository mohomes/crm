package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerLossMapper;
import com.dev.utils.AssertUtil;
import com.dev.vo.CustomerLoss;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import javax.annotation.Resource;
import java.util.Date;

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

    /**
     * 更新客户的流失状态
     * 1、设置参数的默认值
     *      流失原因不能为空
     *
     * 2、设置参数的默认值
     *      流失状态 state=1 0=暂缓流失 1=确认流失
     * 3、执行更新操作，判断受影响的行数
     * @param id
     * @param lossReason
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerLossState(Integer id, String lossReason) {
        AssertUtil.isTrue(null==id,"待确认流失客户不存在");
        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==customerLoss,"待确认流失客户不存在");
        AssertUtil.isTrue(StringUtils.isBlank(lossReason),"流失原因不能为空");
        customerLoss.setLossReason(lossReason);
        customerLoss.setState(1);
        customerLoss.setUpdateDate(new Date());
        customerLoss.setConfirmLossTime(new Date());
        AssertUtil.isTrue(customerLossMapper.updateByPrimaryKeySelective(customerLoss)<1,"确认流失客户失败");
    }
}
