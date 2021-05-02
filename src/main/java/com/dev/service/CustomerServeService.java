package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerMapper;
import com.dev.dao.CustomerServeMapper;
import com.dev.dao.UserMapper;
import com.dev.enums.CustomerServeStatus;
import com.dev.query.CustomerQuery;
import com.dev.utils.AssertUtil;
import com.dev.vo.CustomerServe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author yebai
 * @version V1.0
 * @Package com.dev.service
 * @Description
 * @date 2021/5/1 13:04
 * @ClassName CustomerServeService
 */
@Service
public class CustomerServeService extends BaseService<CustomerServe,Integer> {

    @Resource
    private CustomerServeMapper customerServeMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private UserMapper userMapper;


    /**
     *  参数校验
     *      1、服务类型不能为空
     *      2、客户名称不能为空
     *      3、请求内容不能为空
     *  设置默认值
     *      服务状态
     *          服务创建状态 fw_001
     *  执行添加操作返回受影响的行数
     * @param customerServe
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerServe(CustomerServe customerServe){
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCustomer()),"客户名称不能为空");
        AssertUtil.isTrue(customerMapper.queryCustomerName(customerServe.getCustomer())==null,
                "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()),"请选择服务类型");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()),"服务请求内容不能为空");

        customerServe.setState(CustomerServeStatus.CREATED.getState());
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        AssertUtil.isTrue(customerServeMapper.insertSelective(customerServe)<1,"服务创建失败");
    }

    /**
      * 分配/处理/反馈
     *  1、参数校验
     *      客户服务id
     *          非空 记录必须存在
     *      客户服务状态
     *          如果服务状态 是分配状态 fw_002
     *              分配人 非空 分配用户存在
     *              分配时间
     *              更新时间
     *          如果服务状态 是处理状态 fw_003
     *              服务处理内容
     *                  非空
     *              服务处理时间
     *                  系统当前时间
     *               更新时间
     *          如果服务状态 是反馈状态 fw_004
     *               服务反馈内容  非空
     *               服务满意度  非空
     *               服务状态 设置为归档状态
     * @param customerServe
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerServeState(CustomerServe customerServe){
        AssertUtil.isTrue(customerServe.getId()==null
                || customerServeMapper.selectByPrimaryKey(customerServe.getId())==null,"待更新的服务状态不存在");
        if (CustomerServeStatus.ASSIGNED.getState().equals(customerServe.getState())){
            checkAssignParams(customerServe);
        }else if(CustomerServeStatus.PROCEED.getState().equals(customerServe.getState())){
            checkProceedParams(customerServe);
        }else if(CustomerServeStatus.FEED_BACK.getState().equals(customerServe.getState())){
            checkFeedBackParams(customerServe);
        }
        customerServe.setUpdateDate(new Date());
        AssertUtil.isTrue(customerServeMapper.updateByPrimaryKeySelective(customerServe)<1,"服务状态更新失败");
    }

    // 服务反馈
    private void checkFeedBackParams(CustomerServe customerServe) {
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()),"服务反馈内容不存在");
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getMyd()),"请选择服务反馈满意度");
        // 设置服务状态为 归档状态 fw_005
        customerServe.setState(CustomerServeStatus.ARCHIVED.getState());
    }

    // 服务处理
    private void checkProceedParams(CustomerServe customerServe) {
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()),"服务处理内容不能为空");
        customerServe.setServiceProceTime(new Date());

    }

    // 服务分配
    private void checkAssignParams(CustomerServe customerServe) {
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getAssigner()),"待分配用户不存在");
        AssertUtil.isTrue(userMapper.selectByPrimaryKey(Integer.parseInt(customerServe.getAssigner()))==null,"待分配用户不存在");
        customerServe.setAssignTime(new Date());
    }


}
