package com.magicalrice.adolph.kmovie.widget.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.CastMember;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

public class StarAdapter extends BaseQuickAdapter<CastMember,BaseViewHolder>{
    public StarAdapter(int layoutResId, @Nullable List<CastMember> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CastMember item) {
        if (item != null) {
            GlideApp.with(mContext)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + item.getProfile_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_star));
            helper.setText(R.id.tv_star, item.getName() + "\n饰\n" + item.getCharacter());
        }
    }
}
