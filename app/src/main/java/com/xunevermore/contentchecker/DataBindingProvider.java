package com.xunevermore.contentchecker;

import android.support.annotation.LayoutRes;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

public interface DataBindingProvider {

    public abstract @LayoutRes
    int getLayoutId();

    public abstract void initView();
}
