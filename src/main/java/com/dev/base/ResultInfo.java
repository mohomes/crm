package com.dev.base;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.base
 * @Description
 * @date 2021/4/18 22:07
 * @ClassName ResultInfo
 */
public class ResultInfo {

    private String msg = "success";

    private Integer code = 200;

    private Object result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
