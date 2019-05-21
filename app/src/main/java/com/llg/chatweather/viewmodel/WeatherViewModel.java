package com.llg.chatweather.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.llg.chatweather.utils.CommonUtils;
import com.llg.chatweather.view.BaseActivity;
import com.llg.chatweather.bean.BaseBean;
import com.llg.chatweather.bean.NowResultsBean;
import com.llg.chatweather.model.WeatherDataInterface;
import com.llg.chatweather.model.WeatherModel;
import com.llg.chatweather.view.fragment.BaseFragment;

/**
 * create by loogen on 2019-3-28
 */
public class WeatherViewModel extends BaseObservable implements WeatherDataInterface {
    private static final String TAG = WeatherViewModel.class.getSimpleName();

    public final ObservableField<String> city = new ObservableField<>("-- --");
    public final ObservableField<String> temperature = new ObservableField<>("NaN");
    public final ObservableField<String> weather = new ObservableField<>();
    public final ObservableField<String> weatherCode = new ObservableField<>();
    private WeatherModel model;

    public WeatherViewModel(BaseActivity activity){
        model = new WeatherModel(activity.mCompositeDisposable);
        model.setmWeatherDataInterface(this);
    }

    public WeatherViewModel(BaseFragment fragment){
        model = new WeatherModel(fragment.mCompositeDisposable);
        model.setmWeatherDataInterface(this);
    }
    public void requestData(String location) {
        model.getNowWeatherData(location, CommonUtils.getSystemLanguage(),"");
    }

    @Override
    public void showNowWeatherData(BaseBean<NowResultsBean> bean) {
        NowResultsBean nowResultsBean = bean.getResults().get(0);
        Log.e("ViewModel",nowResultsBean.toString());
        city.set(nowResultsBean.getLocation().getName());
        temperature.set(nowResultsBean.getNow().getTemperature());
        weather.set(nowResultsBean.getNow().getText());
        weatherCode.set(nowResultsBean.getNow().getCode());
    }

    @Override
    public void refreshError(String msg) {

    }
}
