package com.magicalrice.adolph.kmovie.widget.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

public class LoveAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public LoveAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (item != null) {
            String poster = "";
            if (item instanceof MovieDetailBean) {
                poster = ((MovieDetailBean) item).getMovie().getPoster_path();
            } else if (item instanceof TvShowDetailBean) {
                poster = ((TvShowDetailBean) item).getTvShow().getPoster_path();
            } else if (item instanceof RoleDetailBean) {
                poster = ((RoleDetailBean) item).getPerson().getProfile_path();
            }
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + poster)
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        }
    }
}
