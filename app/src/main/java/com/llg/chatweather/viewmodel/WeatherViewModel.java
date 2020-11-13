package com.llg.chatweather.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseViewModel;
import com.llg.chatweather.base.UnPeekLiveData;
import com.llg.chatweather.data.DataRepository;
import com.llg.chatweather.data.Resource;
import com.llg.chatweather.entity.NowWeatherEntity;

/**
 * create by loogen on 2019-3-28
 */
public class WeatherViewModel extends BaseViewModel {

    private static final String TAG = "WeatherViewModel";

    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>("--");
    public final ObservableField<String> weather = new ObservableField<>("--");
    public final MutableLiveData<String> weatherCode = new UnPeekLiveData<>();
    public final MutableLiveData<String> errorMsg = new MutableLiveData<>();
    public final ObservableField<String> updataTime = new ObservableField<>();

    public LiveData<Resource<NowWeatherEntity>> refreshData(String location) {
        return DataRepository.getInstance().getNowWeatherData(location);
    }

    //    public ObjectBoxLiveData<NowWeather> getData(String location) {
//        return DataRepository.getInstance().getWeatherData(location);
//    }
    public void showNowWeatherData(NowWeatherEntity weather) {
        city.set(weather.city);
        temperature.set(weather.tem);
        this.weather.set(weather.wea);
        weatherCode.setValue(weather.wea_img);
        updataTime.set(weather.update_time);
    }

}
