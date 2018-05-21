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
import com.magicalrice.adolph.kmovie.data.entities.BaseCollection;
import com.magicalrice.adolph.kmovie.data.entities.BaseCompany;
import com.magicalrice.adolph.kmovie.data.entities.BaseKeyword;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BasePerson;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adolph on 2018/5/9.
 */

public class SearchResultsAdapter<Z> extends BaseQuickAdapter<Z, BaseViewHolder> implements ListPreloader.PreloadModelProvider {
    private int type = 0;

    public SearchResultsAdapter(int layoutResId, @Nullable List<Z> data, int type) {
        super(layoutResId, data);
        this.type = type;
    }

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = "";
        if (mData != null && mData.size() > 0 && position != mData.size()) {
            if (type == 1) {
                url = ((BaseMovie) mData.get(position)).getPoster_path();
            } else if (type == 2) {
                url = ((BaseTvShow) mData.get(position)).getPoster_path();
            } else if (type == 3) {
                url = ((BaseCollection) mData.get(position)).getPoster_path();
            } else if (type == 4) {
                url = ((BasePerson) mData.get(position)).getProfile_path();
            } else if (type == 5) {
                url = ((BaseCompany) mData.get(position)).getLogo_path();
            }
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
    protected void convert(BaseViewHolder helper, Z item) {
        if (type == 1) {
            helper.addOnClickListener(R.id.tv_more)
                    .setText(R.id.tv_title, ((BaseMovie) item).getTitle())
                    .setText(R.id.tv_content, ((BaseMovie) item).getOverview());
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BaseMovie) item).getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        } else if (type == 2) {
            helper.addOnClickListener(R.id.tv_more)
                    .setText(R.id.tv_title, ((BaseTvShow) item).getName())
                    .setText(R.id.tv_content, ((BaseTvShow) item).getOverview());
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BaseTvShow) item).getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        } else if (type == 3) {
            helper.addOnClickListener(R.id.tv_more)
                    .setText(R.id.tv_title, ((BaseCollection) item).getName())
                    .setText(R.id.tv_content, "无");
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BaseCollection) item).getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        } else if (type == 4) {
            helper.addOnClickListener(R.id.tv_more)
                    .setText(R.id.tv_title, ((BasePerson) item).getName())
                    .setText(R.id.tv_content, "无")
                    .setText(R.id.tv_1, "人物简介");
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BasePerson) item).getProfile_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        } else if (type == 5) {
            helper.addOnClickListener(R.id.tv_more)
                    .setText(R.id.tv_title, ((BaseCompany) item).getName())
                    .setText(R.id.tv_content, "无")
                    .setText(R.id.tv_1, "公司简介");
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + ((BaseCompany) item).getLogo_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_poster));
        }
    }
}
