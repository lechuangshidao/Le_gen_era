package com.Lechuang.app.Bean;

/**
 * Created by Administrator on 2017/8/11.
 */

public class PetInfo {

    /**
     * message : 成功！
     * state : 1
     * data : {"pet_name":"小明","pet_age":"1","pet_tag":"发情期#公"}
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
         * pet_name : 小明
         * pet_age : 1
         * pet_tag : 发情期#公
         */

        private String pet_name;
        private String pet_age;
        private String pet_tag;

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
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
