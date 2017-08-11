package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class PetSeek {

    /**
     * message : 搜索成功
     * state : 1
     * data : [{"user_id":"12","pet_type":"狗","pet_img":"宠物头像","pet_name":"周瑜","userpicture":"http://q.qlogo.cn/qqapp/101394031/E5A42155BF9CC51164A7DD5322A3B2C3/40","distance":"6610.48千米以内"}]
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

    @Override
    public String toString() {
        return "PetSeek{" +
                "message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * user_id : 12
         * pet_type : 狗
         * pet_img : 宠物头像
         * pet_name : 周瑜
         * userpicture : http://q.qlogo.cn/qqapp/101394031/E5A42155BF9CC51164A7DD5322A3B2C3/40
         * distance : 6610.48千米以内
         */

        private String user_id;
        private String pet_type;
        private String pet_img;
        private String pet_name;
        private String userpicture;
        private String distance;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPet_type() {
            return pet_type;
        }

        public void setPet_type(String pet_type) {
            this.pet_type = pet_type;
        }

        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;
        }

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public String getUserpicture() {
            return userpicture;
        }

        public void setUserpicture(String userpicture) {
            this.userpicture = userpicture;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
