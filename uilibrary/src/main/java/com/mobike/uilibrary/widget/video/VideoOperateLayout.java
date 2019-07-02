//package com.mobike.uilibrary.widget.video;
//
//import android.animation.Animator;
//import android.animation.AnimatorSet;
//import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.ActivityInfo;
//import android.graphics.Rect;
//import android.os.Handler;
//import android.util.AttributeSet;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import com.mobike.uilibrary.R;
//import com.mobike.uilibrary.utils.DeviceUtils;
//import org.greenrobot.eventbus.EventBus;
//
///**
// * 视频操作帮助类，包含视频标题，播放/暂停按钮，拖动SeekBar
// * Created by LinXin on 2017/12/7.
// */
//public class VideoOperateLayout extends RelativeLayout {
//
//    private static final String TAG = VideoOperateLayout.class.getSimpleName();
//
//    private static final long HIDE_OPERATE_TIME = 3000;
//    private BaseVideoView mVideoView;
//    private TextView mNotFullScreenTitleTv;
//    private TextView mFullScreenTitleTv;
//    private ViewGroup mFullScreenTitleLayout;
//    private ImageView mPlayImv;
//    private TextView mCurTimeTv;
//    private TextView mTotalTimeTv;
//    private SeekBar mSeekBar;
//    private ViewGroup mSeekBarLayout;
//    private ImageView mFullScreenImv;
//    private TextView mInitTotalTimeTv;
//
//    //为了能判断当前是否需要显示totaltime
//    private int mInitTotalTimeTvVisible = View.GONE;
//    private OnOperateLayoutShownListener mOnOperateLayoutShownListener;
//    private OnScreenChangeListener mOnScreenChangeListener;
//    private boolean mAllowCompleteNormalScreen = true;
//    private boolean mIsForceNomalIfClickBack = false;
//    private boolean mDisableFullScreenOpt = false;
//    private int mSystemUIStatus;
//    private boolean isFullScreen;//是否是全屏
//    private boolean isShowTitleNotFull = true;//是否在非全屏时显示标题
//    private boolean isFullScreenSwitching;//是否正在转屏
//    private boolean isAllowLandscapeWhenFullScreen = true;
//    private boolean isToggleShowOperationViews = false;//是不是点击屏幕显示的操作视图，如果是，则3秒后消失
//    private ViewGroup mParentView;
//    private int mIndex;
//    private ViewGroup.LayoutParams mNormalLayoutParams;
//    private ViewGroup.LayoutParams mFullLayoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//    private int curBright;//全屏前的亮度
//
//    private boolean isHideAllView = false;//是否不展示所有操作按钮 -- 小视频临时效果，后续需优化  - 2018.5.11. 6.5版本
//    private boolean isHideTitle = false;//是否不展示标题，理论上用isHideAllView即可，但测试后发现，isHideAllView为true时，部分情况全屏标题仍会显示，为不影响各个子播放器，故新开变量
//
//    private boolean isShowBottomProgress = false;// 小视频需求，比isHideAllView优先级高，显示底部进度条
//
//
//    /**
//     * 是否开启全屏动画
//     */
//    private boolean isOpenFullScreenAnim;
//    /**
//     * 当前activity是否有状态栏
//     */
//    private boolean hasStatus = true;
//
//    /**
//     * 全屏动画时长
//     */
//    private long fullScreenAnimDuration;
//
//    /**
//     * 是否开启全屏时的状态栏防抖动。（首先activity得是沉浸式全屏）
//     */
//    private boolean isKeepOffStatusBarShake = false;
//
//    // 7.3.1 是否滑动退出
//    private boolean isScrollExitFullScreen = false;
//
//    /**
//     *  是否在全屏状态下点击返回键回到正常模式；
//     *  使用此方法请确认：当前页面没有在全屏状态下，按返回键会回到上一个页面的需求；
//     * @param mIsForceNomalIfClickBack
//     */
//    @Deprecated
//    public void setIsForceNomalIfClickBack(boolean mIsForceNomalIfClickBack) {
//        this.mIsForceNomalIfClickBack = mIsForceNomalIfClickBack;
//    }
//
//    public void setKeepOffStatusBarShake(boolean keepOffStatusBarShake) {
//        isKeepOffStatusBarShake = keepOffStatusBarShake;
//    }
//
//    public void setShowBottomProgress(boolean showBottomProgress) {
//        isShowBottomProgress = showBottomProgress;
//    }
//
//    public void setAllowLandscapeWhenFullScreen(boolean allowLandscapeWhenFullScreen) {
//        isAllowLandscapeWhenFullScreen = allowLandscapeWhenFullScreen;
//    }
//
//    private OnClickListener listener = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if (v == mFullScreenImv) {
//                if (isFullScreen) {
//                    normalScreen();
//                } else {
//                    fullScreen();
//                }
//            } else if (v == mPlayImv) {
//                isToggleShowOperationViews = false;//重置状态
//                //LogUtils.e(TAG, "click play button,isToggleShowOperationViews=" + false);
//                if (mVideoView.isPlaying()) {
//                    //LogUtils.e(TAG, "click pause");
//                    mVideoView.pausePlay();
//                    mHandler.removeCallbacks(mRunnable);
//                    showPlayButton();
//                    mVideoView.onPauseEvent();
//                } else {
//                    //LogUtils.e(TAG, "click play");
//                    mVideoView.playVideo();
//                    mVideoView.onPlayEvent();
//                }
//            } else if (v.getId() == R.id.video_full_screen_back_imv) {
//                if (isFullScreen) {
//                    normalScreen();
//                }
//            }
//        }
//    };
//
//    public boolean isHideTitle() {
//        return isHideTitle;
//    }
//
//    public void setHideTitle(boolean hideTitle) {
//        isHideTitle = hideTitle;
//    }
//
//    public interface OnOperateLayoutShownListener {
//        void onShown(boolean shown);
//    }
//
//    public interface OnScreenChangeListener {
//        void onFullScreen();
//
//        void onNormalScreen();
//    }
//
//    public void setOnOperateLayoutShownListener(OnOperateLayoutShownListener layoutShownListener) {
//        mOnOperateLayoutShownListener = layoutShownListener;
//    }
//
//    public void setOnScreenChangeListener(OnScreenChangeListener onScreenChangeListener) {
//        mOnScreenChangeListener = onScreenChangeListener;
//    }
//
//    private Handler mHandler = new Handler();
//    private Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            isToggleShowOperationViews = false;
//            setVisibility(GONE);
//            if(mVideoView.isHideSeekBarAndTime()){
//                mVideoView.getVideoBottomProgressBar().setVisibility(GONE);
//            }else{
//                mVideoView.getVideoBottomProgressBar().setVisibility(VISIBLE);
//            }
//        }
//    };
//
//    public VideoOperateLayout(Context context) {
//        this(context, null);
//    }
//
//    public VideoOperateLayout(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public VideoOperateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context);
//    }
//
//    private void init(Context context) {
//        inflate(context, R.layout.base_video_operate_layout, this);
//        mNotFullScreenTitleTv = (TextView) findViewById(R.id.video_normal_screen_title_tv);
//        mFullScreenTitleTv = (TextView) findViewById(R.id.video_full_screen_title_tv);
//        mFullScreenTitleLayout = (ViewGroup) findViewById(R.id.video_full_screen_title_rl);
//        mPlayImv = (ImageView) findViewById(R.id.video_operate_play_imv);
//        mCurTimeTv = (TextView) findViewById(R.id.video_operate_cur_time_tv);
//        mTotalTimeTv = (TextView) findViewById(R.id.video_operate_total_time_tv);
//        mSeekBar = (SeekBar) findViewById(R.id.video_operate_seekbar);
//        mSeekBarLayout = (ViewGroup) findViewById(R.id.video_operate_seek_ll);
//        mFullScreenImv = (ImageView) findViewById(R.id.video_operate_fullscreen_imv);
//        mInitTotalTimeTv = (TextView) findViewById(R.id.video_operate_init_total_time_tv);
//        mDisableFullScreenOpt = DoorHelper.getStatus(MeetyouFramework.getContext(),"mDisableFullScreenOpt");
//        mSystemUIStatus = getSystemUiVisibility();
//
//        mFullScreenImv.setOnClickListener(listener);
//        mPlayImv.setOnClickListener(listener);
//        findViewById(R.id.video_full_screen_back_imv).setOnClickListener(listener);
//    }
//
//    public void setVideoView(BaseVideoView videoView) {
//        this.mVideoView = videoView;
//    }
//
//    public void setVideoTitle(String title) {
//        mFullScreenTitleTv.setText(title);
//        mNotFullScreenTitleTv.setText(title);
//    }
//
//    public void setVideoTime(String time) {
//        mInitTotalTimeTv.setText(time);
//        setInitTotalTimeTvVisible(mInitTotalTimeTvVisible);
//    }
//
//    /**
//     * 显示初始化状态：标题,播放按钮和时长
//     */
//    public void showInit() {
//        LogUtils.e(TAG, "showInit");
//        mHandler.removeCallbacks(mRunnable);
//        isToggleShowOperationViews = false;//状态重置
//        show();
//        for (int i = 0; i < getChildCount(); i++) {
//            View view = getChildAt(i);
//            if (view == mInitTotalTimeTv) {
//                setInitTotalTimeTvVisible(VISIBLE);
//            } else if (view == mPlayImv && !isHideAllView) {
//                mPlayImv.setVisibility(View.VISIBLE);
//                mPlayImv.setImageResource(R.drawable.video_btn_play);
//            } else {
//                view.setVisibility(View.INVISIBLE);
//            }
//        }
//        showTitleBar();
//    }
//    /**
//     * 显示标题
//     */
//    public void showTitleBar() {
//        if (isHideTitle){
//            mFullScreenTitleLayout.setVisibility(View.INVISIBLE);
//            mNotFullScreenTitleTv.setVisibility(View.INVISIBLE);
//        }else {
//            if (isFullScreen) {
//                mFullScreenTitleLayout.setVisibility(View.VISIBLE);
//                mNotFullScreenTitleTv.setVisibility(View.INVISIBLE);
//            } else {
//                mFullScreenTitleLayout.setVisibility(View.INVISIBLE);
//                if (isShowTitleNotFull) {//如果非全屏不显示，则隐藏
//                    mNotFullScreenTitleTv.setVisibility(View.VISIBLE);
//                } else {
//                    mNotFullScreenTitleTv.setVisibility(View.INVISIBLE);
//                }
//            }
//        }
//    }
//
//    /**
//     * 设置在非全屏时是否显示标题
//     *
//     * @param isShowTitleNotFull
//     */
//    public void setShowTitleNotFull(boolean isShowTitleNotFull) {
//        this.isShowTitleNotFull = isShowTitleNotFull;
//    }
//
//    /**
//     * 更新播放按钮状态
//     */
//    private void showPlayButton() {
//        if (mVideoView.isPlaying()) {//播放时显示暂停
//            mPlayImv.setImageResource(R.drawable.video_btn_pause);
//            mPlayImv.setVisibility(VISIBLE);
//        } else {//其他情况都显示播放
//            mPlayImv.setImageResource(R.drawable.video_btn_play);
//            mPlayImv.setVisibility(VISIBLE);
//        }
//    }
//
//    public void onSeek(long current) {
//        isToggleShowOperationViews = false;
//        mCurTimeTv.setText(VideoUtils.getDuration(current));
//    }
//
//    /**
//     * 更新进度
//     *
//     * @param current
//     * @param total
//     */
//    public void onProgress(long current, long total) {
//        mCurTimeTv.setText(VideoUtils.getDuration(current));
//        mTotalTimeTv.setText(VideoUtils.getDuration(total));
//    }
//
//    /**
//     * 是否允许全屏
//     *
//     * @param visible
//     */
//    public void setFullScreenVisible(int visible) {
//        mFullScreenImv.setVisibility(visible);
//    }
//
//    /**
//     * 返回非全屏
//     */
//    public void normalScreen() {
//        LogUtils.w(TAG, "normalScreen");
//        isFullScreen = false;
//        isFullScreenSwitching = true;
//
//        //取消全屏
//        if(mVideoView.getContext() instanceof Activity && !mDisableFullScreenOpt){
//            Activity activity =(Activity) mVideoView.getContext();
//            WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
//            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            activity.getWindow().setAttributes(attrs);
//        }else{
//            mVideoView.setSystemUiVisibility(mSystemUIStatus);
//        }
//
//        mFullScreenImv.setImageResource(R.drawable.apk_ic_video_fullscreen_selector);
//        LinganActivity activity = (LinganActivity) getContext();
//        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        activity.setInterceptView(null);
//        ViewGroup group=null;
//        if(!mDisableFullScreenOpt){
//            group = activity.getParentView();
//            if(group==null){
//                group = (ViewGroup) activity.findViewById(R.id.base_layout);
//            }
//        }else{
//            group = (ViewGroup) activity.findViewById(R.id.base_layout);
//        }
//
//        if (isScrollExitFullScreen) {
//            ViewGroup dragView = group.findViewById(R.id.full_video_drag_layout);
//            if (dragView != null) {
//                dragView.removeAllViews();
//                group.removeView(dragView);
//            } else {
//                return;
//            }
//        } else {
//            group.removeView(mVideoView);
//        }
//
//        mParentView.addView(mVideoView, mIndex, mNormalLayoutParams);
//        isFullScreenSwitching = false;
//        showTitleBar();
//        //重置亮度
//        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
//        lpa.screenBrightness = curBright / 100f;
//        activity.getWindow().setAttributes(lpa);
//        mVideoView.getCompleteLayout().hideTitleLayout();
//        if (mOnScreenChangeListener != null) {
//            mOnScreenChangeListener.onNormalScreen();
//        }
//        //仅供内部使用，外部模块不可接收此事件；
//        EventBus.getDefault().post(new VideoSreenEvent(false));
//    }
//
//
//    /**
//     * 设置全屏动画
//     * @param openFullScreenAnim 是否开启
//     * @param duration 时长
//     * @param hasStatus 是否有状态栏（影响动画计算）
//     */
//    public void setOpenFullScreenAnim(boolean openFullScreenAnim, long duration, boolean hasStatus) {
//        this.isOpenFullScreenAnim = openFullScreenAnim;
//        this.hasStatus = hasStatus;
//        this.fullScreenAnimDuration = duration;
//    }
//
//    /**
//     * 全屏
//     */
//    public void fullScreen() {
//        LogUtils.w(TAG, "fullScreen");
//        isFullScreen = true;
//        isFullScreenSwitching = true;
//        if (mParentView == null) {
//            mParentView = (ViewGroup) mVideoView.getParent();
//            mIndex = mParentView.indexOfChild(this);
//            if (mNormalLayoutParams == null) {
//                mNormalLayoutParams = getLayoutParams();
//            }
//        }
//
//        Rect rect = null;
//        //如果打开了全屏动画，保存videoview位置
//        if(isOpenFullScreenAnim) {
//            rect = new Rect();
//            mVideoView.getGlobalVisibleRect(rect);
//        } else {
//            if(!isKeepOffStatusBarShake) {
//                // 全屏
//                if (mVideoView.getContext() instanceof Activity && !mDisableFullScreenOpt) {
//                    Activity activity = (Activity) mVideoView.getContext();
//                    WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
//                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//                    activity.getWindow().setAttributes(attrs);
//                }else{
//                    mVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//                }
//
//                /*mVideoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//                SYSTEM_UI_FLAG_LOW_PROFILE
//                        - SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        - SYSTEM_UI_FLAG_FULLSCREEN
//                        - SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        - SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        - SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        - SYSTEM_UI_FLAG_IMMERSIVE
//                        - SYSTEM_UI_FLAG_IMMERSIVE_STICKY*/
//            }
//        }
//
//        LinganActivity activity = (LinganActivity) getContext();
//        if(isAllowLandscapeWhenFullScreen) {
//            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//        activity.setInterceptView(this);
//        mParentView.removeView(mVideoView);
//
//        //有些没有使用Lingan布局的，可以复写这个，比如优化后的SeeyouActivity
//        ViewGroup group =null;
//        if(!mDisableFullScreenOpt){
//            group = activity.getParentView();
//            if(group==null){
//                group = (ViewGroup) activity.findViewById(R.id.base_layout);
//            }
//        }else{
//            group = (ViewGroup) activity.findViewById(R.id.base_layout);
//        }
//
//        if(isScrollExitFullScreen){
//            // 7.3.1新增视频全屏下滑退出功能
//            RandomDragLayout dragLayout = new RandomDragLayout(mParentView.getContext());
//            dragLayout.setId(R.id.full_video_drag_layout);
//            dragLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            dragLayout.setOnoDragListener(new DragListener() {
//                @Override
//                public void onStartDrag() {
//
//                }
//
//                @Override
//                public void onStartEnter(boolean outOfBound) {
//
//                }
//
//                @Override
//                public void onEndEnter() {
//
//                }
//
//                @Override
//                public void onStartExit(boolean outOfBound) {
//
//                }
//
//                @Override
//                public void onEndExit() {
//                    normalScreen();
//                }
//
//                @Override
//                public void onRelease(boolean isResume) {
//
//                }
//
//                @Override
//                public void onEndResume() {
//
//                }
//            });
//            dragLayout.addView(mVideoView, mFullLayoutParams);
//            group.addView(dragLayout, new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//            dragLayout.setTransitionsView(rect);
//        } else {
//            group.addView(mVideoView, mFullLayoutParams);
//        }
//
//        if (isOpenFullScreenAnim) {
//            startToFullAnim(rect, activity, fullScreenAnimDuration, !hasStatus ? 0 : DeviceUtils.getStatusBarHeight(activity));
//        }
//
//
//        mFullScreenImv.setImageResource(R.drawable.apk_ic_video_reducescreen_selector);//横屏
//        isFullScreenSwitching = false;
//        showTitleBar();
//        //保存当前亮度
//        curBright = (int) (activity.getWindow().getAttributes().screenBrightness * 100);
//        if (mOnScreenChangeListener != null) {
//            mOnScreenChangeListener.onFullScreen();
//        }
//        //仅供内部使用，外部模块不可接收此事件；
//        EventBus.getDefault().post(new VideoSreenEvent(true));
//
//        mVideoView.onFullScreenEvent();
//    }
//
//
//    /**
//     * 执行全屏动画
//     * @param rect
//     * @param activity
//     */
//    private void startToFullAnim(Rect rect, LinganActivity activity, long duration, int statusBarHeight) {
//        //        ObjectAnimator animator = ObjectAnimator.ofFloat(mVideoView, "alpha", 1f, 0f);
////        animator.setDuration(5000);
////        animator.start();
//
//        if(rect != null) {
//
//            ValueAnimator widthValueAnimator = ValueAnimator.ofInt(mVideoView.getWidth(), DeviceUtils.getScreenWidth(MeetyouFramework.getContext()));
//            widthValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    mVideoView.getLayoutParams().width = (int) animation.getAnimatedValue();
//                    mVideoView.requestLayout();
//                }
//            });
//
//
//            ValueAnimator heightValueAnimator = ValueAnimator.ofInt(mVideoView.getHeight(), DeviceUtils.getScreenHeight(MeetyouFramework.getContext()));
//            heightValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    mVideoView.getLayoutParams().height = (int) animation.getAnimatedValue();
//                    mVideoView.requestLayout();
//                }
//
//            });
//
////            ValueAnimator timeValueAnimator = ValueAnimator.ofInt(0, (int) duration);
////            timeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////                @Override
////                public void onAnimationUpdate(ValueAnimator animation) {
////                    if((int)animation.getAnimatedValue() <= 100) {
////                        mVideoView.setSystemUiVisibility(INVISIBLE);
////                    }
////                }
////
////            });
//
//            ObjectAnimator translationX = ObjectAnimator.ofFloat(mVideoView, "translationX", rect.left, 0);
//            ObjectAnimator translationY = ObjectAnimator.ofFloat(mVideoView, "translationY", rect.top - statusBarHeight, 0);
//
//            AnimatorSet animSet = new AnimatorSet();
//            animSet.playTogether(widthValueAnimator, heightValueAnimator/*, timeValueAnimator*/, translationX, translationY);
//            animSet.setDuration(duration);
//            animSet.start();
//
//            animSet.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
////                    mVideoView.setSystemUiVisibility(INVISIBLE);
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//        }
//
//    }
//
//    private void startToNomal(Rect rect, LinganActivity activity) {
//        //        ObjectAnimator animator = ObjectAnimator.ofFloat(mVideoView, "alpha", 1f, 0f);
////        animator.setDuration(5000);
////        animator.start();
//
//        ValueAnimator widthValueAnimator = ValueAnimator.ofInt(DeviceUtils.getScreenWidth(MeetyouFramework.getContext()), mVideoView.getWidth());
//        widthValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mVideoView.getLayoutParams().width = (int) animation.getAnimatedValue();
//                mVideoView.requestLayout();
//            }
//        });
//
//
//        ValueAnimator heightValueAnimator = ValueAnimator.ofInt(DeviceUtils.getScreenHeight(MeetyouFramework.getContext()), mVideoView.getHeight());
//        heightValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                mVideoView.getLayoutParams().height = (int) animation.getAnimatedValue();
//                mVideoView.requestLayout();
//            }
//
//        });
//
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(mVideoView, "translationX", rect.left, 0);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(mVideoView, "translationY", rect.top - DeviceUtils.getStatusBarHeight(activity), 0);
//
//        AnimatorSet animSet = new AnimatorSet();
//        animSet.playTogether(widthValueAnimator, heightValueAnimator, translationX, translationY);
//        animSet.setDuration(5000);
//        animSet.start();
//    }
//
//
//    public boolean isFullScreenSwitching() {
//        return isFullScreenSwitching;
//    }
//
//    public boolean isFullScreen() {
//        return isFullScreen;
//    }
//
//    public SeekBar getSeekBar() {
//        return mSeekBar;
//    }
//
//    public void allowCompleteNormalScreen(boolean allow) {
//        mAllowCompleteNormalScreen = allow;
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK
//                && isFullScreen
//                && (mAllowCompleteNormalScreen | mIsForceNomalIfClickBack)) {
//            //返回
//            normalScreen();
//            return true;
//        }
//        return false;
//    }
//
//    public void show() {
//        LogUtils.e(TAG, "show");
//        setVisibility(VISIBLE);
//        mVideoView.getCompleteLayout().setVisibility(INVISIBLE);
//        mVideoView.getDragLayout().setVisibility(INVISIBLE);
//        mVideoView.getMobileNetworkLayout().setVisibility(INVISIBLE);
//        mVideoView.getVideoBottomProgressBar().setVisibility(INVISIBLE);
//    }
//
//    /**
//     * 设置不播放状态下的时间控件可见情况
//     *
//     * @param visible
//     */
//    public void setInitTotalTimeTvVisible(int visible) {
//        mInitTotalTimeTvVisible = visible;
//        if (visible == View.VISIBLE &&
//                !StringUtils.isEmpty(mInitTotalTimeTv.getText().toString())) {
//            mInitTotalTimeTv.setVisibility(View.VISIBLE);
//        } else {
//            mInitTotalTimeTv.setVisibility(View.GONE);
//        }
//    }
//
//    /**
//     * 显示/隐藏操作视图
//     */
//    public void toggleOperationViews() {
//        mHandler.removeCallbacks(mRunnable);
//        if (isHideAllView || isShown()) {//如果需要隐藏所有操作按钮，则永远不展示，临时效果，后续需优化
//            isToggleShowOperationViews = false;
//            setVisibility(GONE);
//            if(mVideoView.isHideSeekBarAndTime()){
//                mVideoView.getVideoBottomProgressBar().setVisibility(GONE);
//            }else{
//                if (isShortNeedProgress())
//                    mVideoView.getVideoBottomProgressBar().setVisibility(VISIBLE);
//
//            }
//        } else {
//            isToggleShowOperationViews = true;
//            showOperationView();
//            if (mVideoView.isPlaying()) {
//                mHandler.postDelayed(mRunnable, HIDE_OPERATE_TIME);
//            }
//        }
//        if (mOnOperateLayoutShownListener != null) {
//            mOnOperateLayoutShownListener.onShown(isToggleShowOperationViews);
//        }
//        LogUtils.e(TAG, "toggleOperationViews,isToggleShowOperationViews=" + isToggleShowOperationViews);
//    }
//
//    private boolean isShortNeedProgress() {
//        return !isHideAllView || isShowBottomProgress;
//    }
//
//    /**
//     * 显示操作按钮
//     */
//    public void showOperationView() {
//        if (isHideAllView){
//            return;
//        }
//        show();
//        showPlayButton();
//        showTitleBar();
//        setInitTotalTimeTvVisible(GONE);
//        if(mVideoView.isHideSeekBarAndTime()){
//            mSeekBarLayout.setVisibility(GONE);
//        }else{
//            mSeekBarLayout.setVisibility(VISIBLE);
//        }
//        mVideoView.getVideoBottomProgressBar().setVisibility(GONE);
//        mVideoView.getLoadingProgressBar().setVisibility(GONE);
//    }
//
//    /**
//     * 设置是否不展示所有操作按钮 -- 小视频临时效果，后续需优化  - 2018.5.11. 6.5版本
//     *
//     * @param hideAllView
//     */
//    public void setHideAllView(boolean hideAllView) {
//        isHideAllView = hideAllView;
//        mPlayImv.setVisibility(View.GONE);
//    }
//
//    public ImageView getPlayImv() {
//        return mPlayImv;
//    }
//
//
//    public void setScrollExitFullScreen(boolean scrollExitFullScreen) {
//        isScrollExitFullScreen = scrollExitFullScreen;
//    }
//}