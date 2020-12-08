package com.llg.chatweather.ui;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.gyf.immersionbar.ImmersionBar;
import com.llg.chatweather.BR;
import com.llg.chatweather.R;
import com.llg.chatweather.base.BaseActivity;
import com.llg.chatweather.base.DataBindingConfig;
import com.llg.chatweather.databinding.ActivityEditCityBinding;
import com.llg.chatweather.viewmodel.ToolbarViewModel;

public class EditCityActivity extends BaseActivity<ActivityEditCityBinding> implements View.OnClickListener {


    private ToolbarViewModel mToolbarViewModel;

    @Override
    protected void initViewModel() {
        mToolbarViewModel = getActivityViewModel(ToolbarViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_edit_city)
                .addBindingParam(BR.click, this)
                .addBindingParam(BR.toolbarVm, mToolbarViewModel);
    }

    @Override
    protected void init() {
        //status bar
        ImmersionBar.with(this)
                .transparentBar()
                .autoDarkModeEnable(true)
                .titleBar(mViewDataBinding.toolbarLayout.toolbar)
                .init();

        //toolbar
        mToolbarViewModel.rightDrawable.set(ContextCompat.getDrawable(this, R.drawable.ic_search));
        mToolbarViewModel.leftDrawable.set(ContextCompat.getDrawable(this, R.drawable.ic_back));
        mToolbarViewModel.bgDrawable.set(ContextCompat.getDrawable(this, R.color.sun));
        mToolbarViewModel.title.set("添加城市");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_left:
                onBackPressed();

                break;
            case R.id.toolbar_right:

                break;
            default:
                break;

        }
    }
}