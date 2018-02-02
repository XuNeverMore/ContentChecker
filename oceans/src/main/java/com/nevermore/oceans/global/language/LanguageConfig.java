package com.nevermore.oceans.global.language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import com.nevermore.oceans.ob.Dispatcher;
import com.nevermore.oceans.ob.SuperObservableManager;

import java.util.Locale;

/**
 * Created by Administrator on 2018/1/25 0025.
 */

public class LanguageConfig {

    //当前所设置的语言
    public static final String LOCAL_LANGUAGE = "LOCAL_LANGUAGE";

    public static final int LANGUAGE_CHINESE = 0;
    public static final int LANGUAGE_ENGLISH = 1;
    public static final int LANGUAGE_JAPAN = 2;


    /**
     * 设置语言
     *
     * @param context
     * @param languageType  {@link #LANGUAGE_CHINESE}
     */
    public static void setLanguage(Context context, final int languageType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCAL_LANGUAGE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(LOCAL_LANGUAGE, languageType).apply();

        Locale locale = null;
        switch (languageType) {
            case LANGUAGE_CHINESE:
                locale = Locale.CHINA;
                break;
            case LANGUAGE_ENGLISH:
                locale = Locale.ENGLISH;
                break;
            case LANGUAGE_JAPAN:
                locale = Locale.JAPAN;
                break;
            default:
                locale = Locale.ENGLISH;
                break;
        }

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

        SuperObservableManager.notify(OnLanguageChangeListener.class, new Dispatcher<OnLanguageChangeListener>() {
            @Override
            public void call(OnLanguageChangeListener onLanguageChangeListener) {
                onLanguageChangeListener.onLanguageChanged(languageType);
            }
        });

    }

    private static final String TAG = "LanguageConfig";

    /**
     * 获取当前所使用的语言
     *
     * @param context Context
     * @see #setLanguage(Context, int)
     */
    public static int getLanguage(Context context) {
        Configuration configuration = context.getResources().getConfiguration();

        Log.i(TAG, "getLanguage: "+configuration.locale.getCountry());
        int defaultLanguage = LANGUAGE_ENGLISH;
        if (Locale.CHINA.equals(configuration.locale)) {
            defaultLanguage = LANGUAGE_CHINESE;
        } else if (Locale.JAPAN.equals(configuration.locale)) {
            defaultLanguage = LANGUAGE_JAPAN;
        }else if(Locale.ENGLISH.equals(configuration.locale)){
            defaultLanguage = LANGUAGE_ENGLISH;
        }
        return context.getSharedPreferences(LOCAL_LANGUAGE, Context.MODE_PRIVATE).getInt(LOCAL_LANGUAGE, defaultLanguage);
    }

}
