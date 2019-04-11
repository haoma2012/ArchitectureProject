package com.mobike.uilibrary.widget;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.mobike.framework.event.MessageEvent;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.adapter.UIWidgetPagerAdapter;
import com.mobike.uilibrary.base.BaseActivity;
import com.mobike.uilibrary.manager.TabLayoutHelper;
import com.mobike.uilibrary.model.TabHomeModel;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 弹框详解Activity
 * Created by yangdehao@xiaoyouzi.com  on 2019/3/19 下午3:36
 */
public class WidgetDialogActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private ViewPager mViewPager;
    private UIWidgetPagerAdapter widgetPagerAdapter;


    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private Toolbar mToolbar;
    private int firstOffSet;

    //private LoaderImageView mHeaderBg;
    private ImageView mHeaderTitle;
    private ImageView mHeaderSearchTop;
    private TextView mTvSearchCoupon;//搜素按钮
    private RelativeLayout mVideoLayout;
    private RelativeLayout mSearchLayout;// 搜索区域
    private RelativeLayout mSearchBox;//搜素框
    private ViewFlipper mSearchFlipper;

    // 橱窗视频
    //private EcoVideoHelper videoHelper;
    private int mVideoLayoutHeight; // 橱窗视频位置高度
    // 置顶导航条
    private ImageView mIvTabClassify;
    private TabLayout mHeaderTabLayout;
    private TabLayoutHelper mTabHelper;

    public static void enterActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WidgetDialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 5.0新增转场动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        } else {
            context.startActivity(intent);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.widget_dialog_activty;
    }

    @Override
    protected void initView() {
        mTabHelper = new TabLayoutHelper(getApplicationContext());
        mViewPager = findViewById(R.id.widget_recycle_view);

        // AppBarLayout
        mAppBarLayout = findViewById(R.id.appbar);
        mAppBarLayout.addOnOffsetChangedListener(this);
        // 折叠区域
        mToolbar = findViewById(R.id.tool_bar);
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        mHeaderTitle = findViewById(R.id.sheep_home_header_title);
        mHeaderSearchTop = findViewById(R.id.sheep_home_search_top);
        mTvSearchCoupon = findViewById(R.id.sheep_home_search_coupon);
        mSearchLayout = findViewById(R.id.sheep_home_edit_search);
        mSearchBox = findViewById(R.id.sheep_home_search_box);
        mSearchFlipper = findViewById(R.id.sheep_home_search_flipper);
        // 橱窗&视频 + 分类导航条
        mVideoLayout = findViewById(R.id.video_layout);
        mHeaderTabLayout = findViewById(R.id.header_tab_layout);
        mIvTabClassify = findViewById(R.id.iv_tab_classify);

        // TODO test data
        testTabData(mHeaderTabLayout);
        testFragmentPage();

        mTabHelper.setTabWidth(mHeaderTabLayout, getResources().getDimensionPixelOffset(R.dimen.dp_15));

        setListener();
    }

    private void setListener() {
        mIvTabClassify.setOnClickListener(v -> {

        });
    }



    private void testTabData(TabLayout tabLayout) {
        if (tabLayout == null) {
            return;
        }

        for (int i = 0; i < 10; i++) {
            TabLayout.Tab newTab = tabLayout.newTab();
//            View tabContainer = ViewUtil.getEcoLayoutInflater(getApplicationContext()).inflate(R.layout.item_special_sticky_top_tab, null);
//            TextView text = tabContainer.findViewById(R.id.tv_sticky_top_tab_tag);
            if (i == 0) {
                newTab.setText("Android 弹框使用汇总");
            } else {
                if (i % 2 == 0) {
                    newTab.setText("tab" + i);
                } else {
                    newTab.setText("第" + i + "个tab");
                }
            }
//            newTab.setCustomView(tabContainer);
            tabLayout.addTab(newTab);
        }
    }

    private void testFragmentPage() {
        List<TabHomeModel> fragments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TabHomeModel model = new TabHomeModel();

            model.fragment = NewWidgetUIDetailFragment.newInstance(new Bundle());
            if (i % 2 == 0) {
                model.name = "tab" + i;
            } else {
                model.name = "第" + i + "个tab";
            }
            fragments.add(model);
        }

        if (widgetPagerAdapter == null) {
            widgetPagerAdapter = new UIWidgetPagerAdapter(getSupportFragmentManager(), fragments);
        }
        mViewPager.setAdapter(widgetPagerAdapter);
        mHeaderTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (firstOffSet != verticalOffset) {
            firstOffSet = verticalOffset;
            // 最大滚动位置
            //int maxScroll = appBarLayout.getTotalScrollRange();
            // 默认130dp
            int searchLayoutTopMargin = (int) getResources().getDimension(R.dimen.dp_value_130);
            // 滚动位置
            int defaultOffSet = Math.abs(verticalOffset);
            if (defaultOffSet >= searchLayoutTopMargin) {
                defaultOffSet = searchLayoutTopMargin;
            }

            float offsetProgress = defaultOffSet / (float) searchLayoutTopMargin;
            //LogUtils.d(TAG, "滚动距离：" + verticalOffset + " 最大偏移量：" + maxScroll + "百分比" + offsetProgress + " 默认高度 " + searchLayoutTopMargin);

            //首页title渐变效果
            final float progressTopMargin = 1.f - offsetProgress;
            final float progressScale = 1.f - (0.2f * offsetProgress);
            mHeaderTitle.setPivotX(mHeaderTitle.getWidth() / 2);
            mHeaderTitle.setPivotY(0);
            mHeaderTitle.setScaleX(progressScale);
            mHeaderTitle.setScaleY(progressScale);
            mHeaderTitle.setAlpha(progressTopMargin);

            final float topMargin = getResources().getDimension(R.dimen.dp_value_30);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mHeaderTitle.getLayoutParams();
            lp.topMargin = (int) (topMargin + topMargin * offsetProgress);
            mHeaderTitle.setLayoutParams(lp);


            //搜索框上的图片渐变效果
            final float collapsedHeight = getResources().getDimension(R.dimen.dp_value_20);
            final float initHeight = getResources().getDimension(R.dimen.dp_value_80);
            final int searchTopMargin = (int) (collapsedHeight + (initHeight - collapsedHeight) * progressTopMargin);
            RelativeLayout.LayoutParams searchLayoutParams = (RelativeLayout.LayoutParams) mHeaderSearchTop.getLayoutParams();
            searchLayoutParams.topMargin = searchTopMargin;
            mHeaderSearchTop.setLayoutParams(searchLayoutParams);

            final float progressSearchTop = 1.f - Math.abs(verticalOffset / (initHeight - collapsedHeight));
            mHeaderSearchTop.setAlpha(progressSearchTop);

            // 搜索框 动态效果
            // final float bottomPadding = getResources().getDimension(R.dimen.dp_value_13);
            final float topPadding = getResources().getDimension(R.dimen.dp_value_6);
            final float searchDefaultHeight = getResources().getDimension(R.dimen.dp_value_47);
            final float searchHeight = getResources().getDimension(R.dimen.dp_value_60);
//            ViewGroup.LayoutParams barLayoutParams = (ViewGroup.LayoutParams) appBarLayout.getLayoutParams();
//            barLayoutParams.height = (int) (DeviceUtils.dip2px(getApplicationContext(), 190f) - bottomPadding * offsetProgress);
//            appBarLayout.setLayoutParams(barLayoutParams);

            try {
                // ToolBar topMargin 位置
                FrameLayout.LayoutParams searchParams = (FrameLayout.LayoutParams) mToolbar.getLayoutParams();
                int scrollTopMargin = verticalOffset + searchLayoutTopMargin;
                if (scrollTopMargin <= 0) {
                    searchParams.topMargin = 0;
                } else {
                    searchParams.topMargin = scrollTopMargin;
                }
                //int alpha = 1 - searchParams.topMargin / searchLayoutTopMargin;
                //mToolbar.setBackgroundColor(Color.argb(alpha, 255, 56, 79));
                //mToolbar.setAlpha(alpha);

                RelativeLayout.LayoutParams searchBoxParams = (RelativeLayout.LayoutParams) mSearchBox.getLayoutParams();
                RelativeLayout.LayoutParams tvCouponParams = (RelativeLayout.LayoutParams) mTvSearchCoupon.getLayoutParams();
                tvCouponParams.height = (int) (searchDefaultHeight - (searchHeight - searchDefaultHeight) * offsetProgress);
                searchBoxParams.height = (int) (searchDefaultHeight - (searchHeight - searchDefaultHeight) * offsetProgress);
                int tvPaddingTop = (int) (topPadding - topPadding * progressTopMargin);
                tvCouponParams.topMargin = tvPaddingTop;
                searchBoxParams.topMargin = tvPaddingTop;
                tvCouponParams.bottomMargin = tvPaddingTop;
                searchBoxParams.bottomMargin = tvPaddingTop;

                mSearchBox.setLayoutParams(searchBoxParams);
                mTvSearchCoupon.setLayoutParams(tvCouponParams);

            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), e.toString());
            }
        }
    }




    private void testClickAndTouch() {
        mSearchFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSearchFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    // Handler使用
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    String data = (String) msg.obj;
                    mTvSearchCoupon.setText(data);
                    break;

                default:
                    break;
            }
        }
    };


    private void testHandler() {

        // 在主线程使用Handler


        // 在线程使用Handler
        new Thread(() -> {
            // 发送空消息
            mHandler.sendEmptyMessage(0);

            // 发送message
            Message message = new Message();
            message.obj = "子线程更新主线程UI";
            mHandler.sendMessage(message);

            // 其他在子线程更新主线程UI的方式
            // 1.Activity.runOnUiThread(Runnable);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // do someThings
                    mTvSearchCoupon.setText((String) message.obj);
                }
            });

            // 2.View.post(Runnable)
            mTvSearchCoupon.post(new Runnable() {
                @Override
                public void run() {
                    mTvSearchCoupon.setText((String) message.obj);
                }
            });

            // 3.使用AsyncTask代替Thread。。

        }).start();


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        if (event != null) {
            String name = event.getTitle();

            if (!TextUtils.isEmpty(name)) {
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
