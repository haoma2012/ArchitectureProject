package com.mobike.uilibrary.http;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 11:04
 */

import com.mobike.uilibrary.model.WeatherEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 描述：第一步:定义一个接口配置网络请求
 */
public interface WeatherService {
    //  网络接口的使用为查询天气的接口
//
    @GET("weather_mini")
//  此处回调返回的可为任意类型Call<T>，再也不用自己去解析json数据啦！！！
    Call<WeatherEntity> getMessage(@Query("city") String city);
}