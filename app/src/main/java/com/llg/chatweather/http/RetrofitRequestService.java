package com.llg.chatweather.http;

import com.llg.chatweather.bean.DailyResultBean;
import com.llg.chatweather.bean.ErrorBean;
import com.llg.chatweather.bean.NowResultsBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * create by loogen on 2019-3-21
 */
public interface RetrofitRequestService {


    //获取当前的天气信息
    @GET("now.json?key={api_key}&location={location}&language={language}&unit={unit}")
    Observable<NowResultsBean> queryNow(@Path("api_key") String apiKey,
                                        @Path("location") String location,
                                        @Path("language") String language,
                                        @Path("unit") String unit);


    @GET("daily.json?key={api_key}&location={location}&language={language}&unit={unit}&start={start}&days={days}")
    Observable<DailyResultBean> queryDaily(@Path("api_key") String apiKey,
                                           @Path("location") String language,
                                           @Path("unit") String unit,
                                           @Path("start") String start,
                                           @Path("days") int days);
}
