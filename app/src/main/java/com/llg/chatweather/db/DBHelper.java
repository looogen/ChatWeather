package com.llg.chatweather.db;

import android.content.Context;

import com.llg.chatweather.data.entity.NowWeather;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * create by loogen on 2020-6-9
 */
public class DBHelper {

    private static BoxStore boxStore;

    public static void init(Context context) {
//        boxStore = MyObjectBox.builder()
//                .androidContext(context)
//                .name("cityWeather")
//                .build();
//
//        if (BuildConfig.DEBUG) {
//            boolean started = new AndroidObjectBrowser(boxStore).start(context);
//            Log.i("ObjectBrowser", "Started: " + started);
//        }
    }

    private static Box<NowWeather> getNowWeatherBox() {
        return boxStore.boxFor(NowWeather.class);
    }

    public static void addNowWeather(NowWeather weather) {
        getNowWeatherBox().put(weather);
    }

    public static void removeNowWeather(NowWeather weather) {
        getNowWeatherBox().remove(weather);
    }

    public static List<NowWeather> getAllNowWeather() {
        return getNowWeatherBox().query().build().find();
    }

//    public static ObjectBoxLiveData<NowWeather> getCityWeather(String location) {
//        return new ObjectBoxLiveData<>(getNowWeatherBox().query().equal(NowWeather.name, location).build());
//    }


    public static void removeAll() {
        getNowWeatherBox().removeAll();
    }
}
