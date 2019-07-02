package com.mobike.uilibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.mobike.uilibrary.R;

/**
 * 自定义View合集
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-24 20:50
 */
public class MyCanvasView extends View {

    private Context mContext;
    private Paint mPaint;
    private Paint mRedPaint;
    private int defaultPaintColor;

    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public MyCanvasView(Context context) {
        this(context, null);
    }

    // 如果View是在.xml里声明的，则调用第二个构造函数
    // 自定义属性是从AttributeSet参数传进来的
    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public MyCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCanvasView, 0, 0);

        defaultPaintColor = typedArray.getColor(R.styleable.MyCanvasView_paint_color, mContext.getResources().getColor(R.color.red_b));

        typedArray.recycle();
        init();
    }

    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MyCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(defaultPaintColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(100, 100, 200, 300, mPaint);

    }
}
