package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 宠物详情
 * Created by Administrator on 2017/8/3.
 */

public class PetDetails {

    /**
     * message : 成功！
     * state : 1
     * data : [{"pet_name":"小明","pet_img":"update/img/1.jpg","type_name":"哈士奇","pet_age":"1","pet_tag":"发情期#公"}]
     */

    private String message;
    private int state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pet_name : 小明
         * pet_img : update/img/1.jpg
         * type_name : 哈士奇
         * pet_age : 1
         * pet_tag : 发情期#公
         */

        private String pet_name;
        private String pet_img;
        private String type_name;
        private String pet_age;
        private String pet_tag;

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getPet_age() {
            return pet_age;
        }

        public void setPet_age(String pet_age) {
            this.pet_age = pet_age;
        }

        public String getPet_tag() {
            return pet_tag;
        }

        public void setPet_tag(String pet_tag) {
            this.pet_tag = pet_tag;
        }
    }
}
