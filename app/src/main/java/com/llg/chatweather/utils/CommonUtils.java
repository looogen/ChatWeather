package com.llg.chatweather.utils;

import android.os.Build;
import android.os.LocaleList;

import java.util.Collection;
import java.util.Locale;

/**
 * create by loogen on 2019-5-21
 */
public class CommonUtils {

    public static String getSystemLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        if ("zh".equals(locale.getLanguage())) {
            if ("CN".equals(locale.getCountry())) {
                return "zh-Hans";//中文简体
            } else {
                return "zh-Hant";//中文繁体
            }
        }
        return locale.getLanguage();
    }

    public static boolean isCollNotEmpty(Collection coll) {
        if (coll == null) {
            return false;
        }
        return coll.size() > 0;
    }


}
