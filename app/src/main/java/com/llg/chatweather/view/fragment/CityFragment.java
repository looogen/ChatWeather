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

    private static final String KEY_LOCATION = "location";

    private FragmentCityBinding mBinding;
    private boolean isViewCreated = false;

    private boolean isLazyLoad = false; //是否已经懒加载

    public static CityFragment newInstance(String location) {
        Bundle args = new Bundle();
        args.putString(KEY_LOCATION,location);
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
        if (isViewCreated && !isLazyLoad){
            WeatherViewModel viewModel = new WeatherViewModel(this);
            mBinding.setWeatherviewmodel(viewModel);
            String location = getArguments().getString(KEY_LOCATION);
            viewModel.requestData(location);
            isLazyLoad = true;
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
