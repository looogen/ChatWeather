package com.llg.chatweather;

import android.app.Application;
import android.util.Log;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * create by loogen on 2020-5-28
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("RXJava", throwable.getCause()+throwable.getMessage());
            }
        });
    }
}
