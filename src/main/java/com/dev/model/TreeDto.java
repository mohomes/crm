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
    private boolean checked =false; //true勾选 false不勾选

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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
