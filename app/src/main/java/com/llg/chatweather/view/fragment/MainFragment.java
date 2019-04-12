package com.llg.chatweather.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.llg.chatweather.view.MainActivity;
import com.llg.chatweather.R;
import com.llg.chatweather.databinding.FragmentMainBinding;
import com.llg.chatweather.utils.StatusBarUtil;
import com.llg.chatweather.viewmodel.WeatherViewModel;

/**
 * create by loogen on 2019-3-29
 */
public class MainFragment extends Fragment {

    private FragmentMainBinding mBindingView;

    private MainActivity mActivity;

    private Toolbar mToolbar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBindingView = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = mBindingView.getRoot();
        mToolbar = view.findViewById(R.id.toolbar);
        setUpBar();
        return view;
    }

    /**
     * 设置状态栏、工具栏、导航栏
     */
    private void setUpBar() {
        StatusBarUtil.setTranslucent(mActivity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mActivity = (MainActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WeatherViewModel weatherViewModel = new WeatherViewModel(mActivity);
        mBindingView.setWeatherviewmodel(weatherViewModel);
    }

}
