package com.llg.chatweather.http;

public class RetrofitManager {

    private static WeatherAPIService mWeatherAPIService;

    public static WeatherAPIService getWeatherAPIService() {
        if (mWeatherAPIService == null) {
            mWeatherAPIService = RetrofitFactory.getRetrofit().create(WeatherAPIService.class);
        }
        return mWeatherAPIService;
    }

}
