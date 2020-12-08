package com.llg.chatweather.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.llg.chatweather.BR;
import com.llg.chatweather.R;
import com.llg.chatweather.base.BaseActivity;
import com.llg.chatweather.base.DataBindingConfig;
import com.llg.chatweather.databinding.ActivityMainBinding;
import com.llg.chatweather.ui.adapter.WeatherViewPagerAdapter;
import com.llg.chatweather.ui.fragment.WeatherFragment;
import com.llg.chatweather.utils.CommonUtils;
import com.llg.chatweather.viewmodel.MainViewModel;
import com.llg.chatweather.viewmodel.ToolbarViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private MainViewModel mViewModel;
    private ToolbarViewModel mToolbarViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityViewModel(MainViewModel.class);
        mToolbarViewModel = getActivityViewModel(ToolbarViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm, mViewModel)
                .addBindingParam(BR.event, new EventHandler())
                .addBindingParam(BR.click, this)
                .addBindingParam(BR.toolbarVm, mToolbarViewModel);
    }

    @Override
    protected void init() {
        //status bar
        ImmersionBar.with(this)
                .transparentBar()
                .titleBar(mViewDataBinding.toolbarLayout.toolbar)
                .init();

        //toolbar
        mToolbarViewModel.rightDrawable.set(ContextCompat.getDrawable(this, R.drawable.ic_city_24));

        mViewModel.cityList.observe(this, cityList -> {
            if (CommonUtils.isCollNotEmpty(cityList)) {
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
        mViewDataBinding.viewPager.setAdapter(adapter);
        mViewDataBinding.viewPager.setOffscreenPageLimit(3);
        mToolbarViewModel.title.set(cityList.get(0));
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
            mViewDataBinding.indicatorLayout.addView(point, layoutParams);
        }
        mViewDataBinding.indicatorLayout.getChildAt(num).setSelected(true);
    }

    public void setWeatherCode(String code) {
        Log.e(TAG, "setWeatherCode: " + code);
        mViewModel.weatherCode.setValue(code);
//        getLifecycle().addObserver(mBinding.bgAnim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //编辑地区页面
            case R.id.toolbar_right:
                Intent intent = new Intent(MainActivity.this, EditCityActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
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
            mToolbarViewModel.title.set(mViewModel.cityList.getValue().get(position));
            mViewDataBinding.indicatorLayout.getChildAt(num).setSelected(false);
            mViewDataBinding.indicatorLayout.getChildAt(position).setSelected(true);
            num = position;
//            mHandler.postDelayed(hideIndicator,200);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    // TODO 指示器动画效果
//    private boolean canSend = true;
//    private Handler mHandler = new Handler();
//    private Runnable hideIndicator = new Runnable() {
//        @Override
//        public void run() {
//            canSend = true;
//            startHideIndicatorAnimator();
//        }
//    };
//
//    private void startHideIndicatorAnimator() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewDataBinding.indicatorLayout, "alpha", 1, 0.9f, 0.7f, 0.5f, 0.2f, 0);
//        animator.setDuration(500);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation, boolean isReverse) {
//                mViewDataBinding.indicatorLayout.setVisibility(View.INVISIBLE);
//            }
//        });
//        animator.start();
//    }
//
//    private void startShowIndicatorAnimator() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewDataBinding.indicatorLayout, "alpha", 0, 0.2f, 0.5f, 0.7f, 0.9f, 1);
//        animator.setDuration(500);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation, boolean isReverse) {
//                mViewDataBinding.indicatorLayout.setVisibility(View.VISIBLE);
//            }
//        });
//        animator.start();
//    }

}
