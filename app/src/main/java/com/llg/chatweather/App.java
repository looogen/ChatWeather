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
    }
}
