package com.llg.chatweather.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * create by loogen on 2019-3-25
 */
public class RetrofitRequestService {

    private static RetrofitRequestService sRetrofitRequestService;

    private RetrofitRequestService(){

    }

    public static RetrofitRequestService getInstance(){
        if (sRetrofitRequestService == null){
            synchronized (RetrofitRequestService.class){
                if (sRetrofitRequestService == null){
                    sRetrofitRequestService = new RetrofitRequestService();
                }
            }
        }
        return sRetrofitRequestService;
    }

    public RetrofitRequestInferface getService() {
        //1 创建retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //2 创建网络接口实例
        return retrofit.create(RetrofitRequestInferface.class);
    }




}
