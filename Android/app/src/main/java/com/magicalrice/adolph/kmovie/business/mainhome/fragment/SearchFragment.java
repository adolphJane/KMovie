package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDialogFragment;
import com.magicalrice.adolph.kmovie.databinding.FragmentMovieSearchBinding;

/**
 * Created by Adolph on 2018/5/8.
 */

public class SearchFragment extends BaseDialogFragment<FragmentMovieSearchBinding> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getDialog().getWindow().setLayout(metrics.widthPixels,metrics.heightPixels);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_movie_search;
    }

    @Override
    public void createView(View view) {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    public static void showFragment(FragmentActivity activity) {
        if (activity != null) {
            SearchFragment fragment = (SearchFragment) activity.getSupportFragmentManager().findFragmentByTag("search");
            if (fragment != null) {
                fragment.dismissAllowingStateLoss();
                activity.getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
            fragment = new SearchFragment();
            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);
            if (!activity.isFinishing()) {
                activity.getSupportFragmentManager().beginTransaction().add(fragment,"search").commitAllowingStateLoss();
            }
        }
    }
}
