package com.llg.chatweather.widget;

import java.util.Random;

/**
 * create by loogen on 2019-4-9
 */
public class RainLine {
    private int mStartX;
    private int mStartY;

    private int mMaxX;
    private int mMaxY;

    private int mDy = 10;

    private Random mRandom;

    public RainLine(int maxX, int maxY) {
        mMaxX = maxX;
        mMaxY = maxY;
        mRandom = new Random();
        mStartX = mRandom.nextInt(maxX);
        mStartY = mRandom.nextInt(maxY);
    }

    public void change() {
        if (mStartY + mDy > mMaxY) {
            mStartY = 0;
            //一次下落后随机改变x坐标
            mStartX = mRandom.nextInt(mMaxX);
        } else {
            mStartY += mDy;
        }
    }

    public int getmStartX() {
        return mStartX;
    }

    public int getmStartY() {
        return mStartY;
    }
}
