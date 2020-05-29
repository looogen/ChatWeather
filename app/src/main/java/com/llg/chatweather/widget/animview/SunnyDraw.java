package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.llg.chatweather.R;
import com.llg.chatweather.widget.animview.lines.SunnyLine;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-6-21
 * 晴天
 */

public class SunnyDraw extends BaseDraw {
    private Context mContext;
    private Paint mSunPaint;
    private Paint mLinePaint = new Paint();

    private List<SunnyLine> mSunnyLines;

    public SunnyDraw(Context context){
        mContext = context;
        mSunnyLines = new ArrayList<>();
        initPaint();
    }

    private void initPaint() {
        mSunPaint = new Paint();
        mSunPaint.setAntiAlias(true);
        mSunPaint.setColor(mContext.getResources().getColor(R.color.sun));
        mSunPaint.setStrokeWidth(4);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(6);
        mLinePaint.setColor(Color.YELLOW);
    }

    @Override
    protected int setBackGround() {
        return mContext.getResources().getColor(R.color.sunny_bg);
    }

    @Override
    protected void drawAnim(Canvas canvas) {
        canvas.drawCircle(cx,cy,radius,mSunPaint);
        for (SunnyLine line:mSunnyLines){
            canvas.drawLine(line.getSx(),line.getSy(),line.getEx(),line.getEy(),mLinePaint);
        }
    }

    private int cx;
    private int cy;
    private int radius;
    //太阳线数目
    private static final int sunlines = 10;
    private double radian;

    @Override
    void generateLine(int w, int h) {
        cx = w/2;
        cy = h/4;
        radius = w/8;
        mSunnyLines.clear();
        radian = 2*Math.PI/sunlines;
        for(int i = 0;i<sunlines;i++){
            int sx = (int) (cx + (radius+3)*Math.cos(radian*i));
            int sy = (int) (cy + (radius+3)*Math.sin(radian*i));
            int ex = (int) (sx+ (radius+3)*Math.cos(radian*i));
            int ey = (int) (sy+ (radius+3)*Math.sin(radian*i));
            mSunnyLines.add(new SunnyLine(sx,sy,ex,ey));
        }
    }

    private int j = 0;
    @Override
    void changeData() {
        if (j++ >= 20){
            j = 0;
        }
        for(int i = 0;i<sunlines;i++){
            SunnyLine line = mSunnyLines.get(i);
            int sx = (int) (cx + (radius+3)*Math.cos((radian*i+Math.PI/18*j)%(2*Math.PI)));
            int sy = (int) (cy + (radius+3)*Math.sin((radian*i+Math.PI/18*j)%(2*Math.PI)));
            int ex = (int) (sx+ (radius+3)*Math.cos((radian*i+Math.PI/18*j)%(2*Math.PI)));
            int ey = (int) (sy+ (radius+3)*Math.sin((radian*i+Math.PI/18*j)%(2*Math.PI)));
            line.setLine(sx,sy,ex,ey);
        }
    }

    @Override
    public long getAnimDuration() {
        return 100;
    }
}
