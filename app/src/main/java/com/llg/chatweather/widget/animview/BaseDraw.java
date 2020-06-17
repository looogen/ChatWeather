package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;

/**
 * create by loogen on 2019-6-21
 */
public abstract class BaseDraw implements DrawAnimInterface {
    private boolean needInit = true;
    protected Context mContext;

    public BaseDraw(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void drawGraph(Canvas canvas, int width, int height) {
        if (needInit) {
            generateLine(width, height);
            needInit = false;
        }
        drawAnim(canvas);
        changeData();
    }

    protected abstract void drawAnim(Canvas canvas);
    abstract void generateLine(int w, int h);
    abstract void changeData();
}
