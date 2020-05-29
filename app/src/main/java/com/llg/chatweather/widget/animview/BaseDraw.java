package com.llg.chatweather.widget.animview;

import android.graphics.Canvas;

import androidx.annotation.ColorInt;

/**
 * create by loogen on 2019-6-21
 */
public abstract class BaseDraw implements DrawAnimInterface {
    private boolean needInit = true;
    @Override
    public void drawGraph(Canvas canvas, int width, int height) {
        canvas.drawColor(setBackGround());
        if (needInit){
            generateLine(width,height);
            needInit = false;
        }
        drawAnim(canvas);
        changeData();
    }

    protected abstract @ColorInt
    int setBackGround();
    protected abstract void drawAnim(Canvas canvas);
    abstract void generateLine(int w, int h);
    abstract void changeData();
}
