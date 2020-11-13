package com.llg.chatweather.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public LiveData<Resource<NowWeatherEntity>> getNowWeatherData(String city, boolean refresh) {

        MutableLiveData<Resource<NowWeatherEntity>> resource = new MutableLiveData<>();
        if (refresh) {
            RetrofitManager.getWeatherAPIService()
                    .queryNow(null, city, null)
                    .doOnNext(DBHelper::addNowWeather)//添加到本地数据库
                    .compose(RxUtils.rxRequestSchedulerHelper())
                    .doOnSubscribe(disposable -> resource.setValue(Resource.loading(null)))
                    .subscribe(new Observer<NowWeatherEntity>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addSubscribe(d);
                        }

                        @Override
                        public void onNext(NowWeatherEntity now) {
                            resource.setValue(Resource.success(now));
                        }

                        @Override
                        public void onError(Throwable e) {
                            resource.setValue(Resource.error(null, e.getMessage()));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
//            Observable.create(emitter -> {
//                NowWeatherEntity entity = DBHelper.getNowWeather(city);
//                emitter.onNext(entity);
//            }).


        }


        return resource;
    }
}
