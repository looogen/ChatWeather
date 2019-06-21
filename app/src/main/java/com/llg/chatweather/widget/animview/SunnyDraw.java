package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.llg.chatweather.R;

/**
 * create by loogen on 2019-6-21
 *
 *
 * 晴天
 */
public class SunnyDraw extends BaseDraw {
    private Context mContext;


    private Paint mPaint;

    public SunnyDraw(Context context){
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mContext.getResources().getColor(R.color.sun));
    }

    @Override
    int setBackGround() {
        return mContext.getResources().getColor(R.color.sunny_bg);
    }

    @Override
    void drawAnim(Canvas canvas) {

    }

    @Override
    void randomLine(int w, int h) {

    }
}
