package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by 1 on 2016/1/13.
 */
public class Friend implements Serializable {

    public String userIcon;
    private String name;
    private String userid;

    public Friend(String userid, String name, String userIcon) {
        this.userIcon = userIcon;
        this.name = name;
        this.userid = userid;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userIcon='" + userIcon + '\'' +
                ", name='" + name + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
