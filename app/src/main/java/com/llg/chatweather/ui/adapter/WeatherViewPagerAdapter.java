package com.llg.chatweather.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * create by loogen on 2019-4-29
 */
public class WeatherViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragments;


    public WeatherViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm,FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
