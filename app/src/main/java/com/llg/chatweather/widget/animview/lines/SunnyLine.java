package com.llg.chatweather.widget.animview.lines;

import android.graphics.Point;

/**
 * Created by leelugen
 */
public class SunnyLine {
    private Point mStartPoint;
    private Point mEndPoint;

     public SunnyLine(int sX,int sY,int eX,int eY){
         mStartPoint = new Point(sX,sX);
         mEndPoint = new Point(eX,eY);
     }

    public SunnyLine(int index){
         mStartPoint = new Point(0,index);
         mEndPoint = new Point(index,0);
    }
}
