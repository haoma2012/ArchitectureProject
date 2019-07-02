package com.mobike.uilibrary.http;

import com.mobike.uilibrary.model.BaseModel;
import com.mobike.uilibrary.model.ChannelListModel;
import com.mobike.uilibrary.model.Translation;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 注解 + Observable<...>接口描述 网络请求参数
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 10:33
 */
public interface HttpInterface {

    /**
     * // URL模板
     *     //http://fy.iciba.com/ajax.php
     *     // URL实例
     *     //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
     *
     * // 参数说明：
     * // a：固定值 fy
     * // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
     * // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
     * // w：查询内容
     */

    /**
     * // 注解里传入 网络请求 的部分URL地址
     * // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
     * // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
     * // 采用Observable<...>接口
     * // getCall()是接受网络请求数据的方法
     *
     * @return Observable
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCiBaCall();


    // http://test-yzjhd.youzibuy.com/tae_channel_brand?device_id=864368037909625&bundleid=30&resolution=1080%2C1808&platform=android&mode=0&myclient=0730300003000000&tbuid=&sdkversion=28&v_auth=7.xzexVFvqDmzIWjlc2tfTpeDH3g_2HSccO96XQ4epQb4&v=3.0.0&imei=864368037909625&myuid=220054398&app_id=7&channel_id=1&page=1&eco_sdk=2.6.0&device_id=864368037909625&v=3.0.0&platform=android
    @GET("tae_channel_brand?device_id=864368037909625&bundleid=30&resolution=1080%2C1808&platform=android&mode=0&myclient=0730300003000000&tbuid=&sdkversion=28&v_auth=7.xzexVFvqDmzIWjlc2tfTpeDH3g_2HSccO96XQ4epQb4&v=3.0.0&imei=864368037909625&myuid=220054398&app_id=7&channel_id=1&page=1&eco_sdk=2.6.0&device_id=864368037909625&v=3.0.0&platform=android")
    Observable<BaseModel<ChannelListModel>> getChannelBrandCall();

}
