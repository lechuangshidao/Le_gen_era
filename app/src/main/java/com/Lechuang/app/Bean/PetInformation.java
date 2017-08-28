package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 宠物信息
 * Created by Administrator on 2017/8/3.
 */
public class PetInformation {

    /**
     * message : 成功！
     * state : 1
     * name : 哈士奇
     * data : [{"petid":"1","pet_name":"小明","pet_img":"update/img/1.jpg","km":"10千米内"},{"petid":"3","pet_name":"豆儿","pet_img":"update/img/1.jpg","km":"10千米内"},{"petid":"6","pet_name":"gay","pet_img":"update/img/1.jpg","km":"10千米内"}]
     */

    private String message;
    private int state;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * petid : 1
         * pet_name : 小明
         * pet_img : update/img/1.jpg
         * km : 10千米内
         */

        private String petid;
        private String pet_name;
        private String pet_img;
        private String km;

        public String getPetid() {
            return petid;
        }

        public void setPetid(String petid) {
            this.petid = petid;
        }

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

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "petid='" + petid + '\'' +
                    ", pet_name='" + pet_name + '\'' +
                    ", pet_img='" + pet_img + '\'' +
                    ", km='" + km + '\'' +
                    '}';
        }
    }
}
