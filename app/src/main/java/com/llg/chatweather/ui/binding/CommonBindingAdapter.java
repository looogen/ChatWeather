package com.llg.chatweather.ui.binding;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * create by loogen on 2020-11-16
 */
public class CommonBindingAdapter {
    public static final String TAG = "CommonBindingAdapter";

    @BindingAdapter(value = {"visible"})
    public static void setVisible(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter(value = {"drawable"})
    public static void setDrawable(ImageView imageView, Drawable drawable) {
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter(value = {"toolbarTitle"})
    public static void setTitle(TextView textView, String text) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }


}
