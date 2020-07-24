package com.llg.chatweather.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseViewModel;
import com.llg.chatweather.base.UnPeekLiveData;
import com.llg.chatweather.data.DataRepository;
import com.llg.chatweather.data.db.entity.CityWeather;
import com.llg.chatweather.utils.CommonUtils;

import io.objectbox.android.ObjectBoxLiveData;

/**
 * create by loogen on 2019-3-28
 */
public class WeatherViewModel extends BaseViewModel {

    private static final String TAG = WeatherViewModel.class.getSimpleName();

    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final ObservableField<String> weather = new ObservableField<>();
    public final MutableLiveData<Integer> weatherCode = new UnPeekLiveData<>();

    public LiveData<CityWeather> refreshData(String location) {
        return DataRepository.getInstance().getNowWeatherData(location, CommonUtils.getSystemLanguage(), "");
    }

    public ObjectBoxLiveData<CityWeather> getData(String location) {
        return DataRepository.getInstance().getWeatherData(location);
    }

    public void showNowWeatherData(CityWeather cityWeather) {
        if (cityWeather == null) {
            return;
        }
        city.set(cityWeather.name);
        temperature.set(cityWeather.temperature);
        weather.set(cityWeather.text);
        weatherCode.setValue(cityWeather.code);
    }

}
