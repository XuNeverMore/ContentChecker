package com.nevermore.oceans.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.nevermore.oceans.ob.SuperObservableManager;
import com.nevermore.oceans.global.language.OnLanguageChangeListener;

/**
 * Created by Administrator on 2018/1/25 0025.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useMultiLanguage()) {
            SuperObservableManager.registerObserver(OnLanguageChangeListener.class, languageChangeListener);
        }
    }


    protected void toast(String message){
        ToastUtils.showShort(message);
    }

    private OnLanguageChangeListener languageChangeListener = new OnLanguageChangeListener() {
        @Override
        public void onLanguageChanged(int language) {
            recreate();
        }
    };

    /**
     * 是否有多语言引用
     *
     * @return
     */
    protected boolean useMultiLanguage() {
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useMultiLanguage()) {
            SuperObservableManager.unregisterObserver(OnLanguageChangeListener.class, languageChangeListener);
        }
    }

    protected void goActivity(Class<? extends Activity> clazz){
        startActivity(new Intent(this,clazz));
    }
}
