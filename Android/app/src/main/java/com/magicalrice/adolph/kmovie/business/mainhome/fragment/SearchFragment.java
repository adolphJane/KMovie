package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDialogFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.databinding.FragmentMovieSearchBinding;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.SearchViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.SearchPagerAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/8.
 */

public class SearchFragment extends BaseDialogFragment<FragmentMovieSearchBinding> implements TextWatcher {
    @Inject
    MainViewModuleFactory factory;
    private SearchViewModule viewModule;
    private int searchStatus = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            viewModule = ViewModelProviders.of(getActivity(), factory).get(SearchViewModule.class);
            viewModule.getHistoryList();
            ((MainHomeActivity) getActivity()).changeTabLayoutShow(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getDialog().getWindow().setLayout(metrics.widthPixels, metrics.heightPixels);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_movie_search;
    }

    @Override
    public void createView(View view) {
        binding.setHasHistory(false);
        binding.setSearchStatus(searchStatus);
        binding.cancel.setOnClickListener(v -> dismissAllowingStateLoss());
        binding.deleteSearch.setOnClickListener(v -> {
            binding.inputSearch.setText("");
        });
        binding.inputSearch.addTextChangedListener(this);
        initSearchResultsView();
        initData();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainHomeActivity) getActivity()).changeTabLayoutShow(true);
    }

    private void initData() {
        viewModule.historyData.observe(this, strings -> {
            if (strings.size() > 0) {
                binding.setHasHistory(true);
            } else {
                binding.setHasHistory(false);
            }
            updateHistory(strings);
        });

        binding.inputSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                changeStatus(v.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void updateHistory(List<String> historyList) {
        binding.rlSearchHistory.removeAllViews();
        int preId = 0, textViewWidth = 0, lines = 1;
        int historyTag = 10001;
        int width = (int) (ScreenUtils.getScreenWidth() - ScreenUtils.dp2px(getContext(), 40));
        for (int i = 0; i < 5 && i < historyList.size(); i++) {
            String name = historyList.get(i);
            if (TextUtils.isEmpty(name) || lines > 3) {
                break;
            }
            TextView textView = new TextView(getContext());
            textView.setBackgroundResource(R.drawable.shape_color_black4_20dp_corners);
            textView.setPadding((int) ScreenUtils.dp2px(getContext(), 10), (int) ScreenUtils.dp2px(getContext(), 3), (int) ScreenUtils.dp2px(getContext(), 10), (int) ScreenUtils.dp2px(getContext(), 3));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white_4));
            textView.setText(name);
            RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            textView.measure(spec, spec);
            if (preId > 0) {
                //测量textview
                if (width >= textViewWidth + textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(getContext(), 15)) {
                    tvParams.addRule(RelativeLayout.RIGHT_OF, preId);
                    tvParams.setMarginStart((int) ScreenUtils.dp2px(getContext(), 15));
                } else {
                    lines++;
                    textViewWidth = 0;
                }
            }
            textViewWidth += textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(getContext(), 15);
            tvParams.setMargins(0, (int) ((textView.getMeasuredHeight() + ScreenUtils.dp2px(getContext(), 5)) * lines), 0, 0);
            textView.setId(historyTag + i);
            preId = textView.getId();
            ImageView imageView = new ImageView(getContext());
            RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setImageResource(R.drawable.ico_history_delete);
            textView.setOnClickListener(v -> {
                changeStatus(name);
            });
            imageView.setOnClickListener(v -> {
                viewModule.updateHistory(name,false);
            });
            imgParams.addRule(RelativeLayout.ALIGN_RIGHT, preId);
            imgParams.addRule(RelativeLayout.ALIGN_TOP, preId);
            imgParams.setMargins(0, -(int) ScreenUtils.dp2px(getContext(), 4), -(int) ScreenUtils.dp2px(getContext(), 4), 0);
            binding.rlSearchHistory.addView(textView, tvParams);
            binding.rlSearchHistory.addView(imageView, imgParams);
        }
    }

    private void initSearchResultsView() {
        SearchPagerAdapter adapter = new SearchPagerAdapter(getChildFragmentManager());
        binding.searchViewpager.setAdapter(adapter);
        binding.searchTab.setupWithViewPager(binding.searchViewpager);
        binding.searchViewpager.setCurrentItem(0);
    }

    private void changeStatus(String query) {
        if (searchStatus == 1) {
            searchStatus = 2;
            binding.setSearchStatus(searchStatus);
        }
        viewModule.updateQuery(query);
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
                activity.getSupportFragmentManager().beginTransaction().add(fragment, "search").commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() == 0) {
            searchStatus = 1;
            binding.setSearchStatus(searchStatus);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
