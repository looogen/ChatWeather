package com.llg.chatweather.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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

    private boolean isLazyLoad; //是否已经懒加载
    private boolean isReplaceFragment;
    private boolean isFragmentVisible;

    private View mRootView;

    private WeatherViewModel viewModel;


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
        Log.e(TAG, "onCreateView: ");
        if (mRootView == null) {
            mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_city,container,false);
            mRootView = mBinding.getRoot();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        viewModel = new WeatherViewModel(this);
        mBinding.setWeatherviewmodel(viewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
        if (isReplaceFragment) {
            if (isFragmentVisible) {
                initLazyLoad();
            }
        }else {
            initLazyLoad();
        }
    }

    private void initLazyLoad() {
        String location = getArguments().getString(KEY_LOCATION);
        viewModel.requestData(location);
        isLazyLoad = true;
        updateParentUI();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG, "setUserVisibleHint: ");
        super.setUserVisibleHint(isVisibleToUser);
        this.isReplaceFragment = true;
        this.isFragmentVisible = isVisibleToUser;
        if (isVisibleToUser && mRootView != null) {
            if (!isLazyLoad) {
                initLazyLoad();
            }else {
                // 从不可见到可见
                onRestart();
                updateParentUI();
            }
        }
    }

    //更新父级的UI
    private void updateParentUI() {
        MainFragment fragment = (MainFragment) getParentFragment();
        fragment.setViewModel(viewModel);
    }

    private void onRestart() {

    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public boolean immersionBarEnabled() {
        return false;
    }
}
