package com.Lechuang.app.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android on 2017/8/1.
 */

public class MyPetLocationManageInfo implements Serializable {
    private String message;
    private String state;
    private List<MyPetLocationManageData> data;
    public class MyPetLocationManageData{
        private String address_id;
        private String consignee;
        private String phone;
        private String address;
        private String user_id;
        private String pretermit;

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

    public List<MyPetLocationManageData> getData() {
        return data;
    }

    public void setData(List<MyPetLocationManageData> data) {
        this.data = data;
    }
}
