package com.llg.chatweather.widget.animview.lines;

/**
 * create by loogen on 2019-4-9
 */
public class RainLine extends BaseLine {


    public RainLine(int maxX, int maxY) {
        super(maxX, maxY);
        dy = (mRandom.nextInt(9)+1)*10;
    }

    private int dy;

    //这里的逻辑不能太耗时，容易引起卡顿
    @Override
    public void change() {
        if (mStartY + dy > mMaxY) {
            mStartY = 0;
            //一次下落后随机改变x坐标
            mStartX = mRandom.nextInt(mMaxX);
            //20-60
            dy = (mRandom.nextInt(4)+2)*10;
        } else {
            mStartY += dy;
        }
    }
}
