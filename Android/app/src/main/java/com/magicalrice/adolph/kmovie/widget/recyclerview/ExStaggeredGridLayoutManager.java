package com.magicalrice.adolph.kmovie.widget.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class ExStaggeredGridLayoutManager extends StaggeredGridLayoutManager {
    private final String TAG = getClass().getSimpleName();
    private GridLayoutManager.SpanSizeLookup mSpanSizeup;

    public ExStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public GridLayoutManager.SpanSizeLookup getmSpanSizeup() {
        return mSpanSizeup;
    }

    public void setmSpanSizeup(GridLayoutManager.SpanSizeLookup mSpanSizeup) {
        this.mSpanSizeup = mSpanSizeup;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mSpanSizeup.getSpanSize(i) > 1) {
                try {
                    View view = recycler.getViewForPosition(i);
                    if (view != null) {
                        StaggeredGridLayoutManager.LayoutParams params = (LayoutParams) view.getLayoutParams();
                        params.setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
