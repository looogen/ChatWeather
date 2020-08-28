package com.llg.chatweather.data.entity;

/**
 * create by loogen on 2019-3-21
 * <p>
 * <p>
 * 当前天气获取结果
 */
public class NowWeather {


    /**
     * date : 2020-08-26
     * countryEn : China
     * tem2 : 25
     * country : 中国
     * win_meter : 小于12km/h
     * air_level : 优
     * week : 星期三
     * tem1 : 31
     * visibility : 暂缺
     * city : 珠海
     * cityid : 101280701
     * pressure : 997
     * air : 22
     * air_pm25 : 22
     * update_time : 2020-08-26 09:40:56
     * wea : 多云
     * air_tips : 空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！
     * wea_img : yun
     * alarm : {"alarm_type":"","alarm_content":"","alarm_level":""}
     * cityEn : zhuhai
     * win_speed : 2级
     * humidity : 72%
     * tem : 31
     * win : 西南风
     */
    private String date;
    private String countryEn;
    private String tem2;
    private String country;
    private String win_meter;
    private String air_level;
    private String week;
    private String tem1;
    private String visibility;
    private String city;
    private String cityid;
    private String pressure;
    private String air;
    private String air_pm25;
    private String update_time;
    private String wea;
    private String air_tips;
    private String wea_img;
    private Alarm alarm;
    private String cityEn;
    private String win_speed;
    private String humidity;
    private String tem;
    private String win;

    public void setDate(String date) {
        this.date = date;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWin_meter(String win_meter) {
        this.win_meter = win_meter;
    }

    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public void setAir_pm25(String air_pm25) {
        this.air_pm25 = air_pm25;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDate() {
        return date;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public String getTem2() {
        return tem2;
    }

    public String getCountry() {
        return country;
    }

    public String getWin_meter() {
        return win_meter;
    }

    public String getAir_level() {
        return air_level;
    }

    public String getWeek() {
        return week;
    }

    public String getTem1() {
        return tem1;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getCity() {
        return city;
    }

    public String getCityid() {
        return cityid;
    }

    public String getPressure() {
        return pressure;
    }

    public String getAir() {
        return air;
    }

    public String getAir_pm25() {
        return air_pm25;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public String getWea() {
        return wea;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public String getWea_img() {
        return wea_img;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public String getCityEn() {
        return cityEn;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTem() {
        return tem;
    }

    public String getWin() {
        return win;
    }

    public class Alarm {
        /**
         * alarm_type :
         * alarm_content :
         * alarm_level :
         */
        private String alarm_type;
        private String alarm_content;
        private String alarm_level;

        public void setAlarm_type(String alarm_type) {
            this.alarm_type = alarm_type;
        }

        public void setAlarm_content(String alarm_content) {
            this.alarm_content = alarm_content;
        }

        public void setAlarm_level(String alarm_level) {
            this.alarm_level = alarm_level;
        }

        public String getAlarm_type() {
            return alarm_type;
        }

        public String getAlarm_content() {
            return alarm_content;
        }

        public String getAlarm_level() {
            return alarm_level;
        }
    }
}

