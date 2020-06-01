package com.llg.chatweather.widget.animview.lines;


/**
 * Created by leelugen
 */
public class SunnyLine {
    private int sx;
    private int sy;
    private int ex;
    private int ey;

    public SunnyLine(int sx, int sy, int ex, int ey) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
    }

    public int getSx() {
        return sx;
    }

    public int getSy() {
        return sy;
    }

    public int getEx() {
        return ex;
    }

    public int getEy() {
        return ey;
    }


    public void setLine(int sx,int sy,int ex,int ey){
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
    }
}
