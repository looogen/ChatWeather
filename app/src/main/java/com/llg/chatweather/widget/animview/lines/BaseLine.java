package com.llg.chatweather.widget.animview.lines;

import java.util.Random;

/**
 * create by loogen on 2019-5-21
 */
public abstract class BaseLine {

    //起始点
    protected int mStartX;
    protected int mStartY;

    //最大的范围
    protected int mMaxX;
    protected int mMaxY;

    protected int mDy = 10;

    protected Random mRandom;

    public BaseLine(int maxX, int maxY) {
        mMaxX = maxX;
        mMaxY = maxY;
        mRandom = new Random();
        mStartX = mRandom.nextInt(maxX);
        mStartY = mRandom.nextInt(maxY);
    }

    public abstract void change();

    public int getmStartX() {
        return mStartX;
    }

    public int getmStartY() {
        return mStartY;
    }
}
