package com.llg.chatweather.http;

import com.llg.chatweather.bean.DailyResultBean;
import com.llg.chatweather.bean.ErrorBean;
import com.llg.chatweather.bean.NowResultsBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * create by loogen on 2019-3-21
 */
public interface RetrofitRequestService {


    //获取当前的天气信息
    @GET("weather/now.json")
    Observable<ResponseBody> queryNow(@Query("key") String apiKey,
                                      @Query("location") String location,
                                      @Query("language") String language,
                                      @Query("unit") String unit);

    //获取三日之内的天气情况
    @GET("daily.json")
    Observable<DailyResultBean> queryDaily(@Query("key") String apiKey,
                                           @Query("location") String language,
                                           @Query("unit") String unit,
                                           @Query("start") String start,
                                           @Query("days") int days);
}
