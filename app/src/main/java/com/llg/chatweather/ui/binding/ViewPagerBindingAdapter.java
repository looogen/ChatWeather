package com.llg.chatweather.ui.binding;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * create by loogen on 2020-5-28
 */
public class ViewPagerBindingAdapter {

    @BindingAdapter(value = {"pagerChangeListener"},requireAll = false)
    public static void addViewPagerChangeListener(ViewPager viewPager, ViewPager.OnPageChangeListener listener){
        if (listener != null){
            viewPager.addOnPageChangeListener(listener);
        }
    }

    @BindingAdapter(value = {"curItem"},requireAll = false)
    public static void setCurrentItem(ViewPager viewPager,int position){
        if (viewPager.getAdapter() != null){
            if (viewPager.getCurrentItem() != position) {
                viewPager.setCurrentItem(position);
            }
        }
    }

}
