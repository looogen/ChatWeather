package com.llg.chatweather;

import android.os.Bundle;

import com.llg.chatweather.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,MainFragment.newInstance(),"main")
                .commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



}
