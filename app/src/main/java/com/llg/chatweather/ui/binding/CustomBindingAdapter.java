package com.llg.chatweather.ui.binding;

import androidx.databinding.BindingAdapter;

import com.llg.chatweather.widget.animview.SkyView;

/**
 * create by loogen on 2020-5-29
 */
public class CustomBindingAdapter {

    @BindingAdapter(value = {"weatherCode"} ,requireAll = false)
    public static void setSkyView(SkyView skyView, int weatherCode){
        skyView.setWeatherCode(weatherCode);
    }

}
