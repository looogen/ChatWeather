package com.llg.chatweather.data;

import com.llg.chatweather.base.BaseModel;
import com.llg.chatweather.data.bean.BaseBean;
import com.llg.chatweather.data.bean.NowResultsBean;
import com.llg.chatweather.http.Constant;
import com.llg.chatweather.http.RetrofitRequestService;
import com.llg.chatweather.utils.RxUtils;

import io.reactivex.Observable;

/**
 * create by loogen on 2019-3-25
 */
public class DataRepository extends BaseModel {
    private static final String TAG = "DataRepository";

    public static Observable<BaseBean<NowResultsBean>> getNowWeatherData(String location, String language, String unit) {
        return RetrofitRequestService.getInstance().getService()
                .queryNow(Constant.API_KEY, location, language, unit)
                .compose(RxUtils.rxRequestSchedulerHelper());
    }

    @Override
    public void onClear() {
        super.onClear();

    }
}
