package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */

public class HomePager {

    /**
     * message : 成功！
     * state : 1
     * data : [{"id":"1","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.301529","lat":"40.109695"},{"id":"3","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.301529","lat":"40.109695"},{"id":"4","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.301529","lat":"40.109695"},{"id":"5","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.300632","lat":"40.110039"},{"id":"6","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.300632","lat":"40.110039"},{"id":"7","pet_img":"update/img/1.jpg","userpicture":"","lon":"116.300632","lat":"40.110039"}]
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
         * id : 1
         * pet_img : update/img/1.jpg
         * userpicture :
         * lon : 116.301529
         * lat : 40.109695
         */

        private String id;
        private String pet_img;
        private String userpicture;
        private String lon;
        private String lat;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;
        }

        public String getUserpicture() {
            return userpicture;
        }

        public void setUserpicture(String userpicture) {
            this.userpicture = userpicture;
        }

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
    }
}
