package com.yjxxt.crs.query;

import com.yjxxt.crs.base.BaseQuery;

public class UserQuery extends BaseQuery {
    // 用户名
    private String userName;

    // 电话
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
