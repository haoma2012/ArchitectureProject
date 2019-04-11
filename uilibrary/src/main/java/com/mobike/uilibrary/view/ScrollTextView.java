package com.mobike.uilibrary.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Scroller;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/4 上午11:34
 */
public class ScrollTextView extends AppCompatTextView {

    private Context mContext;
    private Scroller mScroller;
    private static final int DEFAULT_DURATION = 300;

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        // 初始化Scroller
        mScroller = new Scroller(mContext);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            offsetLeftAndRight(mScroller.getCurrX() - getLeft());
            offsetTopAndBottom(mScroller.getCurrY() - getTop());
            invalidate();
        }
    }

    public void startScrollerScroll() {
        mScroller.startScroll(getLeft(), getTop(), 0, -400, DEFAULT_DURATION);
        invalidate();
    }

    public void startScrollFling() {
        mScroller.fling(getLeft(), getTop(), 0, -5000, getLeft(), getLeft(), 200, 1200);
    }
}
