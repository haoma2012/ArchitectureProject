package com.mobike.uilibrary.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Window;
import com.mobike.framework.event.BaseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * BaseActivity 基类
 * TODO 1.状态栏设置 2.固定title样式 3.跳转方法 4.接收传值方法 5.EventBus 6.加载动画
 * Created by yangdehao@xiaoyouzi.com  on 2018/8/6 上午10:17
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected boolean isHandleEventBus = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.context = this;
//        this.initDilutions();
//        this.initLayoutInflater();
//        this.initSwipeBack();
//        this.initEventBus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            // set an enter transition Explode Slide Fade
            getWindow().setEnterTransition(new Slide());
            // set an exit transition
            getWindow().setExitTransition(new Slide());
        }
        setContentView(getLayoutId());
        initEventBus();
        beforeInitView(savedInstanceState);
        // 初始化View
        initView();
    }

    /**
     * 初始化前执行
     * @param savedInstanceState bundle
     */
    protected void beforeInitView(@Nullable Bundle savedInstanceState) {
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    private void initEventBus() {
        if (this.isHandleEventBus && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (this.isHandleEventBus && EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BaseEvent event) {

    }
}
