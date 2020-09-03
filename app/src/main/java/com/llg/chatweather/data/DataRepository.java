package com.llg.chatweather.data;

import com.llg.chatweather.base.BaseModel;
import com.llg.chatweather.db.DBHelper;
import com.llg.chatweather.entity.NowWeatherEntity;
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

    public void getNowWeatherData(String city, DataResult<NowWeatherEntity> result) {
        RetrofitManager.getWeatherAPIService()
                .queryNow(null, city, null)
                .compose(RxUtils.rxRequestSchedulerHelper())
                .subscribe(new Observer<NowWeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(NowWeatherEntity now) {
                        //添加到数据库
                        DBHelper.addNowWeather(now);
                        result.setResult(now, new NetState());
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
