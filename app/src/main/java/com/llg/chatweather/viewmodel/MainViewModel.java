package com.llg.chatweather.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.llg.chatweather.base.BaseViewModel;

import java.util.List;

/**
 * create by loogen on 2020-5-26
 */
public class MainViewModel extends BaseViewModel {
    public final MutableLiveData<Integer> curItem = new MutableLiveData<>(0);
    public final LiveData<String> curCity = new MutableLiveData<>();
    public final LiveData<List<String>> cityList = new MutableLiveData<>();
    public final LiveData<Integer> weatherCode = new MutableLiveData<>(0);


}
