package com.magicalrice.adolph.kmovie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerFragment;

/**
 * Created by Adolph on 2018/5/10.
 */

public abstract class BaseDaggerFragment<DB extends ViewDataBinding> extends DaggerFragment {
    public DB binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(),container,false);
        binding = DataBindingUtil.bind(view);
        createView(view);
        return view;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void createView(View view);
}
