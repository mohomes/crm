package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerMapper;
import com.dev.query.CustomerQuery;
import com.dev.utils.AssertUtil;
import com.dev.utils.PhoneUtil;
import com.dev.vo.Customer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String,Object> queryCustomerByParams(CustomerQuery customerQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerQuery.getPage(),customerQuery.getLimit());
        PageInfo<Customer> info = new PageInfo<>(customerMapper.selectByParams(customerQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }

    /**
     *  1、参数校验
     *      客户名称 非空 唯一性
     * @param customer
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(Customer customer){
        checkCustomerName(customer.getName(),customer.getFr(),customer.getPhone());

        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        String khno ="KH"+System.currentTimeMillis();
        customer.setKhno(khno);
        AssertUtil.isTrue(customerMapper.insertSelective(customer)<1,"客户信息添加失败");
    }

    private void checkCustomerName(String name, String fr, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"客户名称不能为空");
        AssertUtil.isTrue(customerMapper.queryCustomerName(name)!=null,"客户名称重复");
        AssertUtil.isTrue(StringUtils.isBlank(fr),"法人代表不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号码格式不正确");
    }
}
