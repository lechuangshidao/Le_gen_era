package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class HomePageHospital {

    /**
     * message : 成功！
     * state : 1
     * data : [{"longitude":"116.301523","latitude":"40.109692","h_img":null,"h_name":"上地宠物医院","tel":"010-55555555"},{"longitude":"116.301523","latitude":"40.109692","h_img":"/Public/Uploads/2017-07-25/5976bbdbc3068.png","h_name":"回龙观宠物医院","tel":"010-11111111"},{"longitude":"116.301523","latitude":"40.109692","h_img":null,"h_name":"昌平宠物医院","tel":"010-123456789"},{"longitude":"116.301523","latitude":"40.109692","h_img":null,"h_name":"上地宠物医院","tel":"010-789456132"},{"longitude":"116.301523","latitude":"40.109692","h_img":null,"h_name":"回龙观宠物医院","tel":"010-456123789"},{"longitude":"116.301523","latitude":"40.109692","h_img":"/Public/Uploads/2017-08-10/598bcb0e689db.jpg","h_name":"上海宠物医院","tel":"15134041905"},{"longitude":"116.301523","latitude":"40.109692","h_img":"/Public/Uploads/2017-08-10/598bcbd591e76.jpg","h_name":"云南宠物医院","tel":"010-58895888"}]
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
         * longitude : 116.301523
         * latitude : 40.109692
         * h_img : null
         * h_name : 上地宠物医院
         * tel : 010-55555555
         */

        private String longitude;
        private String latitude;
        private Object h_img;
        private String h_name;
        private String tel;

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getH_img() {
            return h_img;
        }

        public void setH_img(Object h_img) {
            this.h_img = h_img;
        }

        public String getH_name() {
            return h_name;
        }

        public void setH_name(String h_name) {
            this.h_name = h_name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
