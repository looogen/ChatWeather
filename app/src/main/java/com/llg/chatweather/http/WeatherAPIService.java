package com.llg.chatweather.http;

import com.llg.chatweather.entity.DetailWeatherEntity;
import com.llg.chatweather.entity.NowWeatherEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * create by loogen on 2019-3-21
 */
public interface WeatherAPIService {

    //免费实况天气接口
    @GET("api?version=v6")
    Observable<NowWeatherEntity> queryNow(@Query("cityid") String cityId,
                                          @Query("city") String city,
                                          @Query("ip") String ipAddress);

    //免费七日天气接口
    @GET("api?version=v1")
    Observable<DetailWeatherEntity> queryDetail(@Query("cityid") String cityId,
                                                @Query("city") String city,
                                                @Query("ip") String ipAddress);
}
