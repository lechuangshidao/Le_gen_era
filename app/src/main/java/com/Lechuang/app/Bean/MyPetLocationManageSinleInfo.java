package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/8/1.
 */

public class MyPetLocationManageSinleInfo implements Serializable {
    private String message;
    private String state;
    private MyPetLocationManageSingleData data;
    public class MyPetLocationManageSingleData{
        private String address_id;
        private String consignee;
        private String phone;
        private String address;
        private String user_id;
        private String pretermit;
        private String x_address;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPretermit() {
            return pretermit;
        }

        public void setPretermit(String pretermit) {
            this.pretermit = pretermit;
        }

        public String getX_address() {
            return x_address;
        }

        public void setX_address(String x_address) {
            this.x_address = x_address;
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

    public MyPetLocationManageSingleData getData() {
        return data;
    }

    public void setData(MyPetLocationManageSingleData data) {
        this.data = data;
    }
}
