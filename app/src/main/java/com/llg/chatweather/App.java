package com.llg.chatweather;

import android.app.Application;

import com.llg.chatweather.db.DBHelper;

/**
 * create by loogen on 2020-5-28
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.init(this);
        //test
//        DBHelper.addCityWeather(new CityWeather("AC000", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC020", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC020", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC030", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC040", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));

    }
}
