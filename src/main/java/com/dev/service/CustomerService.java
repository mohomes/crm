package com.dev.service;

import com.dev.base.BaseService;
import com.dev.dao.CustomerLossMapper;
import com.dev.dao.CustomerMapper;
import com.dev.dao.CustomerOrderMapper;
import com.dev.query.CustomerQuery;
import com.dev.utils.AssertUtil;
import com.dev.utils.PhoneUtil;
import com.dev.vo.Customer;
import com.dev.vo.CustomerLoss;
import com.dev.vo.CustomerOrder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private CustomerOrderMapper customerOrderMapper;

    @Resource
    private CustomerLossMapper customerLossMapper;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void customerLoss(){

        List<Customer> lossCustomers = customerMapper.queryLossCustomer();
        if (lossCustomers!=null && lossCustomers.size()>0){
            //
            List<Integer> lossCusIds = new ArrayList<>();
            // 定义一个集合用于接收流失客户的列表
            List<CustomerLoss> lossList = new ArrayList<>();
            lossCustomers.forEach(customer->{
                CustomerLoss loss = new CustomerLoss();
                loss.setCreateDate(new Date());
                // 客户编号
                loss.setCusNo(customer.getKhno());
                // 客户经理
                loss.setCusManager(customer.getCusManager());
                // 客户名称
                loss.setCusName(customer.getName());
                //
                loss.setIsValid(1);
                loss.setUpdateDate(new Date());
                // 客户流失状态 0=暂缓流失状态 1=确认流失状态
                loss.setState(0);
                // 客户最后下单时间
                // 通过客户id查询最后的订单记录（最后一条）
                CustomerOrder customerOrder = customerOrderMapper.queryLossCustomerByCusId(customer.getId());
                // 如果客户存在 则设置客户的最后下单时间
                if (customerOrder!=null){
                    loss.setConfirmLossTime(customerOrder.getOrderDate());
                }
                lossList.add(loss);
                // 将对应的流失id设置到对应的集合中
                lossCusIds.add(customer.getId());
            });
            AssertUtil.isTrue(customerLossMapper.insertBatch(lossList)!=lossList.size(),"客户流失记录转移失败");
            /*批量更新客户的流失状态*/
            AssertUtil.isTrue(customerMapper.updateStateByCusIds(lossCusIds)!=lossCusIds.size(),"客户流失记录转移失败");
        }

    }

    public Map<String,Object> queryCustomerContributionByParams(CustomerQuery customerQuery){
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerQuery.getPage(),customerQuery.getLimit());
        PageInfo<Map<String,Object>> info =
                new PageInfo<Map<String,Object>>(customerMapper.queryCustomerContributionByParams(customerQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",info.getTotal());
        map.put("data",info.getList());
        return map;
    }


    public Map<String,Object> countCustomerMake(){
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> maps = customerMapper.countCustomerMake();
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();

        if (maps!=null && maps.size()>0) {
            maps.forEach(m->{
                data1.add(m.get("level").toString());
                // total 数据设置到Y轴上
                data2.add(m.get("total").toString());
            });
        }

        map.put("data1",data1);
        map.put("data2",data2);
        return map;
    }

    public Map<String, Object> countCustomerMake02() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> maps = customerMapper.countCustomerMake();
        // 饼状图数据 数组（字符串对象）
        ArrayList<String> data1 = new ArrayList<>();
        // 数组（对象）
        ArrayList<Map<String,Object>> data2 = new ArrayList<>();

        if (maps!=null && maps.size()>0) {
            maps.forEach(m->{
                data1.add(m.get("level").toString());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name",m.get("level"));
                hashMap.put("value",m.get("total"));
                data2.add(hashMap);
            });
        }

        map.put("data1",data1);
        map.put("data2",data2);
        return map;
    }
}
