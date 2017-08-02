package com.Lechuang.app.Bean;

/**
 * Created by Administrator on 2017/8/1.
 */

public class VerificationCode {

    /**
     * message : 验证成功!
     * state : 1
     * data : {}
     */

    private String message;
    private String state;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
