package com.llg.chatweather.widget.animview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-6-21
 */
public abstract class BaseDraw implements DrawAnimInterface {

    protected List<BaseLine> mLines = new ArrayList<>();

    private boolean needInit = true;

    @Override
    public void drawGraph(Canvas canvas, int width, int height) {
        canvas.drawColor(setBackGround());
        if (needInit){
            randomLine(width,height);
            needInit = false;
        }
        drawAnim(canvas);
        changeLine();
    }

    abstract @ColorInt int setBackGround();
    abstract void drawAnim(Canvas canvas);
    abstract void randomLine(int w, int h);

    private void changeLine() {
        for (BaseLine line : mLines) {
            line.change();
        }
    }
}
