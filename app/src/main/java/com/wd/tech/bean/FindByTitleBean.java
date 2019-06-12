package com.wd.tech.bean;

import java.util.List;

public class FindByTitleBean {

    /**
     * result : [{"id":63,"releaseTime":1553064683000,"source":"哈哈网","title":"脱离时代需求的搜索引擎，会不会被人工智能APP取代？"},{"id":62,"releaseTime":1553064558000,"source":"亚马逊网","title":"亚马逊：用AI瞄准全球10万亿美元的医疗健康大机遇"},{"id":61,"releaseTime":1553064018000,"source":"雨天","title":"区块链如何帮助人们更方便搞定法律服务？"},{"id":60,"releaseTime":1553063676000,"source":"侏罗纪","title":"威胁不止有51%攻击，区块链为何频遭黑客入侵？"},{"id":59,"releaseTime":1553063316000,"source":"科技行者","title":"中国移动亮相2019世界移动大会 展示5G发展计划并推出首款自主品牌5G终端"}]
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
         * id : 63
         * releaseTime : 1553064683000
         * source : 哈哈网
         * title : 脱离时代需求的搜索引擎，会不会被人工智能APP取代？
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
