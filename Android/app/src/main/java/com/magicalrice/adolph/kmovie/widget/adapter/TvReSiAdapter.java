package com.magicalrice.adolph.kmovie.widget.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

/**
 * Created by Adolph on 2018/5/21.
 */

public class TvReSiAdapter extends BaseQuickAdapter<BaseTvShow,BaseViewHolder> {
    public TvReSiAdapter(int layoutResId, @Nullable List<BaseTvShow> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseTvShow item) {
        helper.addOnClickListener(R.id.tv_more)
                .setText(R.id.tv_title, item.getName())
                .setText(R.id.tv_content, item.getOverview());
        GlideApp.with(mContext)
                .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + item.getPoster_path())
                .placeholder(R.drawable.item_img_placeholder)
                .error(R.drawable.item_img_error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into((ImageView) helper.getView(R.id.img_poster));
    }
}
