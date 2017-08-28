package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class PetSeek {

    /**
     * message : 成功！
     * state : 1
     * data : [{"lon":"116.30038500","lat":"40.11192500","pet_img":"/adminchongai/Public/Uploads/2017-08-14/599111489fb99.jpg","pet_name":"小明","user_id":"87","km":"5km"},{"lon":"116.30641200","lat":"40.11014500","pet_img":"/adminchongai/Public/Uploads/2017-08-14/599113a4a5299.jpg","pet_name":"豆儿","user_id":"87","km":"5km"},{"lon":"116.29971100","lat":"40.11084900","pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","pet_name":"superman","user_id":"14","km":"5km"},{"lon":"116.30631200","lat":"40.11014500","pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911c178b963.jpg","pet_name":"gay","user_id":"17","km":"5km"},{"lon":"116.29468000","lat":"40.10471700","pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911c59becc7.jpg","pet_name":"大黄","user_id":"20","km":"5km"},{"lon":"116.29463300","lat":"40.10479500","pet_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","pet_name":"小灰","user_id":"24","km":"5km"},{"lon":"116.27933800","lat":"40.10481400","pet_img":"/chongai/uploads/user/2017-08-18/1503042190.png","pet_name":"呵呵","user_id":"35","km":"5km"},{"lon":"116.29467300","lat":"40.11141400","pet_img":"/adminchongai/Public/Uploads/2017-08-14/599154789efde.jpg","pet_name":"夏侯惇","user_id":"51","km":"5km"}]
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
         * lon : 116.30038500
         * lat : 40.11192500
         * pet_img : /adminchongai/Public/Uploads/2017-08-14/599111489fb99.jpg
         * pet_name : 小明
         * user_id : 87
         * km : 5km
         */

        private String lon;
        private String lat;
        private String pet_img;
        private String pet_name;
        private String user_id;
        private String km;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }
    }
}
