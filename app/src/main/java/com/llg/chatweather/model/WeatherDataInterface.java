package com.llg.chatweather.model;

import com.llg.chatweather.bean.BaseBean;
import com.llg.chatweather.bean.NowResultsBean;

/**
 * create by loogen on 2019-3-25
 */
public interface WeatherDataInterface {
    void showNowWeatherData(BaseBean<NowResultsBean> bean);
    void refreshError(String msg);
}
