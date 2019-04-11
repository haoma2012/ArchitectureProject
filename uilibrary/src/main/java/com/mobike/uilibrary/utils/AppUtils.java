package com.mobike.uilibrary.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * 常用API使用：https://juejin.im/post/58c407ee44d90400698757d8
 * <p>
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/3 下午2:42
 */
public class AppUtils {

    private Context mContext;
    private String packageName;

    public AppUtils(Context context) {
        mContext = context;
        packageName = context.getPackageName();
    }

    /**
     * 资源名称、资源类型、应用包名
     *
     * @param view    view
     * @param resName 本地资源名称 ："ic_launcher"
     * @param defType 资源类型"drawable"
     */
    public void setViewIdentify(View view, String resName, String defType) {
        if (mContext == null || view == null) {
            return;
        }

        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(mContext.getResources().getIdentifier(resName, defType, packageName));

        } else {
            view.setBackgroundResource(mContext.getResources().getIdentifier(resName, defType, packageName));
        }
    }
}
