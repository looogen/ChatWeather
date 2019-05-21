package com.llg.chatweather.utils;

import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

/**
 * create by loogen on 2019-5-21
 */
public class CommonUtils {

    public static String getSystemLanguage(){
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if (locale.getLanguage().equals("zh")) {
            if (locale.getCountry().equals("CN")) {
                return "zh-Hans";//中文简体
            } else {
                return "zh-Hant";//中文繁体
            }
        }
        return locale.getLanguage();
    }


}
