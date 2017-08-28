package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/8/18.
 */

public class UpImageBean implements Serializable{

    /**
     * message : 上传成功!
     * state : 1
     * data : {"filename":"45816806_47.jpg","picurl":"/chongai/uploads/user/2017-08-10/45816806_47.jpg"}
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
         * filename : 45816806_47.jpg
         * picurl : /chongai/uploads/user/2017-08-10/45816806_47.jpg
         */

        private String filename;
        private String picurl;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
