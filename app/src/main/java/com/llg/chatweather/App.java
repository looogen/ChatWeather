package com.llg.chatweather;

import android.app.Application;
import android.util.Log;

import com.llg.chatweather.data.db.DBHelper;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * create by loogen on 2020-5-28
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.init(this);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("RXJava", throwable.getCause() + throwable.getMessage());
            }
        });

        //test
//        DBHelper.addCityWeather(new CityWeather("AC000", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC020", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC020", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC030", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));
//        DBHelper.addCityWeather(new CityWeather("AC040", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "ss"));

    }
}
