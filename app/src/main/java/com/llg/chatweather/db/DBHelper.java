package com.llg.chatweather.db;

import android.content.Context;

import com.llg.chatweather.BuildConfig;
import com.llg.chatweather.entity.MyObjectBox;
import com.llg.chatweather.entity.NowWeatherEntity;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * create by loogen on 2020-6-9
 */
public class DBHelper {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context)
                .name("cityWeather")
                .build();

        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(context);
        }
    }

    private static Box<NowWeatherEntity> getNowWeatherBox() {
        return boxStore.boxFor(NowWeatherEntity.class);
    }

    public static void addNow(NowWeatherEntity now) {
        now.alarmEntityToOne.setTarget(now.alarm);
        //TODO 存在bug 添加数据时 要判断有没有旧的值     ------更新操作
        getNowWeatherBox().put(now);
    }

    public static void removeNowWeather(String cityId) {
//        getNowWeatherBox().remove(weather);
    }

//    public static List<NowWeather> getAllNowWeather() {
//        return getNowWeatherBox().query().build().find();
//    }

//    public static ObjectBoxLiveData<NowWeather> getCityWeather(String location) {
//        return new ObjectBoxLiveData<>(getNowWeatherBox().query().equal(NowWeather.name, location).build());
//    }

    public static void removeAll() {
        getNowWeatherBox().removeAll();
    }
}
