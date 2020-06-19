package com.llg.chatweather.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseViewModel;
import com.llg.chatweather.base.UnPeekLiveData;
import com.llg.chatweather.data.DataRepository;
import com.llg.chatweather.data.bean.BaseBean;
import com.llg.chatweather.data.bean.NowResultsBean;
import com.llg.chatweather.utils.CommonUtils;

import io.objectbox.android.ObjectBoxLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * create by loogen on 2019-3-28
 */
public class WeatherViewModel extends BaseViewModel {

    private static final String TAG = WeatherViewModel.class.getSimpleName();



    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final ObservableField<String> weather = new ObservableField<>();
    public final MutableLiveData<Integer> weatherCode = new UnPeekLiveData<>();

    public void requestData(String location) {
        refreshing.setValue(true);
        DataRepository.getNowWeatherData(location, CommonUtils.getSystemLanguage(), "")
                .subscribe(new Observer<BaseBean<NowResultsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseBean<NowResultsBean> result) {
                        if (result.getResults().size() > 0) {
                            showNowWeatherData(result.getResults().get(0));
                        } else {
                            // TODO: 2020-5-28 错误信息
                            Log.e(TAG, "requestData: " + result.getStatus() + result.getStatusCode());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 2020-5-28 错误信息
                        Log.e(TAG, e.getMessage() + e.getCause());
                        refreshing.setValue(false);
                    }

                    @Override
                    public void onComplete() {
                        refreshing.setValue(false);
                    }
                });
    }

    private void showNowWeatherData(NowResultsBean nowResultsBean) {
        city.set(nowResultsBean.getLocation().getName());
        temperature.set(nowResultsBean.getNow().getTemperature());
        weather.set(nowResultsBean.getNow().getText());
        weatherCode.setValue(nowResultsBean.getNow().getCode());
    }

}
