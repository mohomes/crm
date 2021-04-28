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
        customer.setState(0);
        AssertUtil.isTrue(customerMapper.insertSelective(customer)<1,"客户信息添加失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer){
        AssertUtil.isTrue(null==customer.getId(),"待更新记录不存在");
        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        checkCustomerName(customer.getName(),customer.getFr(),customer.getPhone());
        temp = customerMapper.queryCustomerName(customer.getName());
        // 判断客户记录 是否存在 且客户id是否与更新记录的id一致
        AssertUtil.isTrue(null!=temp &&!(temp.getId().equals(customer.getId())),"客户名称已存在，请重试");
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer)<1,"更新客户信息失败");
    }

    public void deleteCustomer(Integer id){
        AssertUtil.isTrue(null==id,"待删除记录不存在");
        Customer customer = customerMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==customer,"带删除记录不存在");
        customer.setUpdateDate(new Date());
        customer.setIsValid(0);
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer)<1,"删除失败");
    }

    private void checkCustomerName(String name, String fr, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"客户名称不能为空");
        AssertUtil.isTrue(customerMapper.queryCustomerName(name)!=null,"客户名称重复");
        AssertUtil.isTrue(StringUtils.isBlank(fr),"法人代表不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号码格式不正确");
    }
}
