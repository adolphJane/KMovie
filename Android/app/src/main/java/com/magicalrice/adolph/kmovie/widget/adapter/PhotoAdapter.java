package com.magicalrice.adolph.kmovie.widget.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;

import java.util.List;

public class PhotoAdapter extends BaseQuickAdapter<Image,BaseViewHolder> {
    public PhotoAdapter(int layoutResId, @Nullable List<Image> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Image item) {
        int height = item.getHeight();
        int width = item.getWidth();
        int imgWidth = (int) (ScreenUtils.dp2px(mContext,160) * width / height);
        GlideApp.with(mContext)
                .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + item.getFile_path())
                .placeholder(R.drawable.item_img_placeholder)
                .error(R.drawable.item_img_error)
                .override(imgWidth, (int) ScreenUtils.dp2px(mContext,160))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into((ImageView) helper.getView(R.id.img_photo));
    }
}
