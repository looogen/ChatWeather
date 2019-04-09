package com.llg.chatweather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
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
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean isNeedDrawing;

    private List<RainLine> mRainLines = new ArrayList<>();

    private Paint mPaint = new Paint();


    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setFormat(PixelFormat.TRANSLUCENT); // 顶层绘制SurfaceView设成透明


        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        randomLine(rect.width(), rect.height());
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
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
                mCanvas = mHolder.lockCanvas();
                mCanvas.drawColor(Color.WHITE);
                drawRain(mCanvas);
                changeRain();
                mHolder.unlockCanvasAndPost(mCanvas);
                TimeUnit.MICROSECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void changeRain() {
        for (RainLine line : mRainLines) {
            line.change();
        }
    }

    private void drawRain(Canvas canvas) {
        for (RainLine line : mRainLines) {
            int x = line.getmStartX();
            int y = line.getmStartY();
            canvas.drawLine(x,y,x,y+30,mPaint);
        }
    }


    private void randomLine(int maxX, int maxY) {
        for (int i = 0; i < 60; i++) {
            RainLine rainLine = new RainLine(maxX, maxY);
            mRainLines.add(rainLine);
        }
    }
}
