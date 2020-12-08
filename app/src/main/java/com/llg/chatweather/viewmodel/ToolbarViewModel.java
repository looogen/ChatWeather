package com.llg.chatweather.viewmodel;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;

import com.llg.chatweather.base.BaseViewModel;

/**
 * create by loogen on 2020-11-16
 */
public class ToolbarViewModel extends BaseViewModel {
    public static final String TAG = "ToolbarViewModel";
    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<Drawable> leftDrawable = new ObservableField<>();
    public final ObservableField<Drawable> rightDrawable = new ObservableField<>();
    public final ObservableField<Drawable> bgDrawable = new ObservableField<>();

    {
        Integer.MAX_VALUE

    }

//    public final ObservableBoolean leftVisible = new ObservableBoolean(false);
//    public final ObservableBoolean rightVisible = new ObservableBoolean(false);
}
