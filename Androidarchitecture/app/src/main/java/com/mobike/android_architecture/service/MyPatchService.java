package com.mobike.android_architecture.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.mobike.android_architecture.utils.TinkerLog;

/**
 * 补丁加载类
 * 参考TinkerPatchService
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-08 09:31
 */
public class MyPatchService {
    private static final String TAG = "MyPatchService";
    private static final int MIN_SDKVER_TO_USE_JOBSCHEDULER = 26;
    private static final String PATCH_PATH_EXTRA = "patch_path_extra";
    private static final String RESULT_CLASS_EXTRA = "patch_result_class";
    private static int notificationId = -1119860829;

    private static Class<? extends IntentService> resultServiceClass = null;

    public static void runPatchService(Context context, String path) {
        if (Build.VERSION.SDK_INT < MIN_SDKVER_TO_USE_JOBSCHEDULER) {
            runPatchServiceByIntentService(context, path);
        } else {

        }
    }


    /**
     * 启动服务
     * @param context
     * @param path
     */
    private static void runPatchServiceByIntentService(Context context, String path) {
        TinkerLog.i(TAG, "run patch service by intent service.");
        Intent intent = new Intent(context, IntentServiceRunner.class);
        intent.putExtra(PATCH_PATH_EXTRA, path);
        intent.putExtra(RESULT_CLASS_EXTRA, resultServiceClass.getName());
        context.startService(intent);
    }


    public static class IntentServiceRunner extends IntentService {

        public IntentServiceRunner(String name) {
            super("MyPatchService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            // 处理something
            increasingPriority();
            doApplyPatch(getApplicationContext(), intent);
        }

        private void doApplyPatch(Context context, Intent intent) {


        }

        private void increasingPriority() {
//        if (Build.VERSION.SDK_INT > 24) {
//            TinkerLog.i(TAG, "for Android 7.1, we just ignore increasingPriority job");
//            return;
//        }
            if (Build.VERSION.SDK_INT >= 26) {
                TinkerLog.i(TAG, "for system version >= Android O, we just ignore increasingPriority "
                        + "job to avoid crash or toasts.");
                return;
            }

            if ("ZUK".equals(Build.MANUFACTURER)) {
                TinkerLog.i(TAG, "for ZUK device, we just ignore increasingPriority "
                        + "job to avoid crash.");
                return;
            }

            TinkerLog.i(TAG, "try to increase patch process priority");
            try {
                Notification notification = new Notification();
                if (Build.VERSION.SDK_INT < 18) {
                    startForeground(notificationId, notification);
                } else {
                    startForeground(notificationId, notification);
                    // start InnerService
                    startService(new Intent(this, InnerService.class));
                }
            } catch (Throwable e) {
                TinkerLog.i(TAG, "try to increase patch process priority error:" + e);
            }
        }


        /**
         * I don't want to do this, believe me
         */
        //InnerService
        public static class InnerService extends Service {
            @Override
            public void onCreate() {
                super.onCreate();
                try {
                    startForeground(notificationId, new Notification());
                } catch (Throwable e) {
                    TinkerLog.e(TAG, "InnerService set service for push exception:%s.", e);
                }
                // kill
                stopSelf();
            }

            @Override
            public void onDestroy() {
                stopForeground(true);
                super.onDestroy();
            }

            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        }
    }

}
