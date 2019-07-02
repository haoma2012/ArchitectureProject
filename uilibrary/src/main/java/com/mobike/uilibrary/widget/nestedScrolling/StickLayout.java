package com.mobike.uilibrary.widget.nestedScrolling;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/15 下午3:25
 */
public class StickLayout extends LinearLayout implements NestedScrollingParent {
    private int maxScrollY;
    private Scroller mScroller;
    private Context mContext;

    public StickLayout(Context context) {
        this(context, null);
    }

    public StickLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private void initView(Context context) {


    }


    @Override
    public boolean startNestedScroll(int axes) {
//        return super.startNestedScroll(axes);
        return true;
    }

    @Override
    public void onNestedPreScroll(@Nullable View target, int dx, int dy, @Nullable int[] consumed) {
        // dy > 0表示子View向上滑动;

        // 子View向上滑动且父View的偏移量<ImageView高度
        boolean hiddenTop = dy > 0 && getScrollY() < maxScrollY;

        // 子View向下滑动(说明此时父View已经往上偏移了)且父View还在屏幕外面, 另外内部View不能在垂直方向往下移动了
        /**
         * ViewCompat.canScrollVertically(view, int)
         * 负数: 顶部是否可以滚动(官方描述: 能否往上滚动, 不太准确吧~)
         * 正数: 底部是否可以滚动
         */
        boolean showTop = dy < 0 && getScrollY() > 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (velocityY > 0 && getScrollY() < maxScrollY) // 向上滑动, 且当前View还没滑到顶
        {
            fling((int) velocityY, maxScrollY);
            return true;
        } else if (velocityY < 0 && getScrollY() > 0) // 向下滑动, 且当前View部分在屏幕外
        {
            fling((int) velocityY, 0);
            return true;
        }
        return false;
    }

    public void fling(int velocityY, int maxY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, maxY);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) // 不允许向下滑动
        {
            y = 0;
        }
        if (y > maxScrollY) // 防止向上滑动距离大于最大滑动距离
        {
            y = maxScrollY;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }
}
