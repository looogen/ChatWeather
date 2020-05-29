package com.llg.chatweather.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.llg.chatweather.BR;
import com.llg.chatweather.R;
import com.llg.chatweather.base.BaseFragment;
import com.llg.chatweather.databinding.FragmentWeatherBinding;
import com.llg.chatweather.viewmodel.WeatherViewModel;

/**
 * create by loogen on 2019-4-29
 */
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding,WeatherViewModel> {
    private static final String TAG = "WeatherFragment";
    private static final String KEY_LOCATION = "location";

    public static WeatherFragment newInstance(String location) {
        Bundle args = new Bundle();
        args.putString(KEY_LOCATION,location);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    protected int getVariableId() {
        return BR.vm;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setEvent(new EventHandler());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " );
//        if (getArguments() != null){
//            mViewModel.requestData(getArguments().getString(KEY_LOCATION));
//        }
    }

    public class EventHandler implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (getArguments() != null){
                mViewModel.requestData(getArguments().getString(KEY_LOCATION));
            }
        }
    }
}
