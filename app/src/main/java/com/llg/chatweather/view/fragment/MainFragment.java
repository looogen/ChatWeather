package com.llg.chatweather.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.llg.chatweather.R;
import com.llg.chatweather.databinding.FragmentMainBinding;
import com.llg.chatweather.view.MainActivity;
import com.llg.chatweather.view.adapter.CityViewPagerAdapter;
import com.llg.chatweather.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * create by loogen on 2019-3-29
 */
public class MainFragment extends BaseFragment {

    private FragmentMainBinding mBindingView;

    private MainActivity mActivity;

    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private CityViewPagerAdapter pagerAdapter;

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
        return mBindingView.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = mBindingView.getRoot().findViewById(R.id.toolbar);
        mViewPager = mBindingView.getRoot().findViewById(R.id.view_pager);
        ImmersionBar.setTitleBar(mActivity, mToolbar);
      //  setUpBar();
    }

    private void setUpBar() {
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mActivity.setSupportActionBar(mToolbar);
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
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(CityFragment.newInstance("zhuhai"));
        fragments.add(CityFragment.newInstance("beijing"));
        fragments.add(CityFragment.newInstance("guangzhou"));
        pagerAdapter = new CityViewPagerAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initImmersionBar() {
                ImmersionBar.with(this)
                .transparentBar().autoDarkModeEnable(true)
                .init();
    }
}
