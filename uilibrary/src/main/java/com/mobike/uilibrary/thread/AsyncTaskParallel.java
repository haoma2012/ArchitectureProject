package com.mobike.uilibrary.thread;

import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Created by yangdehao@xiaoyouzi.com  on 2019/4/24 下午2:43
 */
public abstract class AsyncTaskParallel<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    //    private static ExecutorService SINGLE_TASK_EXECUTOR;
//    private static ExecutorService LIMITED_TASK_EXECUTOR;
    private static ExecutorService FULL_TASK_EXECUTOR;

    static {
//        SINGLE_TASK_EXECUTOR = Executors.newSingleThreadExecutor();
//        LIMITED_TASK_EXECUTOR = Executors.newFixedThreadPool(7);
        FULL_TASK_EXECUTOR = Executors.newCachedThreadPool();
    }


    /**
     * 并行执行
     * execute 在4.0后默认效果是 先进先出，一条线程执行；
     * executeOnExecutor 可以用改为线程池运行
     * http://blog.csdn.net/mddy2001/article/details/17127065
     * <p>
     * 自定义的CorePoolSize为7的Executor(Executors.newFixedThreadPool(7))：
     * 使用未设限制的Executor(Executors.newCachedThreadPool())：
     * 默认效果，单个线程池的executeOnExecutor(AsyncTask.SERIAL_EXECUTOR)
     * 内置的5个线程池的： AsyncTask.THREAD_POOL_EXECUTOR
     *
     * @param params
     * @return
     */
    public AsyncTask executeParallel(Params... params) {
        return executeOnExecutor(FULL_TASK_EXECUTOR, params);
    }
}