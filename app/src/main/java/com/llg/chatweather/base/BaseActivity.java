package com.llg.chatweather.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.llg.chatweather.App;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private ViewModelProvider mActivityProvider;
    private ViewModelProvider.Factory mFactory;

    protected T mViewDataBinding;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    /**
     * init data and view
     */
    protected abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        mViewDataBinding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            mViewDataBinding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        init();
    }


    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    protected ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider((App) this.getApplicationContext(),
                getAppFactory(this));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
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
}
