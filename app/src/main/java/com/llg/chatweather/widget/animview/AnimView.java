package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.llg.chatweather.widget.animview.lines.BaseLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by loogen on 2019-4-9
 */
public class AnimView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private boolean isNeedDrawing;

    private DrawAnimInterface mAnimInterface;


    protected List<BaseLine> mLines = new ArrayList<>();

    //可绘制的区域
    private int mDrawWidth;
    private int mDrawHeight;

    public void setAnimInterface(DrawAnimInterface animInterface) {
        this.mAnimInterface = animInterface;
    }

    public AnimView(Context context) {
        super(context);
        init(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(PixelFormat.TRANSLUCENT); // 顶层绘制SurfaceView设成透明
        setFocusable(true);
        setFocusableInTouchMode(true);

        //获取可以绘制的区域大小
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        mDrawWidth = rect.width();
        mDrawHeight = rect.height();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isNeedDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isNeedDrawing = false;
    }


    private Canvas mCanvas;

    @Override
    public void run() {
        while (isNeedDrawing) {
            try {
                if (mAnimInterface != null){
                    mCanvas = mHolder.lockCanvas();
                    mAnimInterface.drawGraph(mCanvas,mDrawWidth,mDrawHeight);
                }
                TimeUnit.MICROSECONDS.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }

    public void callStop() {
        isNeedDrawing = false;
    }
}
