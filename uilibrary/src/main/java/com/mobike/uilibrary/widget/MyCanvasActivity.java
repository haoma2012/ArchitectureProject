package com.mobike.uilibrary.widget;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.base.BaseActivity;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-24 20:49
 */
public class MyCanvasActivity extends BaseActivity {

    /**
     * Intent跳转
     */
    public static void enterActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyCanvasActivity.class);
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
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView() {

    }
}
