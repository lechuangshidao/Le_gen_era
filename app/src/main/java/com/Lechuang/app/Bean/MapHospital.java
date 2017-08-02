package com.Lechuang.app.Bean;

/**
 * Created by Administrator on 2017/7/31.
 */

public class MapHospital {
        public class Data{
            private String lng;
            private String lat;
            private String token;

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "lng='" + lng + '\'' +
                        ", lat='" + lat + '\'' +
                        ", token='" + token + '\'' +
                        '}';
            }
        }
    }

