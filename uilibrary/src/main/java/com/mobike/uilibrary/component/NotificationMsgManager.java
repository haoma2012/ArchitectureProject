package com.mobike.uilibrary.component;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.mobike.uilibrary.R;
import com.mobike.uilibrary.widget.UIWidgetActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 18:49
 */
public class NotificationMsgManager {

    public static final String ACTION_FLASH_SALE = "action.flash.sale";
    public static final int NOTIFICATION_ID_FLASH_SALE = 1000; //通知栏消息ID
    public static final int FLASH_SALE_REMINDER_DELAY_MINS = 5 * 60 * 1000; //限时抢购提醒，在设置后的5分钟
    public static final int MSG_ICON = R.drawable.apk_bottom_ic_buy;
    public static final String MSG_TITLE = "new msg";

    private static NotificationMsgManager mInstance;
    private Context mContext;
    private AlarmManager mAlarmManager;
    private Map<String, PendingIntent> mFlashSalePendingIntents; //已发送定时请求，通过 时间段+pos作为 PendingIntent key

    private NotificationMsgManager() {
        mFlashSalePendingIntents = new HashMap<>();
    }

    public static NotificationMsgManager getInstance() {
        if (mInstance == null) {
            synchronized (NotificationMsgManager.class) {
                if (mInstance == null) {
                    mInstance = new NotificationMsgManager();
                }
            }
        }
        return mInstance;
    }

    public void setContext(@NonNull Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
    }

    public void showNotification(int notifyId, Class<?> targetClass) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC); //锁屏可见并显示全部内容
        builder.setAutoCancel(true);
        builder.setSmallIcon(MSG_ICON);
        builder.setContentTitle(MSG_TITLE);
        builder.setContentText("new msg got");
        builder.setCategory(Notification.CATEGORY_MESSAGE);
        builder.setPriority(NotificationCompat.PRIORITY_MAX); //系统通知消息
        Intent intent = new Intent(mContext, UIWidgetActivity.class);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyId, builder.build());
    }

    public void registerFlashSaleNotification(long triggerTime, @NonNull final String id) {
        Intent intent = new Intent(ACTION_FLASH_SALE);
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Todo 已发送定时提醒需要缓存到File，并同步Cache数据
        mFlashSalePendingIntents.put(id, pendingIntent);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
    }

    public void cancelFlashSaleNotification(String requestId) {
        PendingIntent pendingIntent = mFlashSalePendingIntents.get(requestId);
        if(pendingIntent != null) {
            mAlarmManager.cancel(pendingIntent);
            //Todo 已发送定时提醒需要缓存到File，并同步Cache数据
            mFlashSalePendingIntents.remove(requestId);
        }
    }

    public long getFlashSaleReminderTime() {
        return (System.currentTimeMillis() + FLASH_SALE_REMINDER_DELAY_MINS);
    }
}
