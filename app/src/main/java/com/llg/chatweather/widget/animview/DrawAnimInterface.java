package com.llg.chatweather.widget.animview;

import android.graphics.Canvas;

/**
 * create by loogen on 2019-6-21
 */
public interface DrawAnimInterface {
    void drawGraph(Canvas canvas,int width,int height);
    long setAnimDuration();
}
