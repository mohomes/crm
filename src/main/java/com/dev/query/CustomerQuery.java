package com.dev.query;

import com.dev.base.BaseQuery;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.query
 * @Description
 * @date 2021/4/27 20:14
 * @ClassName CustomerQuery
 */
public class CustomerQuery extends BaseQuery {
    private String customerName;  // 客户名称
    private String customerNo; // 客户编号
    private String level; // 客户级别

    private String time; // 订单时间

    private Integer type;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
