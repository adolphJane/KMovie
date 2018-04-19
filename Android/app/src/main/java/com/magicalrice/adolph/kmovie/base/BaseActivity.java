package com.magicalrice.adolph.kmovie.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.magicalrice.adolph.kmovie.utils.StatusBarUtil;

import dagger.android.AndroidInjection;
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
        binding = DataBindingUtil.setContentView(this,getLayoutRes());
    }

}
