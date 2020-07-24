package com.llg.chatweather.data.db.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Unique;

@Entity
public class CityWeather {
    @Id
    public long dbId;//数据库ID

    @Index
    @Unique
    public String uid; //城市id
    public String name;//城市名字
    public String text;//天气状况
    public int code;//天气代码
    public String temperature;//温度
    public String last_update;//最后更新时间
    public String wind_direction;//风力方向
    public String wind_scale;//风力等级

//    public ToMany<Daily> dailies;

    public CityWeather(String uid, String name, String text, int code, String temperature, String last_update, String wind_direction, String wind_scale) {
        this.uid = uid;
        this.name = name;
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.last_update = last_update;
        this.wind_direction = wind_direction;
        this.wind_scale = wind_scale;
    }

    public CityWeather() {

    }
}