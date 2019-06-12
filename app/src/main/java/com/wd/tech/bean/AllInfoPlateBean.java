package com.wd.tech.bean;

import java.util.List;

public class AllInfoPlateBean {

    /**
     * result : [{"id":1,"name":"电商消费","pic":"url"},{"id":2,"name":"娱乐淘金","pic":"url"},{"id":3,"name":"雪花一代","pic":"url"},{"id":4,"name":"人工智能","pic":"url"},{"id":5,"name":"车与出行","pic":"url"},{"id":6,"name":"智能终端","pic":"url"},{"id":7,"name":"医疗健康","pic":"url"},{"id":8,"name":"金融地产","pic":"url"},{"id":9,"name":"企业服务","pic":"url"},{"id":10,"name":"创业维艰","pic":"url"},{"id":11,"name":"社交通讯","pic":"url"},{"id":12,"name":"全球热点","pic":"url"},{"id":13,"name":"生活腔调","pic":"url"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * name : 电商消费
         * pic : url
         */

        private int id;
        private String name;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
