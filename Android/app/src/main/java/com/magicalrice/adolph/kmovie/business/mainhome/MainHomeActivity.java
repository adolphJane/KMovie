package com.magicalrice.adolph.kmovie.business.mainhome;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.AppManager;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MainHomeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.SearchFragment;
import com.magicalrice.adolph.kmovie.databinding.ActivityMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.StatusBarUtil;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.scroll_tag.onScrollSelectTagListener;

import java.util.List;

import javax.inject.Inject;

public class MainHomeActivity extends BaseActivity<ActivityMainHomeBinding> implements TabLayout.OnTabSelectedListener, MainHomeClickListener, onScrollSelectTagListener {
    private MainHomeViewModule viewModule;
    private long exitTime = 0;
    @Inject
    MainViewModuleFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.translucentStatusBar(this, true);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        binding.setShowTag(true);
        setUpTabLayout();
        setUpToolBar();
        viewModule = new ViewModelProvider(this, factory).get(MainHomeViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main_home;
    }

    private void setUpTabLayout() {
        TabLayout bottomTab = binding.bottomTab;
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_movie), true);
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_tv));
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_me));
        bottomTab.addOnTabSelectedListener(this);
        bottomTab.getTabAt(0).select();
    }

    private void setUpToolBar() {
        binding.setClickListener(this);
        binding.tagGroup.addOnScrollSelectTagListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
        onShowFragment(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
        onShowFragment(tab.getPosition());
    }

    private void onShowFragment(int position) {
        switch (position) {
            case 0:
                binding.appbar.setVisibility(View.VISIBLE);
                changeFragment("main_movie");
                break;
            case 1:
                binding.appbar.setVisibility(View.VISIBLE);
                changeFragment("main_tv");
                break;
            case 2:
                binding.appbar.setVisibility(View.GONE);
                changeFragment("main_me");
                break;
        }
        binding.tagGroup.detachListener();
    }

    private void changeFragment(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tr = manager.beginTransaction();
        Fragment movieFragment = manager.findFragmentByTag("main_movie");
        Fragment tvFragment = manager.findFragmentByTag("main_tv");
        Fragment meFragment = manager.findFragmentByTag("main_me");

        if (movieFragment != null) {
            tr.hide(movieFragment);
        }

        if (tvFragment != null) {
            tr.hide(tvFragment);
        }

        if (meFragment != null) {
            tr.hide(meFragment);
        }

        if (TextUtils.equals(tag, "main_movie")) {
            if (movieFragment == null) {
                movieFragment = new MainHomeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);                //电影type:1
                movieFragment.setArguments(bundle);
                tr.add(R.id.content, movieFragment, "main_movie");
            } else {
                tr.show(movieFragment);
            }
        } else if (TextUtils.equals(tag, "main_tv")) {
            if (tvFragment == null) {
                tvFragment = new MainHomeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);                //电视type:2
                tvFragment.setArguments(bundle);
                tr.add(R.id.content, tvFragment, "main_tv");
            } else {
                tr.show(tvFragment);
            }
        } else if (TextUtils.equals(tag, "main_me")) {
            if (meFragment == null) {
                meFragment = new MeFragment();
                Bundle bundle = new Bundle();
                meFragment.setArguments(bundle);
                tr.add(R.id.content_me, meFragment, "main_me");
            } else {
                tr.show(meFragment);
            }
        }
        tr.commitAllowingStateLoss();
    }

    public void updateTag(ViewPager viewPager, List<String> tagList, int position) {
        if (binding != null) {
            binding.tagGroup.setParam(viewPager, tagList, position);
        }
    }

    public MainViewModuleFactory getFactory() {
        return factory;
    }

    public void changeTabLayoutShow(boolean isShow) {
        if (binding != null) {
            binding.setShowTag(isShow);
        }
    }

    @Override
    public void onSearchClick() {
        SearchFragment.showFragment(this);
    }

    @Override
    public void scrollToTop() {
        viewModule.shoudTop.setValue(true);
    }

    @Override
    public void onSelectTag(int position) {

    }

    @Override
    public void onScrollTop(boolean isTop) {

    }

    @Override
    public void onScrollBottom(boolean isBottom) {

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().exitApp();
            super.onBackPressed();
        }
    }

    public void hideFabButton() {
        if (binding != null) {
            binding.floatBtn.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}