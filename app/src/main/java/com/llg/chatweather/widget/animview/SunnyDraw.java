package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

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

    public SunnyDraw(Context context) {
        super(context);
        mContext = context;
        mSunnyLines = new ArrayList<>();
        initPaint();
    }

    private void initPaint() {
        mSunPaint = new Paint();
        mSunPaint.setAntiAlias(true);
        mSunPaint.setColor(mContext.getResources().getColor(R.color.sun));
        mSunPaint.setStrokeWidth(2);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.YELLOW);
    }

    @Override
    protected void drawAnim(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, mSunPaint);
        for (SunnyLine line : mSunnyLines) {
            canvas.drawLine(line.getSx(), line.getSy(), line.getEx(), line.getEy(), mLinePaint);
        }
    }

    //太阳圆心坐标
    private int cx;
    private int cy;
    //太阳圆的半径
    private int radius;
    //太阳线数目
    private static final int sunlines = 10;
    //线与圆的空白数
    private static final int spacing = 3;

    private int size;

    private float radianRate;

    @Override
    void generateLine(int w, int h) {
        cx = 200;
        cy = 200;
        radius = 50;
        mSunnyLines.clear();
        radianRate = 2f / sunlines;
        size = radius + spacing;
        for (int i = 0; i < sunlines; i++) {
            double radian = radianRate * i * Math.PI;
            int sx = (int) (cx + size * Math.cos(radian));
            int sy = (int) (cy + size * Math.sin(radian));
            int ex = (int) (sx + size * Math.cos(radian));
            int ey = (int) (sy + size * Math.sin(radian));
            mSunnyLines.add(new SunnyLine(sx, sy, ex, ey));
        }
    }

    boolean change = false;
    @Override
    void changeData() {
        //造成一种旋转的错觉变化
        float rate = change ? radianRate/2 : 0;
        for (int i = 0; i < sunlines; i++) {
            SunnyLine line = mSunnyLines.get(i);
            double radian = (radianRate *i+rate) * Math.PI;
            int sx = (int) (cx + size * Math.cos(radian));
            int sy = (int) (cy + size * Math.sin(radian));
            int ex = (int) (sx + size * Math.cos(radian));
            int ey = (int) (sy + size * Math.sin(radian));
            line.setLine(sx, sy, ex, ey);
        }
        change = !change;
    }

    @Override
    public long getAnimDuration() {
        return 200;
    }


    @Override
    public int getBackGround() {
        return ContextCompat.getColor(mContext, R.color.sunny_bg);
    }
}
