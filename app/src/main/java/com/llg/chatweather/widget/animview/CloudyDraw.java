package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.llg.chatweather.R;

/**
 * create by loogen on 2019-6-25
 */
public class CloudyDraw extends BaseDraw {

    public CloudyDraw(Context context) {
        super(context);
    }

    @Override
    protected void drawAnim(Canvas canvas) {

    }

    @Override
    void generateLine(int w, int h) {

    }

    @Override
    void changeData() {

    }

    @Override
    public long getAnimDuration() {
        return 0;
    }

    @Override
    public int getBackGround() {
        return ContextCompat.getColor(mContext, R.color.cloudy_bg);
    }
}
