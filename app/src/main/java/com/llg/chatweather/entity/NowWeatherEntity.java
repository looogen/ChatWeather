package com.llg.chatweather.entity;

import com.google.gson.annotations.Expose;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Transient;
import io.objectbox.annotation.Unique;
import io.objectbox.relation.ToOne;

/**
 * create by loogen on 2019-3-21
 * <p>
 * 当前天气获取结果
 * 数据库和Gson的实体数据类
 * <p>
 */

@Entity
public class NowWeatherEntity {


    @Id
    @Expose(serialize = false, deserialize = false)
    public long id;


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
    public String date;
    public String countryEn;
    public String tem2;
    public String country;
    public String win_meter;
    public String air_level;
    public String week;
    public String tem1;
    public String visibility;
    public String city;
    @Unique
    @Index
    public String cityid;
    public String pressure;
    public String air;
    public String air_pm25;
    public String update_time;
    public String wea;
    public String air_tips;
    public String wea_img;

    //数据库忽略该字段
    @Transient
    public AlarmEntity alarm;

    //Gson忽略该字段
    @Expose(serialize = false, deserialize = false)
    public ToOne<AlarmEntity> alarmEntityToOne;

    public String cityEn;
    public String win_speed;
    public String humidity;
    public String tem;
    public String win;
}

