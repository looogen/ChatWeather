package com.llg.chatweather.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 *
 * 天气动态背景
 * 根据不同的天气加载不同的背景
 * create by loogen on 2019-4-23
 */
public class SkyView extends FrameLayout {

    public SkyView(@NonNull Context context) {
        this(context,null);
    }

    public SkyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SkyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {



    }

    private void setWeather(int code){
//        switch (code){
//
//
//
//
//
//        }





    }
}
