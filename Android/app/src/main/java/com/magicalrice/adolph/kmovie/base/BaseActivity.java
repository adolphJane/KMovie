package com.magicalrice.adolph.kmovie.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Adolph on 2018/2/26.
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends DaggerAppCompatActivity{

    public DB binding;

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        binding = DataBindingUtil.setContentView(this,getLayoutRes());
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
