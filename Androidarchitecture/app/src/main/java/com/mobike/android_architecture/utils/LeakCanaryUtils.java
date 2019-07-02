package com.mobike.android_architecture.utils;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by 李杰 on 2018/4/4.
 */

public class LeakCanaryUtils {
    private static RefWatcher refWatcher;

    public static void setupLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            refWatcher = RefWatcher.DISABLED;
        }
        refWatcher = LeakCanary.install(application);
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
