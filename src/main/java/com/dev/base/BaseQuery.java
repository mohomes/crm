package com.dev.base;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.base
 * @Description
 * @date 2021/4/18 22:05
 * @ClassName BaseQuery
 */
public class BaseQuery {

    private Integer page =1;
    private Integer limit =10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
