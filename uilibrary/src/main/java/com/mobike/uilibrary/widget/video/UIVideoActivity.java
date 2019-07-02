package com.mobike.uilibrary.widget.video;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.utils.AnimationUtils;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 视频类
 * * 香港卫视：http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8
 * * CCTV1高清：http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
 * * CCTV3高清：http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
 * * CCTV5高清：http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8
 * * CCTV5+高清：http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8
 * * CCTV6高清：http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8
 * * 苹果提供的测试源（点播）：http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/12 下午2:48
 */
public class UIVideoActivity extends BaseActivity {
    private static final String TAG = UIVideoActivity.class.getSimpleName();
    private static final String TEST_URL = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
    private VideoView mVideoView;

    // Operate UI
    private static final long HIDE_OPERATE_TIME = 3000;
    //private BaseVideoView mVideoView;
    private TextView mNotFullScreenTitleTv;
    private TextView mFullScreenTitleTv;
    private ViewGroup mFullScreenTitleLayout;
    private ImageView mPlayImv;
    private TextView mCurTimeTv;
    private TextView mTotalTimeTv;
    private SeekBar mSeekBar;
    private ViewGroup mSeekBarLayout;
    private ImageView mFullScreenImv;
    private TextView mInitTotalTimeTv;

    private boolean isFullScreen;//是否是全屏
    private boolean isShowTitleNotFull = true;//是否在非全屏时显示标题
    private boolean isFullScreenSwitching;//是否正在转屏

    private ProgressBar progressBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ui_video;
    }

    @Override
    protected void beforeInitView(@Nullable Bundle savedInstanceState) {
        super.beforeInitView(savedInstanceState);
    }

    @Override
    protected void initView() {

        mVideoView = findViewById(R.id.video_view);
        //start play
        mVideoView.setVideoPath(TEST_URL);
        mVideoView.requestFocus();
        //mVideoView.start();

        //initBind();
        // initOperateLayout
        initOperateLayout();

        initProgressBar();
    }

    private void initOperateLayout() {
        mNotFullScreenTitleTv = (TextView) findViewById(R.id.video_normal_screen_title_tv);
        mFullScreenTitleTv = (TextView) findViewById(R.id.video_full_screen_title_tv);
        mFullScreenTitleLayout = (ViewGroup) findViewById(R.id.video_full_screen_title_rl);
        mPlayImv = (ImageView) findViewById(R.id.video_operate_play_imv);
        mCurTimeTv = (TextView) findViewById(R.id.video_operate_cur_time_tv);
        mTotalTimeTv = (TextView) findViewById(R.id.video_operate_total_time_tv);
        mSeekBar = (SeekBar) findViewById(R.id.video_operate_seekbar);
        mSeekBarLayout = (ViewGroup) findViewById(R.id.video_operate_seek_ll);
        mFullScreenImv = (ImageView) findViewById(R.id.video_operate_fullscreen_imv);
        mInitTotalTimeTv = (TextView) findViewById(R.id.video_operate_init_total_time_tv);

        mFullScreenImv.setOnClickListener(listener);
        mPlayImv.setOnClickListener(listener);
        findViewById(R.id.video_full_screen_back_imv).setOnClickListener(listener);
    }

    private void initBind() {
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(mVideoView);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mFullScreenImv) {
//                if (isFullScreen) {
//                    normalScreen();
//                } else {
//                    fullScreen();
//                }
            } else if (v == mPlayImv) {
                //isToggleShowOperationViews = false;//重置状态
                Log.e(TAG, "click play button,isToggleShowOperationViews=" + false);
                if (mVideoView.isPlaying()) {
                    Log.e(TAG, "click pause");
                    mVideoView.pause();
                    //mHandler.removeCallbacks(mRunnable);
                    showPlayButton();
                    //mVideoView.onPauseEvent();
                } else {
                    Log.e(TAG, "click play");
                    mVideoView.start();
                    //mVideoView.onPlayEvent();
                }
            } else if (v.getId() == R.id.video_full_screen_back_imv) {
//                if (isFullScreen) {
//                    normalScreen();
//                }
            }
        }
    };

    /**
     * 更新播放按钮状态
     */
    private void showPlayButton() {
        if (mVideoView.isPlaying()) {//播放时显示暂停
            mPlayImv.setImageResource(R.drawable.video_btn_pause);
            mPlayImv.setVisibility(View.VISIBLE);
        } else {//其他情况都显示播放
            mPlayImv.setImageResource(R.drawable.video_btn_play);
            mPlayImv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mVideoView.isPlaying()) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlaybackVideo();
    }

    private void stopPlaybackVideo() {
        try {
            mVideoView.stopPlayback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initProgressBar() {
        // 使用Toolbar代替actionbar

//        Toolbar mToolBar = findViewById(R.id.title_tool_bar);
//        mToolBar.setTitle("Video详情页");
//        mToolBar.setNavigationIcon(R.drawable.nav_btn_back);//设置导航栏图标
//        mToolBar.setLogo(R.drawable.tab_item_cart);//设置app logo
//        mToolBar.setTitle("Video详情页");//设置主标题
//        mToolBar.setSubtitle("Subtitle");//设置子标题
//
//        mToolBar.inflateMenu(R.menu.bottom_menu);//设置右上角的填充菜单
//        mToolBar.setOnMenuItemClickListener(item -> {
//            int menuItemId = item.getItemId();
//            Toast.makeText(getApplicationContext(), "menu点击" + menuItemId, Toast.LENGTH_SHORT).show();
//
//            return true;
//        });


        FrameLayout mLoadingLayout = findViewById(R.id.frame_loading);
        progressBar = findViewById(R.id.pb_count_down_top);

        TextView tvCountDownTop = findViewById(R.id.tv_count_down_top);
        mLoadingLayout.setVisibility(View.VISIBLE);

        AnimationUtils.doProgressAnimation(progressBar, new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 通过animated多少数值，来获取当前剩余的进度或者已经完成的进度
                int value = (int) animation.getAnimatedValue();
                tvCountDownTop.setText(String.format(Locale.CHINA, ("剩余\n%d"), value));
//                if (value == 0) {
//                    mLoadingLayout.setVisibility(View.GONE);
//                }
            }
        });
    }

    private SearchView mSearchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        mSearchView = (SearchView) searchItem.getActionView();

        return super.onCreateOptionsMenu(menu);

    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void testReentrantLock() {
        ReentrantLock lock = new ReentrantLock();

        try {
            // 锁定
            lock.lock();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }

    }

}