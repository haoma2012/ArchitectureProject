package com.mobike.android_architecture.ui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.mobike.android_architecture.utils.LeakCanaryUtils;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/18 下午4:57
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化信息
        initImportant();

    }

    private void initImportant() {

        LeakCanaryUtils.setupLeakCanary(this);
    }


}
