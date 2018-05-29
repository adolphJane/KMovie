package com.magicalrice.adolph.kmovie.business.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magicalrice.adolph.kmovie.R;

/**
 * Created by Adolph on 2018/5/23.
 */

public class TextDisplayBoxFragment extends DialogFragment {
    private String title;
    private String overview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        overview = getArguments().getString("overview");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_display,container,false);
        ((TextView)view.findViewById(R.id.tv_header)).setText(title);
        ((TextView)view.findViewById(R.id.content)).setText(overview);
        return view;
    }

    public static void showFragment(FragmentActivity activity,String title,String overview) {
        if (activity != null) {
            TextDisplayBoxFragment fragment = (TextDisplayBoxFragment) activity.getSupportFragmentManager().findFragmentByTag("text_box");
            if (fragment != null) {
                fragment.dismissAllowingStateLoss();
                activity.getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
            fragment = new TextDisplayBoxFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",title);
            bundle.putString("overview",overview);
            fragment.setArguments(bundle);
            if (!activity.isFinishing()) {
                activity.getSupportFragmentManager().beginTransaction().add(fragment, "text_box").commitAllowingStateLoss();
            }
        }
    }
}
