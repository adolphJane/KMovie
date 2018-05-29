package com.magicalrice.adolph.kmovie.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.magicalrice.adolph.kmovie.utils.StatusBarUtil;
import com.sw.debug.view.modules.TimerModule;

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
        AppManager.getInstance().addActivity(this);
        TimerModule.Companion.getInstance().begin(getApplicationContext());
        binding = DataBindingUtil.setContentView(this,getLayoutRes());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            TimerModule.Companion.getInstance().end(getApplicationContext());
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
