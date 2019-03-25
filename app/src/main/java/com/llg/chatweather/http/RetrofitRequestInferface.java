package com.llg.chatweather.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.llg.chatweather.bean.BaseBean;
import com.llg.chatweather.bean.DailyResultBean;
import com.llg.chatweather.bean.NowResultsBean;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * create by loogen on 2019-3-21
 */
public interface RetrofitRequestInferface {

    //获取当前的天气信息
    @GET("weather/now.json")
    Observable<BaseBean<NowResultsBean>> queryNow(@Query("key") String apiKey,
                                                  @Query("location") String location,
                                                  @Query("language") String language,
                                                  @Query("unit") String unit);

    //获取三日之内的天气情况
    @GET("daily.json")
    Observable<BaseBean<DailyResultBean>> queryDaily(@Query("key") String apiKey,
                                           @Query("location") String language,
                                           @Query("unit") String unit,
                                           @Query("start") String start,
                                           @Query("days") int days);
}
