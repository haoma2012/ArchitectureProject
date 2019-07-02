package com.mobike.uilibrary.widget.video;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 自定义SurfaceView
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/12 下午4:22
 */
public class UISurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = UISurfaceView.class.getSimpleName();
    public SurfaceHolder mHolder;
    //用于绘图的canvas
    public Canvas mCanvas;
    //子线程标志位
    public boolean mIsDrawing;

    public UISurfaceView(Context context) {
        this(context, null);
    }

    public UISurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UISurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        draw();
    }

    /**
     * 子类重写
     */
    public void draw() {
//        try {
//            mCanvas = mHolder.lockCanvas();
//            //draw something
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != mCanvas) {
//                mHolder.unlockCanvasAndPost(mCanvas);
//            }
//        }
    }
}
