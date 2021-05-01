package com.dev.query;

import com.dev.base.BaseQuery;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.query
 * @Description
 * @date 2021/5/1 4:58
 * @ClassName CustomerReprieveQuery
 */
public class CustomerReprieveQuery extends BaseQuery {
    private Integer lossId;

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
