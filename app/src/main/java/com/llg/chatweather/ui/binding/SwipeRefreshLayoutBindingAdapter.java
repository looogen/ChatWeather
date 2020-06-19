package com.llg.chatweather.ui.binding;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * create by loogen on 2020-5-29
 */
public class SwipeRefreshLayoutBindingAdapter {

    @BindingAdapter(value = {"refreshListener"}, requireAll = false)
    public static void addOnRefreshListener(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener listener) {
        if (null != listener) {
            refreshLayout.setOnRefreshListener(listener);
        }
    }

    @BindingAdapter(value = {"refreshing"}, requireAll = false)
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
    }
}
