package com.Lechuang.app.Bean;

/**
 * Created by Administrator on 2017/8/1.
 */

public class SmsRegistration {

    /**
     * message : 手机注册成功!
     * state : 1
     * data : {"token":"d651IDWvoZTU45fJY2pmNdmMnve4//S+ZRA4xSpowStfLXXoU253QjqrXxr3rtWRS+m8","user_id":"24"}
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
        /**
         * token : d651IDWvoZTU45fJY2pmNdmMnve4//S+ZRA4xSpowStfLXXoU253QjqrXxr3rtWRS+m8
         * user_id : 24
         */

        private String token;
        private String user_id;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
