package com.Lechuang.app.Bean;

import java.io.Serializable;
import java.util.List;

import www.xcd.com.mylibrary.entity.BaseData;

/**
 * Created by Android on 2017/7/20.
 */

public class PetMessageInfo implements Serializable {
    private String message;
    private String state;
    private List<PetMessageData> data;
    public class PetMessageData extends BaseData {
        private String pet_name;
        private String pet_age;
        private String pet_tag;
        private String pet_img;
        private String pet_id;
        private String pet_type;

        public String getPet_type() {
            return pet_type;
        }

        public void setPet_type(String pet_type) {
            this.pet_type = pet_type;
        }

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

        public String getPet_img() {
            return pet_img;
        }

        public void setPet_img(String pet_img) {
            this.pet_img = pet_img;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }
    }
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

    public List<PetMessageData> getData() {
        return data;
    }

    public void setData(List<PetMessageData> data) {
        this.data = data;
    }
}
