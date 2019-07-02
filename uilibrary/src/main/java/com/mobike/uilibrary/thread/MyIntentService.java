package com.mobike.uilibrary.thread;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/24 下午8:31
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
