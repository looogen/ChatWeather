package com.llg.chatweather.db;

import android.content.Context;

import com.llg.chatweather.BuildConfig;
import com.llg.chatweather.entity.MyObjectBox;
import com.llg.chatweather.entity.NowWeatherEntity;
import com.llg.chatweather.entity.NowWeatherEntity_;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.query.QueryBuilder;

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

    private static long getCityIdByName(String name) {
        QueryBuilder<NowWeatherEntity> builder = getNowWeatherBox().query().contains(NowWeatherEntity_.city, name);
        NowWeatherEntity nowWeather = builder.build().findFirst();
        if (nowWeather != null) {
            return nowWeather.cityid;
        }
        return -1;
    }

    public static void addNowWeather(NowWeatherEntity now) {
        getNowWeatherBox().put(now);
    }

    public static NowWeatherEntity getNowWeather(String cityName) {
        long cityId = getCityIdByName(cityName);
        return getNowWeatherBox().get(cityId);
    }

    public static void removeNowWeather(String cityName) {
        long cityId = getCityIdByName(cityName);
        getNowWeatherBox().remove(cityId);
    }

    public static String[] getAllWeatherCityName() {
        return getNowWeatherBox().query().build()
                .property(NowWeatherEntity_.city)
                .findStrings();
    }
}
