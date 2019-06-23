package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.llg.chatweather.R;
import com.llg.chatweather.widget.animview.lines.SunnyLine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-6-21
 *
 *
 * 晴天
 */
public class SunnyDraw extends BaseDraw {
    private Context mContext;
    private Paint mPaint;

    private List<SunnyLine> mSunnyLines;

    public SunnyDraw(Context context){
        mContext = context;
        mSunnyLines = new ArrayList<>();
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
    void generateLine(int w, int h) {
        int size = w/5;
        mSunnyLines.clear();
        for(int i = 0;i<4;i++){
            mSunnyLines.add(new SunnyLine(size*i));
        }
    }

    @Override
    void changeData() {

    }
}
