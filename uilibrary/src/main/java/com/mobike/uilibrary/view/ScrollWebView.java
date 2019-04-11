package com.mobike.uilibrary.view;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

import static android.support.v4.widget.ViewDragHelper.INVALID_POINTER;

/**
 * 解决ScrollView 嵌套WebView 滑动冲突问题
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/3 下午11:32
 */
public class ScrollWebView extends WebView {
    private static final String TAG = ScrollWebView.class.getSimpleName();
    private boolean mIsBeingDragged;
    /**
     * ID of the active pointer. This is used to retain consistency during
     * drags/flings if multiple pointers are used.
     */
    private int mActivePointerId = INVALID_POINTER;
    /**
     * Position of the last motion event.
     */
    private int mLastMotionY;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;

    private float startX;
    private float startY;
    private float offsetx;
    private float offsety;

    public ScrollWebView(Context context) {
        super(context);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initScrollWebView();
    }

    private void initScrollWebView() {


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                startX = event.getX();
                startY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:
                Log.e("MotionEvent", "webview滑动");
                offsetx = Math.abs(event.getX() - startX);
                offsety = Math.abs(event.getY() - startY);
                if (offsetx > offsety) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    Log.e("MotionEvent", "屏蔽了父控件");
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    Log.e("MotionEvent", "事件传递给父控件");
                }
                break;
            default:
                break;
        }


        return super.onTouchEvent(event);
    }


    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//
//        if (action == MotionEvent.ACTION_MOVE && mIsBeingDragged) {
//            return true;
//        }
//
//        if (super.onInterceptTouchEvent(ev)) {
//            return true;
//        }
//
//        // 不能滑动了 不拦截
//        if(getScrollY() == 0 && !canScrollVertically(1)) {
//            return false;
//        }
//
//        switch (action & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_MOVE: {
//
//                final int activePointerId = mActivePointerId;
//                if (activePointerId == INVALID_POINTER) {
//                    // If we don't have a valid id, the touch down wasn't on content.
//                    break;
//                }
//                // 获取pointerIndex 手指点击
//                final int pointerIndex = ev.findPointerIndex(activePointerId);
//                if (pointerIndex == -1) {
//                    Log.e(TAG, "Invalid pointerId=" + activePointerId
//                            + " in onInterceptTouchEvent");
//                    break;
//                }
//
//                //得到当前触摸的y左边
//                final int y = (int) ev.getY(pointerIndex);
//                //计算移动的插值
//                final int yDiff = Math.abs(y - mLastMotionY);
//                //如果yDiff大于最小滑动距离，并且是垂直滑动则认为触发了滑动手势。
//                if (yDiff > mTouchSlop && (/*getNestedScrollAxes() &*/ SCROLL_AXIS_VERTICAL) == 0) {
//                    //标记拖动状态为true
//                    mIsBeingDragged = true;
//                    //赋值mLastMotionY
//                    mLastMotionY = y;
//                    //初始化mVelocityTracker并添加
////                    initVelocityTrackerIfNotExists();
////                    mVelocityTracker.addMovement(ev);
////                    mNestedYOffset = 0;
////                    if (mScrollStrictSpan == null) {
////                        mScrollStrictSpan = StrictMode.enterCriticalSpan("ScrollView-scroll");
////                    }
//                    final ViewParent parent = getParent();
//                    if (parent != null) {
//                        //通知父布局不再拦截触摸事件
//                        parent.requestDisallowInterceptTouchEvent(true);
//                    }
//                }
//                break;
//            }
//            case MotionEvent.ACTION_DOWN:
//                int y = (int) ev.getY();
//
//
//                break;
//
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//
//                //清除Drag状态
//                mIsBeingDragged = false;
//                mActivePointerId = INVALID_POINTER;
////                recycleVelocityTracker();
////                if (mScroller.springBack(mScrollX, mScrollY, 0, 0, 0, getScrollRange())) {
////                    postInvalidateOnAnimation();
////                }
//                //回调NestedScroll相关接口
//               // stopNestedScroll();
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                //当多个手指触摸中有一个手指抬起时，判断是不是当前active的点，如果是则寻找新的
//                //mActivePointerId
//                //onSecondaryPointerUp(ev);
//                break;
//
//        }
//
//        return mIsBeingDragged;
//    }
}
