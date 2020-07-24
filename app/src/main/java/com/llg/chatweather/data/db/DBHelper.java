package com.llg.chatweather.data.db;

import android.content.Context;
import android.util.Log;

import com.llg.chatweather.BuildConfig;
import com.llg.chatweather.data.db.entity.CityWeather;
import com.llg.chatweather.data.db.entity.CityWeather_;
import com.llg.chatweather.data.db.entity.MyObjectBox;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.android.ObjectBoxLiveData;

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
            boolean started = new AndroidObjectBrowser(boxStore).start(context);
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    private static Box<CityWeather> getCityWeatherBox() {
        return boxStore.boxFor(CityWeather.class);
    }

    public static void addCityWeather(CityWeather weather) {
        getCityWeatherBox().put(weather);
    }

    public static void removeCityWeather(CityWeather weather) {
        getCityWeatherBox().remove(weather);
    }

    public static List<CityWeather> getAllCityWeather() {
        return getCityWeatherBox().query().build().find();
    }

    //根据location 获取天气
    public static ObjectBoxLiveData<CityWeather> getCityWeather(String location) {
        return new ObjectBoxLiveData<>(getCityWeatherBox().query().equal(CityWeather_.name, location).build());
    }


    public static void removeAll() {
        getCityWeatherBox().removeAll();
    }
}
