package com.llg.chatweather.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.llg.chatweather.BR;
import com.llg.chatweather.R;
import com.llg.chatweather.base.BaseActivity;
import com.llg.chatweather.databinding.ActivityMainBinding;
import com.llg.chatweather.ui.adapter.WeatherViewPagerAdapter;
import com.llg.chatweather.ui.fragment.WeatherFragment;
import com.llg.chatweather.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private static final String TAG = "MainActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getVariableId() {
        return BR.vm;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .transparentBar()
                .titleBar(mBinding.toolbar)
                .init();
        mBinding.setEvent(new EventHandler());

        // TODO: 2020-6-1 读取添加的城市
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(WeatherFragment.newInstance("beijing"));
        fragments.add(WeatherFragment.newInstance("zhuhai"));
        fragments.add(WeatherFragment.newInstance("kunming"));
        WeatherViewPagerAdapter adapter = new WeatherViewPagerAdapter(getSupportFragmentManager(),fragments);
        mBinding.viewPager.setAdapter(adapter);
    }

    public class EventHandler implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.e(TAG, "onPageSelected: "+position );
            mViewModel.curItem.setValue(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
