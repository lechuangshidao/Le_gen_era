package com.Lechuang.app.Bean;

import java.util.List;

/**
 * 宠物分类
 * Created by Administrator on 2017/8/3.
 */

public class PetClassification {

    /**
     * message : 成功！
     * state : 1
     * data : [{"id":"1","type_name":"狗","pid":"0","addtime":"12345","petone":[{"id":"3","type_name":"大型犬","pid":"1","addtime":"12345","pettwo":[{"id":"12","type_name":"松狮","pid":"3","addtime":"12345"},{"id":"13","type_name":"牧羊犬","pid":"3","addtime":"12345"}]},{"id":"4","type_name":"中型犬","pid":"1","addtime":"12345","pettwo":[{"id":"10","type_name":"柴犬","pid":"4","addtime":"12345"},{"id":"11","type_name":"哈士奇","pid":"4","addtime":"12345"}]},{"id":"5","type_name":"小型犬","pid":"1","addtime":"12345","pettwo":[{"id":"6","type_name":"萨摩耶","pid":"5","addtime":"12345"},{"id":"9","type_name":"吉娃娃","pid":"5","addtime":"12345"}]}]},{"id":"2","type_name":"猫","pid":"0","addtime":"12345","petone":[{"id":"14","type_name":"长毛猫","pid":"2","addtime":"12345","pettwo":[{"id":"16","type_name":"波斯猫","pid":"14","addtime":"12345"},{"id":"17","type_name":"挪威森林猫","pid":"14","addtime":"12345"},{"id":"18","type_name":"缅因猫","pid":"14","addtime":"12345"},{"id":"19","type_name":"伯曼猫","pid":"14","addtime":"12345"}]},{"id":"15","type_name":"短毛猫","pid":"2","addtime":"12345","pettwo":[{"id":"20","type_name":"暹罗猫","pid":"15","addtime":"12345"},{"id":"21","type_name":"俄罗斯蓝猫","pid":"15","addtime":"12345"},{"id":"22","type_name":"孟买猫","pid":"15","addtime":"12345"},{"id":"23","type_name":"中国狸花猫","pid":"15","addtime":"12345"}]}]}]
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
         * type_name : 狗
         * pid : 0
         * addtime : 12345
         * petone : [{"id":"3","type_name":"大型犬","pid":"1","addtime":"12345","pettwo":[{"id":"12","type_name":"松狮","pid":"3","addtime":"12345"},{"id":"13","type_name":"牧羊犬","pid":"3","addtime":"12345"}]},{"id":"4","type_name":"中型犬","pid":"1","addtime":"12345","pettwo":[{"id":"10","type_name":"柴犬","pid":"4","addtime":"12345"},{"id":"11","type_name":"哈士奇","pid":"4","addtime":"12345"}]},{"id":"5","type_name":"小型犬","pid":"1","addtime":"12345","pettwo":[{"id":"6","type_name":"萨摩耶","pid":"5","addtime":"12345"},{"id":"9","type_name":"吉娃娃","pid":"5","addtime":"12345"}]}]
         */

        private String id;
        private String type_name;
        private String pid;
        private String addtime;
        private List<PetoneBean> petone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public List<PetoneBean> getPetone() {
            return petone;
        }

        public void setPetone(List<PetoneBean> petone) {
            this.petone = petone;
        }

        public static class PetoneBean {
            /**
             * id : 3
             * type_name : 大型犬
             * pid : 1
             * addtime : 12345
             * pettwo : [{"id":"12","type_name":"松狮","pid":"3","addtime":"12345"},{"id":"13","type_name":"牧羊犬","pid":"3","addtime":"12345"}]
             */

            private String id;
            private String type_name;
            private String pid;
            private String addtime;
            private List<PettwoBean> pettwo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public List<PettwoBean> getPettwo() {
                return pettwo;
            }

            public void setPettwo(List<PettwoBean> pettwo) {
                this.pettwo = pettwo;
            }

            public static class PettwoBean {
                /**
                 * id : 12
                 * type_name : 松狮
                 * pid : 3
                 * addtime : 12345
                 */

                private String id;
                private String type_name;
                private String pid;
                private String addtime;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }
            }
        }
    }
}
