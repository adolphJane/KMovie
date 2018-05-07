package com.magicalrice.adolph.kmovie.widget.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.magicalrice.adolph.kmovie.R;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MagicalLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
