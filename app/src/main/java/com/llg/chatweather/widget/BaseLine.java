package com.llg.chatweather.widget;

import java.util.Random;

/**
 * create by loogen on 2019-5-21
 */
public abstract class BaseLine {
    protected int mStartX;
    protected int mStartY;

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

    protected abstract void change();

    public int getmStartX() {
        return mStartX;
    }

    public int getmStartY() {
        return mStartY;
    }
}
