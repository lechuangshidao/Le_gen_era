package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class PetHospital {

    /**
     * message : 成功！
     * state : 1
     * data : {"pet":{"data":[{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911c59becc7.jpg","pet_name":"大黄","pet_tag":"母#撕逼","pet_age":"1","lon":"116.29468000","lat":"40.10471700","userpicture":"/chongai/uploads/user/2017-08-21/b1cCDG1503301450.png","nickname":"豆豆","id":"20","userlogin":"15010487565"},{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","pet_name":"小灰","pet_tag":"母#爱撕逼","pet_age":"1","lon":"116.29463300","lat":"40.10479500","userpicture":"/chongai/uploads/user/2017-08-21/CDGde21503306941.jpeg","nickname":"1234啊啊啊","id":"24","userlogin":"18764823183"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","nickname":"侯增宇","id":"63","userlogin":"13237093080","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29465900","lat":"40.10484500"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/599113a4a5299.jpg","nickname":"豆豆","id":"84","userlogin":"15727393984","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29464600","lat":"40.10479700"}],"type":"pet"},"hospital":{"data":[{"longitude":"116.30066300","latitude":"40.11220000","h_img":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","h_name":"回龙观宠物医院","tel":"18811561684"},{"longitude":"116.29993600","latitude":"40.11194500","h_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","h_name":"上海宠物医院","tel":"15710056095"},{"longitude":"116.30122000","latitude":"40.11190100","h_img":"/Public/Uploads/2017-08-24/599e8f627deb8.jpg","h_name":"美国宠物医院","tel":"15134041905"}],"type":"hospital"}}
     */

    private String message;
    private String state;
    private DataBeanXX data;

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

    public DataBeanXX getData() {
        return data;
    }

    public void setData(DataBeanXX data) {
        this.data = data;
    }

    public static class DataBeanXX {
        /**
         * pet : {"data":[{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911c59becc7.jpg","pet_name":"大黄","pet_tag":"母#撕逼","pet_age":"1","lon":"116.29468000","lat":"40.10471700","userpicture":"/chongai/uploads/user/2017-08-21/b1cCDG1503301450.png","nickname":"豆豆","id":"20","userlogin":"15010487565"},{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","pet_name":"小灰","pet_tag":"母#爱撕逼","pet_age":"1","lon":"116.29463300","lat":"40.10479500","userpicture":"/chongai/uploads/user/2017-08-21/CDGde21503306941.jpeg","nickname":"1234啊啊啊","id":"24","userlogin":"18764823183"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","nickname":"侯增宇","id":"63","userlogin":"13237093080","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29465900","lat":"40.10484500"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/599113a4a5299.jpg","nickname":"豆豆","id":"84","userlogin":"15727393984","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29464600","lat":"40.10479700"}],"type":"pet"}
         * hospital : {"data":[{"longitude":"116.30066300","latitude":"40.11220000","h_img":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","h_name":"回龙观宠物医院","tel":"18811561684"},{"longitude":"116.29993600","latitude":"40.11194500","h_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","h_name":"上海宠物医院","tel":"15710056095"},{"longitude":"116.30122000","latitude":"40.11190100","h_img":"/Public/Uploads/2017-08-24/599e8f627deb8.jpg","h_name":"美国宠物医院","tel":"15134041905"}],"type":"hospital"}
         */

        private PetBean pet;
        private HospitalBean hospital;

        public PetBean getPet() {
            return pet;
        }

        public void setPet(PetBean pet) {
            this.pet = pet;
        }

        public HospitalBean getHospital() {
            return hospital;
        }

        public void setHospital(HospitalBean hospital) {
            this.hospital = hospital;
        }

        public static class PetBean {
            /**
             * data : [{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/59911c59becc7.jpg","pet_name":"大黄","pet_tag":"母#撕逼","pet_age":"1","lon":"116.29468000","lat":"40.10471700","userpicture":"/chongai/uploads/user/2017-08-21/b1cCDG1503301450.png","nickname":"豆豆","id":"20","userlogin":"15010487565"},{"pet_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","pet_name":"小灰","pet_tag":"母#爱撕逼","pet_age":"1","lon":"116.29463300","lat":"40.10479500","userpicture":"/chongai/uploads/user/2017-08-21/CDGde21503306941.jpeg","nickname":"1234啊啊啊","id":"24","userlogin":"18764823183"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","nickname":"侯增宇","id":"63","userlogin":"13237093080","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29465900","lat":"40.10484500"},{"userpicture":"/adminchongai/Public/Uploads/2017-08-14/599113a4a5299.jpg","nickname":"豆豆","id":"84","userlogin":"15727393984","pet_img":"","pet_name":"","pet_tag":"","pet_age":"","lon":"116.29464600","lat":"40.10479700"}]
             * type : pet
             */

            private String type;
            private List<DataBean> data;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * pet_img : /adminchongai/Public/Uploads/2017-08-14/59911c59becc7.jpg
                 * pet_name : 大黄
                 * pet_tag : 母#撕逼
                 * pet_age : 1
                 * lon : 116.29468000
                 * lat : 40.10471700
                 * userpicture : /chongai/uploads/user/2017-08-21/b1cCDG1503301450.png
                 * nickname : 豆豆
                 * id : 20
                 * userlogin : 15010487565
                 */

                private String pet_img;
                private String pet_name;
                private String pet_tag;
                private String pet_age;
                private String lon;
                private String lat;
                private String userpicture;
                private String nickname;
                private String id;
                private String userlogin;

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

                public String getPet_tag() {
                    return pet_tag;
                }

                public void setPet_tag(String pet_tag) {
                    this.pet_tag = pet_tag;
                }

                public String getPet_age() {
                    return pet_age;
                }

                public void setPet_age(String pet_age) {
                    this.pet_age = pet_age;
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

                public String getUserpicture() {
                    return userpicture;
                }

                public void setUserpicture(String userpicture) {
                    this.userpicture = userpicture;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserlogin() {
                    return userlogin;
                }

                public void setUserlogin(String userlogin) {
                    this.userlogin = userlogin;
                }
            }
        }

        public static class HospitalBean {
            /**
             * data : [{"longitude":"116.30066300","latitude":"40.11220000","h_img":"/adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg","h_name":"回龙观宠物医院","tel":"18811561684"},{"longitude":"116.29993600","latitude":"40.11194500","h_img":"/adminchongai/Public/Uploads/2017-08-14/599150877bc6b.jpg","h_name":"上海宠物医院","tel":"15710056095"},{"longitude":"116.30122000","latitude":"40.11190100","h_img":"/Public/Uploads/2017-08-24/599e8f627deb8.jpg","h_name":"美国宠物医院","tel":"15134041905"}]
             * type : hospital
             */

            private String type;
            private List<DataBeanX> data;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<DataBeanX> getData() {
                return data;
            }

            public void setData(List<DataBeanX> data) {
                this.data = data;
            }

            public static class DataBeanX {
                /**
                 * longitude : 116.30066300
                 * latitude : 40.11220000
                 * h_img : /adminchongai/Public/Uploads/2017-08-14/59911406e4b46.jpg
                 * h_name : 回龙观宠物医院
                 * tel : 18811561684
                 */

                private String longitude;
                private String latitude;
                private String h_img;
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

                public String getH_img() {
                    return h_img;
                }

                public void setH_img(String h_img) {
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
    }
}
