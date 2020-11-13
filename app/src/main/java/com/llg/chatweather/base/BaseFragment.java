package com.llg.chatweather.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.llg.chatweather.App;

public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {

    protected V mViewDataBinding;
    protected AppCompatActivity mActivity;

    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider.Factory mFactory;


    protected abstract void initViewMode();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewMode();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        mViewDataBinding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        mViewDataBinding.setLifecycleOwner(this);
        if (dataBindingConfig.getVmVariableId() != 0 && dataBindingConfig.getStateViewModel() != null) {
            mViewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        }

        //other Params
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            mViewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        return mViewDataBinding.getRoot();
    }

    //viewmodel 生命周期范围在Fragment内
    protected <T extends ViewModel> T getFragmentViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    //viewmodel 生命周期范围在Activity内
    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }

    //viewmodel 生命周期范围在Application内
    protected ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider((App) mActivity.getApplicationContext(),
                getAppFactory(mActivity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        checkActivity(this);
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private void checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
    }
}
