package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.llg.chatweather.R;

/**
 * create by loogen on 2020-6-3
 */
public class DefaultDraw extends BaseDraw {
    public DefaultDraw(Context context) {
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
        return ContextCompat.getColor(mContext, R.color.snow_bg);
    }

}
