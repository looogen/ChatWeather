package com.llg.chatweather.ui.fragment;

import android.content.Context;
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
import com.llg.chatweather.ui.MainActivity;
import com.llg.chatweather.viewmodel.WeatherViewModel;

/**
 * create by loogen on 2019-4-29
 */
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding, WeatherViewModel> {
    private static final String TAG = "WeatherFragment";
    private static final String KEY_LOCATION = "location";
    private boolean isLoading = false;

    public static WeatherFragment newInstance(String location) {
        Bundle args = new Bundle();
        args.putString(KEY_LOCATION, location);
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

    private String getCity() {
        if (getArguments() != null) {
            return getArguments().getString(KEY_LOCATION);
        }
        return "";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setEvent(new EventHandler());
        mViewModel.weatherCode.observe(getViewLifecycleOwner(), code -> ((MainActivity) mActivity).setWeatherCode(code));
        Log.e(TAG, "onViewCreated: " + getCity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " + getCity());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: " + getCity());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + getCity());
        if (!isLoading) {
            if (getArguments() != null) {
                mViewModel.getData(getArguments().getString(KEY_LOCATION)).observe(getViewLifecycleOwner(), cityWeathers -> {
                    if (cityWeathers.size() >= 1) {
                        mViewModel.showNowWeatherData(cityWeathers.get(0));
                    } else {
                        refreshData();
                    }
                });
            } else {
                Log.e(TAG, "loadData: getArguments == null");
            }
            isLoading = true;
        } else {
            if (mViewModel.weatherCode.getValue() != null) {
                ((MainActivity) mActivity).setWeatherCode(mViewModel.weatherCode.getValue());
            } else {
                ((MainActivity) mActivity).setWeatherCode(-1);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + getCity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoading = false;
        Log.e(TAG, "onDestroyView: " + getCity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " + getCity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " + getCity());
    }

    public class EventHandler implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            refreshData();
        }
    }

    private void refreshData() {
        if (getArguments() != null) {
            mViewModel.refreshing.setValue(true);
            mViewModel.refreshData(getArguments().getString(KEY_LOCATION)).observe(getViewLifecycleOwner(),
                    cityWeather -> {
                        mViewModel.showNowWeatherData(cityWeather);
                        mViewModel.refreshing.setValue(false);
                    });
        } else {
            Log.e(TAG, "loadData: getArguments == null");
        }
    }

}
