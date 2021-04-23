package com.dev.model;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.model
 * @Description
 * @date 2021/4/23 0:22
 * @ClassName TreeModel
 */
public class TreeDto {
    private Integer id;
    private Integer pId;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
