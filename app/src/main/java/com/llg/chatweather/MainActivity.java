package com.llg.chatweather;

import android.os.Bundle;
import android.view.WindowManager;

import com.llg.chatweather.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //设置透明状态栏
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,MainFragment.newInstance(),"main")
                .commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



}
