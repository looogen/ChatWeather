package com.llg.chatweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.llg.chatweather.R;

/**
 * create by loogen on 2019-5-21
 */
public class RainView extends BaseWeatherView {

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setBackGround() {
        return getResources().getColor(R.color.colorAccent);
    }

    @Override
    protected void drawLine(Canvas canvas) {
        for (BaseLine line : mLines) {
            int x = line.getmStartX();
            int y = line.getmStartY();
            canvas.drawLine(x,y,x,y+30,mLinePaint);
        }
    }

    @Override
    protected void randomLine(int maxX, int maxY) {
        for (int i = 0; i < 60; i++) {
            RainLine rainLine = new RainLine(maxX, maxY);
            mLines.add(rainLine);
        }
    }

    @Override
    protected void initLinePaint(Paint paint) {
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStrokeWidth(4);
    }
}
