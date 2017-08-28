package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 点击搜索按顶部搜索框
 * Created by Administrator on 2017/8/17.
 */

public class SeekPet {

    /**
     * message : 成功！
     * state : 1
     * data : [{"lon":"116.301238","lat":"40.11089","pet_img":"update/img/1.jpg","pet_name":"小明"},{"lon":"116.301635","lat":"40.110752","pet_img":"update/img/1.jpg","pet_name":"豆儿"},{"lon":"116.301635","lat":"40.110753","pet_img":"update/img/1.jpg","pet_name":"superman"},{"lon":"116.301635","lat":"40.110754","pet_img":"update/img/1.jpg","pet_name":"巨人"},{"lon":"116.301635","lat":"40.110753","pet_img":"update/img/1.jpg","pet_name":"gay"},{"lon":"116.301635","lat":"40.110752","pet_img":"update/img/1.jpg","pet_name":"大黄"},{"lon":"116.301634","lat":"40.110752","pet_img":"update/img/1.jpg","pet_name":"小灰"},{"lon":"116.301634","lat":"40.110752","pet_img":"update/img/1.jpg","pet_name":"小小"}]
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
         * lon : 116.301238
         * lat : 40.11089
         * pet_img : update/img/1.jpg
         * pet_name : 小明
         */

        private String lon;
        private String lat;
        private String pet_img;
        private String pet_name;

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
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
    }
}
