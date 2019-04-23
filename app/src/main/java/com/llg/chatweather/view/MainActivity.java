package com.llg.chatweather.view;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.llg.chatweather.R;
import com.llg.chatweather.view.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,MainFragment.newInstance(),"main")
                .commit();

        mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // TODO Auto-generated method stub
                super.onDrawerSlide(drawerView, slideOffset);
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        });
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



}
