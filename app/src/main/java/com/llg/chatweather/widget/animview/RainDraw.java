package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.llg.chatweather.R;
import com.llg.chatweather.widget.animview.lines.BaseLine;
import com.llg.chatweather.widget.animview.lines.RainLine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-5-21
 */
public class RainDraw extends BaseDraw  {

    private Context mContext;

    protected Paint mPaint;

    protected List<BaseLine> mLines = new ArrayList<>();

    public RainDraw(Context context){
        mContext = context;
        initPaint();
    }

    @Override
    int setBackGround() {
        return mContext.getResources().getColor(R.color.rain_bg);
    }

    @Override
    void drawAnim(Canvas canvas) {
        for (BaseLine line : mLines) {
            int x = line.getmStartX();
            int y = line.getmStartY();
            canvas.drawLine(x,y,x,y+30,mPaint);
        }
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    void generateLine(int maxX, int maxY) {
        mLines.clear();
        for (int i = 0; i < 60; i++) {
            RainLine rainLine = new RainLine(maxX, maxY);
            mLines.add(rainLine);
        }
    }

    @Override
    void changeData() {
        for (BaseLine line : mLines) {
            line.change();
        }
    }
}
