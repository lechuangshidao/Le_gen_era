package com.Lechuang.app.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android on 2017/8/2.
 */

public class MyPetIntegralTaskInfo implements Serializable{

    /**
     * message : 活动已完成!
     * state : 1
     * data : [{"title":"添加宠物","status":"3"},{"title":"发起一次宠物活动","status":""},{"title":"分享给好友","status":"5"}]
     */

    private String message;
    private String state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 添加宠物
         * status : 3
         */

        private String title;
        private String status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
