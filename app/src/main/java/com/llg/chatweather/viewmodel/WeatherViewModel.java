package com.llg.chatweather.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.llg.chatweather.base.BaseViewModel;
import com.llg.chatweather.data.DataRepository;
import com.llg.chatweather.data.bean.NowResultsBean;
import com.llg.chatweather.utils.CommonUtils;

/**
 * create by loogen on 2019-3-28
 */
public class WeatherViewModel extends BaseViewModel {

    private static final String TAG = WeatherViewModel.class.getSimpleName();

    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final ObservableField<String> weather = new ObservableField<>();
    public final ObservableInt weatherCode = new ObservableInt(-1);

    public void requestData(String location) {
        addSubscribe(DataRepository.getNowWeatherData(location, CommonUtils.getSystemLanguage(),"")
                .subscribe(result -> {
                    if (result.getResults().size()>0) {
                        refreshing.setValue(false);
                        showNowWeatherData(result.getResults().get(0));
                    }else {
                        // TODO: 2020-5-28 错误信息
                    }
                }));
    }

    private void showNowWeatherData(NowResultsBean nowResultsBean) {
        city.set(nowResultsBean.getLocation().getName());
        temperature.set(nowResultsBean.getNow().getTemperature());
        weather.set(nowResultsBean.getNow().getText());
        weatherCode.set(nowResultsBean.getNow().getCode());
    }

}
