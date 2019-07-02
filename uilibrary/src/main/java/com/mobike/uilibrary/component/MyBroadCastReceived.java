package com.mobike.uilibrary.component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 自定义广播
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 18:29
 */
public class MyBroadCastReceived extends BroadcastReceiver {
    public static String ACTION_BROADCAST = "notification_msg";

    public MyBroadCastReceived() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("test", "notify receive: " + intent.getAction());
        if (ACTION_BROADCAST.equals(intent.getAction())) {
            Toast.makeText(context, "收到ACTION_BROADCAST广播了", Toast.LENGTH_SHORT).show();
            NotificationMsgManager.getInstance().showNotification(1, null);
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            Toast.makeText(context, "屏幕关闭了", Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Toast.makeText(context, "屏幕打开了", Toast.LENGTH_SHORT).show();

        }
    }
}
