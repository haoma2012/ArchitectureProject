package com.mobike.uilibrary.http;

import android.util.Log;
import com.mobike.uilibrary.model.BaseModel;
import com.mobike.uilibrary.model.ChannelListModel;
import com.mobike.uilibrary.model.Translation;
import com.mobike.uilibrary.model.WeatherEntity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 相关网络请求
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 10:27
 */
public class RetrofitHttpUtils {

    private static final String TAG = RetrofitHttpUtils.class.getSimpleName();

    private static final String BASE_URL = "http://wthrcdn.etouch.cn/";
    private static final String YZJ_DEV_URL = "http://test-yzjhd.youzibuy.com/";

//    private static RetrofitHttpUtils instance;

    /**
     * 使用DCL创建单例
     * 推荐直接使用静态内部类
     *
     * @return RetrofitHttpUtils
     */
    public static RetrofitHttpUtils getInstance() {
//        if (instance == null) {
//            synchronized (RetrofitHttpUtils.class) {
//                if (instance == null) {
//                    instance = new RetrofitHttpUtils();
//                }
//            }
//        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * 5.静态内部类
     */
    private static class SingletonHolder {
        private static final RetrofitHttpUtils INSTANCE = new RetrofitHttpUtils();
    }

    // http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
    public void getCiBaData() {
        // 1.创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())// 设置使用Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
        // 2.创建 网络请求接口 的实例
        HttpInterface request = retrofit.create(HttpInterface.class);

        // 3.采用Observable<...>形式 对 网络请求 进行封装
        Observable<Translation> observable = request.getCiBaCall();

        // 4.发送网络请求
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Translation>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Translation translation) {
                        // 显示结果处理
                        if (translation != null) {
                            translation.show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "请求失败");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "请求成功");
                    }
                });

    }

    /**
     * 单纯使用Retrofit的联网请求
     */
    public void doRequestByRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<WeatherEntity> call = weatherService.getMessage("北京");
        call.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                //测试数据返回
                WeatherEntity weatherEntity = response.body();
                Log.e(TAG, "response == " + weatherEntity.getData().getGanmao());
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {
                Log.e(TAG, "Throwable : " + t);
            }
        });
    }


    public void getChannelBrandList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YZJ_DEV_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 2.创建 网络请求接口 的实例
        HttpInterface request = retrofit.create(HttpInterface.class);

        // 3.采用Observable<...>形式 对 网络请求 进行封装
        Observable<BaseModel<ChannelListModel>> observable = request.getChannelBrandCall();

        // 4.发送网络请求
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseModel<ChannelListModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(BaseModel<ChannelListModel> model) {
                        // 显示结果处理
                        if (model != null) {
                            ChannelListModel channelListModel =  model.data;

                            if (channelListModel != null) {
                                Log.d(TAG, "请求成功：" + channelListModel.slogan_picture);
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "请求失败" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "请求成功");
                    }
                });

    }


}
