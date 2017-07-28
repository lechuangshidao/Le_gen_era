package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/7/28.
 */

public class LoginInfo implements Serializable {
    private String message;
    private String state;
    private LoginData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
