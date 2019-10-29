package com.mumu.vo;

import java.io.Serializable;

/**
 * @author Huangxulin
 * @date 2019/9/4 - 19:11
 */
public class Member implements Serializable {
    private String phone, password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
