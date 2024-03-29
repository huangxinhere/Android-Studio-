package com.example.retrofittext;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Car {
    /**
     * translation : ["车"]
     * basic : {"us-phonetic":"kɑːr","phonetic":"kɑː(r)","uk-phonetic":"kɑː(r)","explains":["n. 汽车；车厢","n. (Car)人名；(土)贾尔；(法、西)卡尔；(塞)察尔"]}
     * query : car
     * errorCode : 0
     * web : [{"value":["汽车","小汽车","嵌合抗原受体(Chimeric Antigen Receptor)","车"],"key":"Car"},{"value":["安全车","安全汽车","安全矿车","救生吊车"],"key":"safety car"},{"value":["碰碰车","碰碰汽车","碰撞用"],"key":"bumper car"}]
     */

    private List<String> translation;
    private BasicBean basic;
    private String query;
    private Integer errorCode;
    private List<WebBean> web;

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        /**
         * us-phonetic : kɑːr
         * phonetic : kɑː(r)
         * uk-phonetic : kɑː(r)
         * explains : ["n. 汽车；车厢","n. (Car)人名；(土)贾尔；(法、西)卡尔；(塞)察尔"]
         */

        @SerializedName("us-phonetic")
        private String usphonetic;
        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        private List<String> explains;

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {
        /**
         * value : ["汽车","小汽车","嵌合抗原受体(Chimeric Antigen Receptor)","车"]
         * key : Car
         */

        private List<String> value;
        private String key;

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}