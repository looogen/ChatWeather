package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.llg.chatweather.R;
import com.llg.chatweather.widget.animview.lines.BaseLine;
import com.llg.chatweather.widget.animview.lines.RainLine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-5-21
 */
public class RainDraw extends BaseDraw  {

    protected Paint mPaint;

    protected List<BaseLine> mLines = new ArrayList<>();

    public RainDraw(Context context){
        super(context);
        initPaint();
    }


    @Override
    protected void drawAnim(Canvas canvas) {
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
        mPaint.setStrokeWidth(2);
    }

    @Override
    void generateLine(int maxX, int maxY) {
        mLines.clear();
        for (int i = 0; i < 50; i++) {
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

    @Override
    public long getAnimDuration() {
        return 16;
    }

    @Override
    public int getBackGround() {
        return ContextCompat.getColor(mContext, R.color.rain_bg);
    }

}
