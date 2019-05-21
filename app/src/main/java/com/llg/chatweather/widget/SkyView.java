package com.llg.chatweather.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * 天气动态背景
 * 根据不同的天气加载不同的背景
 * create by loogen on 2019-4-23
 */
public class SkyView extends FrameLayout {

    private Context mContext;

    private static final String TAG = SkyView.class.getSimpleName();

    public SkyView(@NonNull Context context) {
        this(context, null);
    }

    public SkyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SkyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setWeatherCode(String weatherCode) {
        if (weatherCode == null) {
            return;
        }
        Log.e(TAG, "setWeatherCode: " + weatherCode);
        int code = Integer.parseInt(weatherCode);
        if (code >= 0 && code <= 3) {
            //晴天
        } else if (code > 3 && code <= 9) {
            //多云
        } else if (code > 9 && code <= 18) {

        } else if (code > 18 && code <= 24) {
            //雪
        }
        //雨
        removeAllViews();
        RainView rainView = new RainView(mContext);
        addView(rainView);
    }

}


