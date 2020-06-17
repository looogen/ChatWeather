package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 天气动态背景
 * 根据不同的天气加载不同的背景
 * create by loogen on 2019-4-23
 */
public class WeatherAnimaView extends FrameLayout implements LifecycleObserver {
    private Context mContext;

    private AnimView mAnimView;

    private static final String TAG = WeatherAnimaView.class.getSimpleName();

    public WeatherAnimaView(@NonNull Context context) {
        this(context, null);
    }

    public WeatherAnimaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public WeatherAnimaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAnimView= new AnimView(mContext);
        addView(mAnimView);
    }

    private int preWeatherCode = -1;

    public void setWeatherCode(int code) {
        if (code == preWeatherCode) {
            return;
        }
        BaseDraw mBaseDraw;
        if (code >= 0 && code <= 3) {
            mBaseDraw = new SunnyDraw(mContext);
        } else if (code > 3 && code < 9) {
            mBaseDraw = new CloudyDraw(mContext);
        } else if (code == 9) {
            mBaseDraw = new OvercastDraw(mContext);
        } else if (code > 9 && code <= 18) {
            mBaseDraw = new RainDraw(mContext);
        } else if (code > 18 && code <= 24) {
            mBaseDraw = new SnowDraw(mContext);
        } else {
            mBaseDraw = new DefaultDraw(mContext);
        }
        mAnimView.callStop();
        mAnimView.setAnimInterface(mBaseDraw);
        mAnimView.callStart();
        preWeatherCode = code;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void startAnimation() {
        Log.e(TAG, "startAnimation: ");
        if (mAnimView != null) {
            mAnimView.callStart();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stopAnimation() {
        Log.e(TAG, "stopAnimation: ");
        if (mAnimView != null) {
            mAnimView.callStop();
        }
    }
}


