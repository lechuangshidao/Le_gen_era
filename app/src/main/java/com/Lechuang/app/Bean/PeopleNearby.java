package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 附近宠物
 * Created by Administrator on 2017/8/3.
 */

public class PeopleNearby {

    /**
     * message : 成功！
     * state : 1
     * data : [{"petid":"69","pet_name":"骨头","pet_img":"update/img/1.jpg","nickname":"啊啊啊啊啊啊啊啊","userpicture":"http://q.qlogo.cn/qqapp/101394031/E5A42155BF9CC51164A7DD5322A3B2C3/40","type_name":"狗","km":"1千米以内"},{"petid":"90","pet_name":"豆豆","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"哈士奇","km":"1千米以内"},{"petid":"93","pet_name":"狗蛋","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"哈士奇","km":"1千米以内"},{"petid":"94","pet_name":"起个好名行不","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"狗","km":"1千米以内"},{"petid":"95","pet_name":"服了","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"猫","km":"1千米以内"},{"petid":"96","pet_name":"大头","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"大型犬","km":"1千米以内"},{"petid":"97","pet_name":"天天","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"中型犬","km":"1千米以内"},{"petid":"98","pet_name":"一库","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"哈士奇","km":"1千米以内"},{"petid":"99","pet_name":"EZ","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"狗","km":"1千米以内"},{"petid":"100","pet_name":"哈哈","pet_img":"update/img/1.jpg","nickname":"咋说的","userpicture":"阿斯顿","type_name":"狗","km":"1千米以内"}]
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

    @Override
    public String toString() {
        return "PeopleNearby{" +
                "message='" + message + '\'' +
                ", state=" + state +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * petid : 69
         * pet_name : 骨头
         * pet_img : update/img/1.jpg
         * nickname : 啊啊啊啊啊啊啊啊
         * userpicture : http://q.qlogo.cn/qqapp/101394031/E5A42155BF9CC51164A7DD5322A3B2C3/40
         * type_name : 狗
         * km : 1千米以内
         */

        private String petid;
        private String pet_name;
        private String pet_img;
        private String nickname;
        private String userpicture;
        private String type_name;
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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUserpicture() {
            return userpicture;
        }

        public void setUserpicture(String userpicture) {
            this.userpicture = userpicture;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }
    }
}
