package com.mobike.framework.rx;

import android.graphics.Bitmap;
import android.util.Log;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RxJava使用过程详解
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-04 20:54
 */
public class TestRxJavaDemo {

    private static final String TAG = TestRxJavaDemo.class.getSimpleName();

    private static TestRxJavaDemo instance;

    public static TestRxJavaDemo getInstance() {
        if (instance == null) {
            instance = new TestRxJavaDemo();
        }
        return instance;
    }

    /**
     * 创建被观察者方式
     * 1.Observable.create(ObservableOnSubscribe<T> source)
     * 2.Observable.just(T...)
     * 3.Observable.fromArray(T... items);
     */
    public Observable createObservable() {
        // 1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            // create() 是 RxJava 最基本的创造事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> subscriber) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onComplete();

            }
        });

        // 方法1：just(T...)：直接将传入的参数依次发送出来
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();
        Observable observable1 = Observable.just(1, 2, 3);
        // 方法2：from(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
        String[] words = {"A", "B", "C"};
        Observable observable2 = Observable.fromArray(words);
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();

        // 1. 设置一个集合
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        Observable observable3 =  Observable.fromIterable(list);




        // 延迟操作 defer
//        final Integer i = 10;
//        final Observable observable4 = Observable.defer(new Callable<ObservableSource>() {
//            @Override
//            public ObservableSource call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//        Observable.timer(2, TimeUnit.SECONDS).subscribe(getDefaultObserver());
//        Observable.interval(2, TimeUnit.SECONDS).subscribe(getDefaultObserver());
//        // 参数1 = 事件序列起始点；
//        // 参数2 = 事件数量；
//        // 参数3 = 第1次事件延迟发送时间；
//        // 参数4 = 间隔时间数字；
//        // 参数5 = 时间单位
//        Observable.intervalRange(3,10,2, 1, TimeUnit.SECONDS).subscribe(getDefaultObserver());
//        Observable.range(3, 10).subscribe(getDefaultObserver());
//        Observable.rangeLong(3,10).subscribe(getDefaultObserver());


        return observable;
    }

    private Observer getDefaultObserver() {
        // 1. 创建观察者 （Observer ）对象
        Observer<Integer> observer = new Observer<Integer>() {
            // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件

            // 1. 定义Disposable类变量
            private Disposable mDisposable;
            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");

                mDisposable = d;
            }

            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件作出响应" + value);
                if (value == 2) { // 设置在接收到第二个事件后切断观察者和被观察者的连接
                    mDisposable.dispose();
                    Log.d(TAG, "已经切断了连接：" + mDisposable.isDisposed());
                }
            }

            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        return observer;
    }

    /**
     * 创建观察者
     */
    public void createSubScriber() {
        //<--方式1：采用Observer 接口 -->
        // 1. 创建观察者 （Observer ）对象
        Observer<Integer> observer = new Observer<Integer>() {
            // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件

            // 1. 定义Disposable类变量
            private Disposable mDisposable;
            // 观察者接收事件前，默认最先调用复写 onSubscribe（）
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");

                mDisposable = d;
            }

            // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件作出响应" + value);
                if (value == 2) { // 设置在接收到第二个事件后切断观察者和被观察者的连接
                    mDisposable.dispose();
                    Log.d(TAG, "已经切断了连接：" + mDisposable.isDisposed());
                }
            }

            // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        final Subscriber subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件作出响应" + value);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };





        // 订阅
        Observable observable = createObservable();
        if (observable != null) {
            observable.subscribe(observer);
        } else {
            if (subscriber != null) {
                observable.subscribe((Observer) subscriber);
            }
        }

        // 使用流式API
        //这就是RxJava的流式API调用
        Observable.just("On", "Off", "On", "On")
                // 2. 使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：字符串变换成整形类型
                .map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) throws Exception {
                if ("On".equalsIgnoreCase(s)) {
                    return 1;
                } else if ("Off".equalsIgnoreCase(s)) {
                    return 0;
                }
                return 1;
            }
        })
                //在传递过程中对事件进行过滤操作
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        return s！=null;
//                    }
//                })
                .subscribe(getDefaultObserver());





    }


    public void realUseRxJava() {
        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "对Next事件作出响应" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };
        // 基于RxJava的链式操作
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                // 注：建议发送事件前检查观察者的isUnsubscribed状态，以便在没有观察者时，让Observable停止发射数据
                emitter.onNext("ydh");
                emitter.onNext("LiHang");
                emitter.onNext("saonan");
                emitter.onComplete();
            }
        })
                .subscribe(observer);

        // 简单使用
        Observable.just("one","one","one","one","one","one","one","one","one","one").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

    }


    /**
     * 线程切换
     */

    public void createThreadRxJava() {
        Observable.just("http://www.qiniu.com/12233444.png")
                .throttleFirst(1, TimeUnit.SECONDS)
                //指定了被观察者执行的线程环境
                .subscribeOn(Schedulers.newThread())
                //将接下来执行的线程环境指定为io线程
                .observeOn(Schedulers.io())
                //使用map操作来完成类型转换
                .map(new Function<String, Bitmap>() {

                    @Override
                    public Bitmap apply(String s) throws Exception {
                        // 下载文件转换成bitmap 一个耗时操作
                        return null;
                    }
                })
                //将后面执行的线程环境切换为主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * Android新增
     * 1.AndroidSchedulers提供了针对Android的线程系统的调度器
     *
     */
    public void testAndroidObserve() {


        // 背压Flowable
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {

            Subscription sub;
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {


            }
        }, BackpressureStrategy.ERROR);

        // 订阅
        upstream.subscribe(new Subscriber<Integer>() {
            Subscription sub;

                    @Override
                    public void onSubscribe(Subscription s) {
                        sub = s;

                        sub.request(1);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "对Next事件作出响应" + integer);
                        sub.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "对onError事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }
}
