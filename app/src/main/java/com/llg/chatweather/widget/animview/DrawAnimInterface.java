package com.llg.chatweather.widget.animview;

import android.graphics.Canvas;

import androidx.annotation.ColorInt;

/**
 * create by loogen on 2019-6-21
 */
public interface DrawAnimInterface {
    void drawGraph(Canvas canvas, int width, int height);

    long getAnimDuration();

    @ColorInt
    int getBackGround();
}
