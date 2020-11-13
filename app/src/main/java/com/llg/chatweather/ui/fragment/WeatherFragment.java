package com.llg.chatweather.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.llg.chatweather.BR;
import com.llg.chatweather.R;
import com.llg.chatweather.base.BaseFragment;
import com.llg.chatweather.base.DataBindingConfig;
import com.llg.chatweather.databinding.FragmentWeatherBinding;
import com.llg.chatweather.ui.MainActivity;
import com.llg.chatweather.viewmodel.WeatherViewModel;

/**
 * create by loogen on 2019-4-29
 */
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private static final String TAG = "WeatherFragment";
    private static final String KEY_LOCATION = "location";
    private boolean isLoading = false;
    private WeatherViewModel mViewModel;

    public static WeatherFragment newInstance(String location) {
        Bundle args = new Bundle();
        args.putString(KEY_LOCATION, location);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
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
        mViewModel.weatherCode.observe(getViewLifecycleOwner(), code -> ((MainActivity) mActivity).setWeatherCode(code));
        mViewModel.errorMsg.observe(getViewLifecycleOwner(), this::showMessage);
        Log.i(TAG, "onViewCreated: " + getCity());
    }

    private void showMessage(String msg) {
        Snackbar.make(mViewDataBinding.refreshLayout, msg, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoading) {
            if (getArguments() != null) {
                refreshData();
            } else {
                Log.e(TAG, "loadData: getArguments == null");
            }
            isLoading = true;
        } else {
            if (mViewModel.weatherCode.getValue() != null) {
                ((MainActivity) mActivity).setWeatherCode(mViewModel.weatherCode.getValue());
            } else {
                ((MainActivity) mActivity).setWeatherCode("");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoading = false;
    }

    @Override
    protected void initViewMode() {
        mViewModel = getFragmentViewModel(WeatherViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_weather, BR.vm, mViewModel)
                .addBindingParam(BR.event, new EventHandler());
    }

    public class EventHandler implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            refreshData();
        }
    }

    private void refreshData() {
        if (getArguments() != null) {
            mViewModel.refreshData(getArguments().getString(KEY_LOCATION)).observe(this, resource -> {
                switch (resource.getStatus()) {
                    case LOADING:
                        mViewModel.refreshing.setValue(true);
                        break;
                    case SUCCESS:
                        mViewModel.refreshing.setValue(false);
                        if (resource.getData() != null) {
                            mViewModel.showNowWeatherData(resource.getData());
                        }
                        break;
                    case ERROR:
                        mViewModel.refreshing.setValue(false);
                        break;
                }
            });
        } else {
            Log.e(TAG, "loadData: getArguments == null");
        }
    }

}
