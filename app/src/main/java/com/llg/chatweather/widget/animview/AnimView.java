package com.llg.chatweather.widget.animview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create by loogen on 2019-4-9
 */
public class AnimView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "AnimView";

    private SurfaceHolder mHolder;
    private boolean isNeedDrawing;

    private DrawAnimInterface mAnimInterface;

    //可绘制的区域
    private int mDrawWidth;
    private int mDrawHeight;

    ArrayBlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1);
    private final static int CORE_POOL_SIZE = 1;
    private final static int MAX_POOL_SIZE = 1;
    private final static int KEEP_ALIVE_TIME = 0;

    //核心线程数，总线程数，存活时间，存活时间单位，任务队列，线程工厂类，拒绝策略（当提交的请求不能处理时）
    private ExecutorService executorService = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            taskQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public void setAnimInterface(DrawAnimInterface animInterface) {
        this.mAnimInterface = animInterface;
        isNeedDrawing = true;
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
        Log.e(TAG, "init: ");
        mHolder = getHolder();
        mHolder.addCallback(this);

        // 顶层绘制SurfaceView设成透明
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

        // 获取可以绘制的区域大小
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        mDrawWidth = rect.width();
        mDrawHeight = rect.height();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated: >>>>>>>>>>>>>>>>>>>>>>>");
        if (mAnimInterface != null) {
            isNeedDrawing = true;
            Canvas canvas = mHolder.lockCanvas();
            canvas.drawColor(mAnimInterface.getBackGround());
            mHolder.unlockCanvasAndPost(canvas);
        }
        executorService.execute(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed: ");
        isNeedDrawing = false;
        executorService.shutdownNow();
    }

    private Canvas mCanvas;

    @Override
    public void run() {
        while (isNeedDrawing) {
            try {
                if (mAnimInterface != null) {
                    mCanvas = mHolder.lockCanvas();
                    mCanvas.drawColor(mAnimInterface.getBackGround());
                    mAnimInterface.drawGraph(mCanvas, mDrawWidth, mDrawHeight);
                    if (mAnimInterface.getAnimDuration() == 0) {
                        isNeedDrawing = false;
//                        executorService.shutdownNow();
                    } else {
                        TimeUnit.MILLISECONDS.sleep(mAnimInterface.getAnimDuration());
                    }
                }
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
//        isNeedDrawing = false;
//        executorService.shutdownNow();
    }

    public void callStart() {
//        isNeedDrawing = true;
//        executorService.shutdownNow();
//        executorService.execute(this);
    }
}
