package com.llg.chatweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.llg.chatweather.db.DBHelper;

/**
 * create by loogen on 2020-5-28
 */
public class App extends Application implements ViewModelStoreOwner {
    private ViewModelStore mAppViewModelStore;

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.init(this);
        mAppViewModelStore = new ViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
