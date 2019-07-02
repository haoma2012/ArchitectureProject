package com.mobike.uilibrary.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/19 下午5:29
 */
public class NotifationUtils {
    private static final String TAG = NotifationUtils.class.getSimpleName();

    public static boolean isOpenNotification(Context context) {
        if (context == null) {
            return false;
        }
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        boolean isOpen = manager.areNotificationsEnabled();
        Log.d(TAG, "notification open " + isOpen);
        return isOpen;
    }


    public static void openSettingPage(Context context) {
        if (context == null) {
            return;
        }
        // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
