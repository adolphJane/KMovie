package com.magicalrice.adolph.kmovie.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Adolph on 2018/5/8.
 */

public abstract class BaseDialogFragment<DB extends ViewDataBinding> extends DialogFragment {

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
