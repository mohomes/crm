package com.dev.query;

import com.dev.base.BaseQuery;

/**
 * @author yebai
 * @version V1.0
 * @Package com.dev.query
 * @Description
 * @date 2021/5/1 13:05
 * @ClassName CustomerServeQuery
 */
public class CustomerServeQuery extends BaseQuery {

    private String customer;  // 客户名称
    private Integer serveType;   // 服务类型
    /**
     * 服务状态  服务创建=fw_001 服务分配=fw_002 服务处理=fw_003
     *             服务反馈=fw_004  服务归档=fw_005
      */
    private String state;

    // 分配人
    private Integer assigner;

    public Integer getAssigner() {
        return assigner;
    }

    public void setAssigner(Integer assigner) {
        this.assigner = assigner;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getServeType() {
        return serveType;
    }

    public void setServeType(Integer serveType) {
        this.serveType = serveType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

