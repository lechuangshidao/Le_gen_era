package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 宠物医院
 * Created by Administrator on 2017/7/31.
 */

public class MapHospital {

    /**
     * data : [{"bustime":"7：00-12：00","hosname":"昌平医院","hospicture":"医院图片","meter":"700"},{"bustime":"9：00-20：00","hosname":"京华宠物医院","hospicture":"医院图片","meter":"1100"}]
     * message : 查询成功!
     * state : 1
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
         * bustime : 7：00-12：00
         * hosname : 昌平医院
         * hospicture : 医院图片
         * meter : 700
         */

        private String bustime;
        private String hosname;
        private String hospicture;
        private String meter;

        public String getBustime() {
            return bustime;
        }

        public void setBustime(String bustime) {
            this.bustime = bustime;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getHospicture() {
            return hospicture;
        }

        public void setHospicture(String hospicture) {
            this.hospicture = hospicture;
        }

        public String getMeter() {
            return meter;
        }

        public void setMeter(String meter) {
            this.meter = meter;
        }
    }
}

