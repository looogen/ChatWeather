package com.llg.chatweather.http;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * create by loogen on 2019-3-25
 */
public class RetrofitFactory {
    private static final String TAG = "Retrofit";
    private static final Retrofit mRetrofit = defaultFactory();

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }


    private static Retrofit defaultFactory() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack ======================= " + message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);


        //通过拦截器统一给API加上 appid 和 appsecrect
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url();
            HttpUrl newUrl = url.newBuilder()
                    .addQueryParameter("appid", Constant.APP_ID)
                    .addQueryParameter("appsecret", Constant.API_SECRETE)
                    .build();
            Request newRequest = request.newBuilder().url(newUrl).build();
            Log.e(TAG, "intercept: " + newRequest);
            return chain.proceed(newRequest);
        }).addInterceptor(loggingInterceptor).build();


        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_API)
                .client(client)
                .addConverterFactory(WeatherConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
