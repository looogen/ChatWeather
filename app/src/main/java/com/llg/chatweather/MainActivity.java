package com.llg.chatweather;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpToolbar();
    }

    private void setUpToolbar() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
