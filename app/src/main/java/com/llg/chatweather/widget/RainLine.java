package com.llg.chatweather.widget;

/**
 * create by loogen on 2019-4-9
 */
public class RainLine extends BaseLine {

    public RainLine(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    protected void change() {
        if (mStartY + mDy > mMaxY) {
            mStartY = 0;
            //一次下落后随机改变x坐标
            mStartX = mRandom.nextInt(mMaxX);
        } else {
            mStartY += mDy;
        }
    }
}
