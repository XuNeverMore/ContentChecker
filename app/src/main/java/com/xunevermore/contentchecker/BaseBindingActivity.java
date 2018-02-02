package com.xunevermore.contentchecker;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nevermore.oceans.base.BaseActivity;

/**
 * Created by Administrator on 2018/1/25 0025.
 */

public abstract class BaseBindingActivity<B extends ViewDataBinding> extends BaseActivity implements DataBindingProvider{

    public B mDataBing;

    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        mDataBing = DataBindingUtil.setContentView(this,getLayoutId());
        initView();

    }

}
