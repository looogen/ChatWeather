package com.llg.chatweather.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseModel;
import com.llg.chatweather.data.bean.NowResultsBean;
import com.llg.chatweather.data.db.DBHelper;
import com.llg.chatweather.data.db.entity.CityWeather;
import com.llg.chatweather.http.Constant;
import com.llg.chatweather.http.RetrofitRequestService;
import com.llg.chatweather.utils.RxUtils;

import io.objectbox.android.ObjectBoxLiveData;
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

    private MutableLiveData<CityWeather> cityWeatherLiveData = new MutableLiveData<>();

    public LiveData<CityWeather> getNowWeatherData(String location, String language, String unit) {
        Disposable disposable = RetrofitRequestService.getInstance().getService()
                .queryNow(Constant.API_KEY, location, language, unit)
                .compose(RxUtils.rxRequestSchedulerHelper())
                .subscribe(result -> {
                    NowResultsBean now = result.getResults().get(0);
                    if (now != null) {
                        CityWeather weather = new CityWeather(
                                now.getLocation().getId(),
                                now.getLocation().getName(),
                                now.getNow().getText(),
                                now.getNow().getCode(),
                                now.getNow().getTemperature(),
                                now.getLast_update(),
                                now.getNow().getWind_direction(),
                                now.getNow().getWind_scale());
                        cityWeatherLiveData.setValue(weather);
                        DBHelper.addCityWeather(weather);
                    }
                });
        compositeDisposable.add(disposable);
        return cityWeatherLiveData;
    }

    public ObjectBoxLiveData<CityWeather> getWeatherData(String location) {
        return DBHelper.getCityWeather(location);
    }
}
