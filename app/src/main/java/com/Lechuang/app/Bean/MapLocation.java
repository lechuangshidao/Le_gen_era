package com.Lechuang.app.Bean;

/**
 * Created by Administrator on 2017/8/4.
 */

public class MapLocation {

    /**
     * message : 请求定位失败！
     * state : 0
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
}
