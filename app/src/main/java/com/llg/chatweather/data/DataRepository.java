package com.llg.chatweather.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseModel;
import com.llg.chatweather.db.DBHelper;
import com.llg.chatweather.entity.NowWeatherEntity;
import com.llg.chatweather.http.RetrofitManager;
import com.llg.chatweather.utils.RxUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
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

    public LiveData<Resource<NowWeatherEntity>> getNowWeatherData(String city) {
        MutableLiveData<Resource<NowWeatherEntity>> resource = new MutableLiveData<>();
        Observable<NowWeatherEntity> httpObservable = RetrofitManager.getWeatherAPIService()
                .queryNow(null, city, null)
                .doOnNext(weather -> {
                    Log.e(TAG, "httpObservable doOnNext");
                    DBHelper.addNowWeather(weather);
                });

        Observable<NowWeatherEntity> dbObservable = Observable.create(emitter -> {
            NowWeatherEntity weather = DBHelper.getNowWeather(city);
            Log.e(TAG, "dbObservable: create");
            if (weather != null) {
                emitter.onNext(weather);
            }
            emitter.onComplete();
        });

        Observable.concat(dbObservable, httpObservable) //串行事件流
                .filter(weather -> weather != null)
                .distinct(weather -> weather.update_time) //记录key，确保不会发生重复的事件
                .takeUntil(weather -> { //返回true终止事件流
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
                    Date cur = f.parse(weather.update_time);
                    return System.currentTimeMillis() - cur.getTime() <= 1 * 60 * 60 * 1_000;  //1小时内
                }).compose(RxUtils.rxRequestSchedulerHelper())
                .doOnSubscribe(disposable -> resource.setValue(Resource.loading(null)))
                .subscribe(new Observer<NowWeatherEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(NowWeatherEntity weather) {
                        resource.setValue(Resource.success(weather));
                    }

                    @Override
                    public void onError(Throwable e) {
                        resource.setValue(Resource.error(null, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return resource;
    }
}
