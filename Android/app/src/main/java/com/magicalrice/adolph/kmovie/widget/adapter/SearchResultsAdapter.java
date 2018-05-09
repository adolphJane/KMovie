package com.magicalrice.adolph.kmovie.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.base.GlideApp;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adolph on 2018/5/9.
 */

public class SearchResultsAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements ListPreloader.PreloadModelProvider {
    private Context context;
    private int type = 0;
    public SearchResultsAdapter(int layoutResId, @Nullable List<T> data,int type,Context context) {
        super(layoutResId, data);
        this.type = type;
        this.context = context;
    }

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = "";
        if (mData != null && mData.size() > 0 && position != mData.size()) {

        }
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Object item) {
        return GlideApp.with(context)
                .load(item);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {

    }
}
