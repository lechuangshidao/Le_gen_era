package com.Lechuang.app.Bean;

import java.io.Serializable;

/**
 * Created by Android on 2017/7/28.
 */

public class RongYunUserInfo implements Serializable {

    private String message;
    private String state;
    private FragmentMeInfoData data;

    public class FragmentMeInfoData {
        private String user_id;
        private String nickname;
        private String userpicture;
        private String integral;
        private String fensi;
        private String myguanzhu;
        private String sex;
        private String userbirthday;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getFensi() {
            return fensi;
        }

        public void setFensi(String fensi) {
            this.fensi = fensi;
        }

        public String getMyguanzhu() {
            return myguanzhu;
        }

        public void setMyguanzhu(String myguanzhu) {
            this.myguanzhu = myguanzhu;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUserbirthday() {
            return userbirthday;
        }

        public void setUserbirthday(String userbirthday) {
            this.userbirthday = userbirthday;
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

    public FragmentMeInfoData getData() {
        return data;
    }

    public void setData(FragmentMeInfoData data) {
        this.data = data;
    }
}
