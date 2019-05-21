package com.llg.chatweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by loogen on 2019-4-9
 */
public abstract class BaseWeatherView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private boolean isNeedDrawing;

    protected List<BaseLine> mLines = new ArrayList<>();
    protected Paint mLinePaint = new Paint();


    public BaseWeatherView(Context context) {
        super(context);
        init(context);
    }

    public BaseWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseWeatherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(PixelFormat.TRANSLUCENT); // 顶层绘制SurfaceView设成透明

        setFocusable(true);
        setFocusableInTouchMode(true);

        //获取可以绘制的大小
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        randomLine(rect.width(), rect.height());

        initLinePaint(mLinePaint);
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

    @Override
    public void run() {
        while (isNeedDrawing) {
            try {
                Canvas mCanvas = mHolder.lockCanvas();
                mCanvas.drawColor(setBackGround());
                drawLine(mCanvas);
                changeLine();
                mHolder.unlockCanvasAndPost(mCanvas);
                TimeUnit.MICROSECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void changeLine() {
        for (BaseLine line : mLines) {
            line.change();
        }
    }

    protected abstract @ColorInt int setBackGround();

    protected abstract void drawLine(Canvas canvas);

    //随机生成点或者线
    protected abstract void randomLine(int maxX, int maxY);

    //初始点或者线的画笔
    protected abstract void initLinePaint(Paint paint);
}
