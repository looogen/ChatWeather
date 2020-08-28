package com.llg.chatweather.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
        mViewModel.cityList.observe(this, cityList -> {
            if (cityList != null) {
                initViewPager(cityList);
                initIndicatorPoint(cityList.size());
            }
        });


    }

    private void initViewPager(List<String> cityList) {
        List<Fragment> fragments = new ArrayList<>();
        for (String city : cityList) {
            fragments.add(WeatherFragment.newInstance(city));
        }
        WeatherViewPagerAdapter adapter = new WeatherViewPagerAdapter(getSupportFragmentManager(), fragments);
        mBinding.viewPager.setAdapter(adapter);
        mBinding.viewPager.setOffscreenPageLimit(3);
        mViewModel.curCity.setValue(cityList.get(0));
    }

    private int num = 0;

    private void initIndicatorPoint(int size) {
        for (int i = 0; i < size; i++) {
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.point_selector);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                layoutParams.leftMargin = 16;
            } else {
                layoutParams.leftMargin = 0;
            }
            mBinding.indicatorLayout.addView(point, layoutParams);
        }
        mBinding.indicatorLayout.getChildAt(num).setSelected(true);
    }

    public void setWeatherCode(String code) {
        Log.e(TAG, "setWeatherCode: " + code);
        mViewModel.weatherCode.setValue(code);
//        getLifecycle().addObserver(mBinding.bgAnim);
    }

    public class EventHandler implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.e(TAG, "onPageScrolled: ");
//            startShowIndicatorAnimator();
//            if (canSend) {
//                canSend = false;
//                mHandler.postDelayed(hideIndicator, 1000);
//            }
        }

        @Override
        public void onPageSelected(int position) {
//            Log.e(TAG, "onPageSelected: " + position);
            mViewModel.curCity.setValue(mViewModel.cityList.getValue().get(position));
            mBinding.indicatorLayout.getChildAt(num).setSelected(false);
            mBinding.indicatorLayout.getChildAt(position).setSelected(true);
            num = position;
//            mHandler.postDelayed(hideIndicator,200);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private boolean canSend = true;
    private Handler mHandler = new Handler();
    private Runnable hideIndicator = new Runnable() {
        @Override
        public void run() {
            canSend = true;
            startHideIndicatorAnimator();
        }
    };

    private void startHideIndicatorAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.indicatorLayout, "alpha", 1, 0.9f, 0.7f, 0.5f, 0.2f, 0);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                mBinding.indicatorLayout.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    private void startShowIndicatorAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBinding.indicatorLayout, "alpha", 0, 0.2f, 0.5f, 0.7f, 0.9f, 1);
        animator.setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                mBinding.indicatorLayout.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

}
