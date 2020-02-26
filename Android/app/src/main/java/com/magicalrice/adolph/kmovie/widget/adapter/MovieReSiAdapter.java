package com.magicalrice.adolph.kmovie.widget.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

/**
 * Created by Adolph on 2018/5/21.
 */

public class MovieReSiAdapter extends BaseQuickAdapter<BaseMovie,BaseViewHolder> {

    public MovieReSiAdapter(int layoutResId, @Nullable List<BaseMovie> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseMovie item) {
        helper.addOnClickListener(R.id.tv_more)
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getOverview());
        GlideApp.with(mContext)
                .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BaseMovie) item).getPoster_path())
                .placeholder(R.drawable.item_img_placeholder)
                .error(R.drawable.item_img_error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into((ImageView) helper.getView(R.id.img_poster));
    }
}
