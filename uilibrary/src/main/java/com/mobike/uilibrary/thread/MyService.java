package com.mobike.uilibrary.thread;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/25 下午3:07
 */
public class MyService extends Service {

    //通过binder实现调用者client与Service之间的通信
    private MyBinder binder = new MyBinder();

    @Override
    public void onCreate() {
        Log.i("Kathy", "onCreate - Thread ID = " + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Kathy", "onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        Log.i("Kathy", "onDestroy");
        super.onDestroy();
    }

    //client 可以通过Binder获取Service实例
    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }


}
