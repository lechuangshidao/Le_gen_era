package com.Lechuang.app.Bean;

/**
 * 积分兑换
 * Created by Administrator on 2017/8/1.
 */

public class CreditsExchange {

    /**
     * message : 兑换成功！
     * state : 1
     * data :
     */

    private String message;
    private String state;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CreditsExchange{" +
                "message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
