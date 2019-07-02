package com.mobike.framework.http;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-04 16:11
 */
public class OkHttpTest {

    private static final String SHEEP_URL = "https://github.com/hongyangAndroid";

    public void testOkHttpGetUrl() {

        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //创建一个Request
        final Request request = new Request.Builder()
                .url(SHEEP_URL)
                .build();


        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


//    public void testRetrofitGet( ) {
//        // 1.build Retrofit instance
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(SHEEP_URL) // 配置请求地址
//                .addConverterFactory(GsonConverterFactory.create()) // 配置返回数据格式
//                .build(); //使用build模式 创建
//
//        // 2.构建request
//        SheepApi service = retrofit.create(SheepApi.class);
//        // 3. 封装call
//        Call<SheepHomeItemModel> homeListCall = service.getSheepHomeList();
//        // 4.发送异步请求
//        homeListCall.enqueue(new Callback<SheepHomeItemModel>() {
//            @Override
//            public void onResponse(Call<SheepHomeItemModel> call, Response<SheepHomeItemModel> response) {
//                LogUtils.d(TAG, "请求成功" + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<SheepHomeItemModel> call, Throwable t) {
//                LogUtils.d(TAG, "请求失败" + t.toString());
//            }
//        });
//    }
}
