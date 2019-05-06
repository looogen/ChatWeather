package com.llg.chatweather.model;

import android.util.Log;

import com.llg.chatweather.view.BaseActivity;
import com.llg.chatweather.bean.BaseBean;
import com.llg.chatweather.bean.NowResultsBean;
import com.llg.chatweather.http.Constant;
import com.llg.chatweather.http.RetrofitRequestInferface;
import com.llg.chatweather.http.RetrofitRequestService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * create by loogen on 2019-3-25
 */
public class WeatherModel {
    private static final String TAG = "WeatherModel";
    private WeatherDataInterface mWeatherDataInterface;
    private RetrofitRequestInferface mRequestInferface;
    private CompositeDisposable mCompositeDisposable;

    public WeatherModel(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
        mRequestInferface =  RetrofitRequestService.getInstance().getService();
    }

    public void setmWeatherDataInterface(WeatherDataInterface mWeatherDataInterface) {
        this.mWeatherDataInterface = mWeatherDataInterface;
    }

    public void getNowWeatherData(String location, String language, String unit) {
        Observable<BaseBean<NowResultsBean>> observable = mRequestInferface.queryNow(Constant.API_KEY, location,language,unit);
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<NowResultsBean>>() {
                    @Override
                    public void accept(BaseBean<NowResultsBean> NowResultsBean) throws Exception {
                        Log.e(TAG, NowResultsBean.getResults().get(0).toString());
                        if (mWeatherDataInterface != null){
                            mWeatherDataInterface.showNowWeatherData(NowResultsBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,throwable.toString());
                        if (mWeatherDataInterface != null){
                            mWeatherDataInterface.refreshError(throwable.toString());
                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }


}
