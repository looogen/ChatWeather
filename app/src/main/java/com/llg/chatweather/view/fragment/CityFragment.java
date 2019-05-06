package com.llg.chatweather.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llg.chatweather.R;
import com.llg.chatweather.databinding.FragmentCityBinding;
import com.llg.chatweather.viewmodel.WeatherViewModel;

/**
 * create by loogen on 2019-4-29
 */
public class CityFragment extends BaseFragment {

    private FragmentCityBinding mBinding;
    private boolean isViewCreated = false;

    public static CityFragment newInstance() {
        Bundle args = new Bundle();
        CityFragment fragment = new CityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_city,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated){
            WeatherViewModel viewModel = new WeatherViewModel(this);
            mBinding.setWeatherviewmodel(viewModel);
            viewModel.requestData();
        }
    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public boolean immersionBarEnabled() {
        return false;
    }
}
