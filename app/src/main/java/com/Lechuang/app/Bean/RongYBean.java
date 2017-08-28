package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/8/16.
 */

public class RongYBean implements Serializable{

    /**
     * message : 成功！
     * state : 1
     * data : {"userid":"84","r_token":"9WVXgx+Y1FjvGxYm/tkrcJZ2mmDMhuBKBiPIWUYPkB76ei4oFEyU8fHzWsNGbyyAx3UfamOeHbI="}
     */

    private String message;
    private int state;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : 84
         * r_token : 9WVXgx+Y1FjvGxYm/tkrcJZ2mmDMhuBKBiPIWUYPkB76ei4oFEyU8fHzWsNGbyyAx3UfamOeHbI=
         */

        private String userid;
        private String r_token;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getR_token() {
            return r_token;
        }

        public void setR_token(String r_token) {
            this.r_token = r_token;
        }
    }
}
