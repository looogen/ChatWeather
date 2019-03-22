package com.llg.chatweather;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.llg.chatweather.bean.DailyResultBean;
import com.llg.chatweather.bean.NowResultsBean;
import com.llg.chatweather.http.Constant;
import com.llg.chatweather.http.RetrofitRequestService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //1 创建retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        //2 创建网络接口实例
        RetrofitRequestService service = retrofit.create(RetrofitRequestService.class);

      //  https://api.seniverse.com/v3/weather/now.json?key=gszdv59kxtnoorb3&location=beijing&language=zh-Hans&unit=c

        //3 获取可观察的对象
        Observable<ResponseBody> observableDaily = service.queryNow(Constant.API_KEY,"zhuhai","zh-Hans","c");

        observableDaily.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        Log.e(TAG, "onNext: "+ body.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );

                    }
                });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
