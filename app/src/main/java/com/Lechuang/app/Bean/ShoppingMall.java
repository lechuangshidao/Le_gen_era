package com.Lechuang.app.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ShoppingMall {

    /**
     * message : 成功！
     * state : 1
     * data : [{"id":"1","shopimg":"/Public/Uploads/2017-07-23/5974227da673c.png","shopname":"毛绒玩具","integral":"100","addtime":"1500785129","state":"1","sort":"1","hot":"1"},{"id":"2","shopimg":"/Public/Uploads/2017-07-23/5974227da673c.png","shopname":"狗狗玩具","integral":"20","addtime":"1500860099","state":"1","sort":"1","hot":"1"},{"id":"5","shopimg":"/Public/Uploads/2017-07-23/59742814ef1d3.png","shopname":"磨牙玩具","integral":"50","addtime":"1500784661","state":"1","sort":"5","hot":"1"},{"id":"8","shopimg":"/Public/Uploads/2017-07-23/5974227da673c.png","shopname":"毛绒玩具","integral":"100","addtime":"1500785129","state":"1","sort":"1","hot":"1"},{"id":"9","shopimg":"/Public/Uploads/2017-07-23/5974227da673c.png","shopname":"狗狗玩具","integral":"20","addtime":"1500860099","state":"1","sort":"1","hot":"1"},{"id":"10","shopimg":"/Public/Uploads/2017-07-23/59742814ef1d3.png","shopname":"磨牙玩具","integral":"50","addtime":"1500784661","state":"1","sort":"5","hot":"1"}]
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
         * id : 1
         * shopimg : /Public/Uploads/2017-07-23/5974227da673c.png
         * shopname : 毛绒玩具
         * integral : 100
         * addtime : 1500785129
         * state : 1
         * sort : 1
         * hot : 1
         */

        private String id;
        private String shopimg;
        private String shopname;
        private String integral;
        private String addtime;
        private String state;
        private String sort;
        private String hot;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopimg() {
            return shopimg;
        }

        public void setShopimg(String shopimg) {
            this.shopimg = shopimg;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }
    }
}

