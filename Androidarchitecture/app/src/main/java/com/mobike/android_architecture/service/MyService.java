package com.mobike.android_architecture.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mobike.android_architecture.MainActivity;
import com.mobike.android_architecture.R;

/**
 * 我的服务
 * Created by yangdehao@xiaoyouzi.com  on 2019-05-07 19:21
 */
public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    public MyBinder myBinder;
    Handler mHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
        myBinder = new MyBinder();

        // 创建前台进程
        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        //新建Builer对象
        Notification.Builder builer = new Notification.Builder(this);
        builer.setContentTitle("前台服务通知的标题");//设置通知的标题
        builer.setContentText("前台服务通知的内容");//设置通知的内容
        builer.setSmallIcon(R.mipmap.ic_launcher);//设置通知的图标
        builer.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builer.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand");
        mHandler.sendMessageDelayed(new Message(), 2000);
        return super.onStartCommand(intent, flags, startId);


    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService onBind");
        if (myBinder == null) {
            myBinder = new MyBinder();
        }
        return myBinder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onStartCommand");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MyService onUnbind");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {
        public void service_connect_Activity() {
            Log.d(TAG, "Service关联了Activity,并在Activity执行了Service的方法");

        }

    }

//    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//        }
//
//
//    };

}
