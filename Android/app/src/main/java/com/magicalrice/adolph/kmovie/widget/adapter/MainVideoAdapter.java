package com.magicalrice.adolph.kmovie.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainVideoAdapter extends BaseQuickAdapter<BaseVideo, BaseViewHolder> implements ListPreloader.PreloadModelProvider {
    private List<BaseVideo> videoList;

    public MainVideoAdapter(int layoutResId, @Nullable List<BaseVideo> data) {
        super(layoutResId, data);
        this.videoList = data;
    }

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = "";
        if (videoList != null && videoList.size() > 0 && position != videoList.size()) {
            url = videoList.get(position).getPoster_path();
        }
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Object item) {
        return GlideApp.with(mContext)
                .load(item);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseVideo item) {
        if (item != null) {
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + item.getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_video));
            helper.setText(R.id.tv_release_date, item.getRelease_date())
                    .setText(R.id.tv_video_title, item.getTitle());
        }
    }
}
