package com.llg.chatweather.http;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * create by loogen on 2019-3-21
 */
public interface RetrofitRequestService {

    // @GET("users/{user}/repos")
    //  Call<List<Repo>> listRepos(@Path("user") String user);
    @GET("now.json?key={api_key}&location={location}&language={language}&unit={unit}")
    Call<Integer> queryNow();


}
