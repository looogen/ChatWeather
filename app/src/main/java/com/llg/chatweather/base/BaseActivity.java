package com.llg.chatweather.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * create by loogen on 2020-5-26
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected V mBinding;
    protected VM mViewModel;

    private ViewModelProvider mActivityProvider;

    protected abstract @LayoutRes int getLayoutId();
    protected abstract int getVariableId();

    protected VM initViewModel() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initViewDataBinding();
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        mViewModel = initViewModel();
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) getActivityViewModel(modelClass);
        }
        mBinding.setVariable(getVariableId(), mViewModel);
        mBinding.setLifecycleOwner(this);
    }

    protected <T extends BaseViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }
}
