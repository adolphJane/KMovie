package com.magicalrice.adolph.kmovie.widget.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.CountryISO1336;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDate;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResult;
import com.magicalrice.adolph.kmovie.data.datasource.CountryDataSource;

import java.util.List;

/**
 * Created by Adolph on 2018/5/18.
 */

public class ReleaseDateAdapter extends BaseQuickAdapter<ReleaseDatesResult, BaseViewHolder> {
    private CountryDataSource dataSource;

    public ReleaseDateAdapter(int layoutResId, @Nullable List<ReleaseDatesResult> data, CountryDataSource dataSource) {
        super(layoutResId, data);
        this.dataSource = dataSource;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReleaseDatesResult item) {
        String iso3166 = item.getIso_3166_1();
        List<ReleaseDate> dateList = item.getRelease_dates();
        String releaseDate = "";
        String typeName = "";
        if (dateList != null && dateList.size() > 0) {
            releaseDate = dateList.get(0).getRelease_date().toString();
            typeName = dateList.get(0).getTypeName();
            releaseDate = releaseDate.substring(0,releaseDate.indexOf("GMT+") == -1 ? releaseDate.length() - 1 : releaseDate.indexOf("GMT+"));
        }
        CountryISO1336 bean = dataSource.getCountryByISO3166(iso3166);
        if (bean != null) {
            GlideApp.with(mContext)
                    .load(bean.getImgPath())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into((ImageView) helper.getView(R.id.img_country_flag));
            helper.setText(R.id.tv_country_name, bean.getChiName());
        }
        helper.setText(R.id.tv_coutry_release_date, releaseDate)
                .setText(R.id.tv_place, typeName);

    }
}
