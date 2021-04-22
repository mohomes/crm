package com.dev.model;

/**
 * @author 御风
 * @version V1.0
 * @Package com.dev.model
 * @Description
 * @date 2021/4/19 14:33
 * @ClassName UserModel
 */
public class UserModel {
//    private Integer userId;
    private String userName;
    private String trueName;

    private String userIdStr;

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
