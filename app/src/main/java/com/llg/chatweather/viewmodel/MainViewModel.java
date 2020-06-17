package com.llg.chatweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * create by loogen on 2020-5-26
 */
public class MainViewModel extends BaseViewModel {
    public final MutableLiveData<Integer> curItem = new MutableLiveData<>(0);
    public final MutableLiveData<String> curCity = new MutableLiveData<>();
    public final MutableLiveData<List<String>> cityList = new MutableLiveData<>();
    public final MutableLiveData<Integer> weatherCode = new MutableLiveData<>(-1);

    {
        cityList.setValue(Arrays.asList("珠海", "北京", "武汉", "拉萨"));
    }
}
