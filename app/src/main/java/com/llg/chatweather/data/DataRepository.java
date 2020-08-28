package com.llg.chatweather.data;

import com.llg.chatweather.base.BaseModel;
import com.llg.chatweather.data.entity.NowWeather;
import com.llg.chatweather.http.RetrofitManager;
import com.llg.chatweather.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * create by loogen on 2019-3-25
 */
public class DataRepository extends BaseModel {
    private static final String TAG = "DataRepository";

    private DataRepository() {
    }

    private static DataRepository repository;

    public static DataRepository getInstance() {
        if (repository == null) {
            repository = new DataRepository();
        }
        return repository;
    }

    public void getNowWeatherData(String city, DataResult<NowWeather> result) {
        RetrofitManager.getWeatherAPIService()
                .queryNow(null, city, null)
                .compose(RxUtils.rxRequestSchedulerHelper())
                .subscribe(new Observer<NowWeather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NowWeather nowWeather) {
                        result.setResult(nowWeather, new NetState());
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setResult(new NetState(e.getMessage(), false));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    public ObjectBoxLiveData<NowWeather> getWeatherData(String location) {
//        return DBHelper.getCityWeather(location);
//    }

}
