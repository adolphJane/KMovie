package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adolph on 2018/5/17.
 */

public class CountryISO1336 implements Parcelable {
    private String engName;
    private String chiName;
    private String imgPath;

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getChiName() {
        return chiName;
    }

    public void setChiName(String chiName) {
        this.chiName = chiName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.engName);
        dest.writeString(this.chiName);
        dest.writeString(this.imgPath);
    }

    public CountryISO1336() {
    }

    protected CountryISO1336(Parcel in) {
        this.engName = in.readString();
        this.chiName = in.readString();
        this.imgPath = in.readString();
    }

    public static final Parcelable.Creator<CountryISO1336> CREATOR = new Parcelable.Creator<CountryISO1336>() {
        @Override
        public CountryISO1336 createFromParcel(Parcel source) {
            return new CountryISO1336(source);
        }

        @Override
        public CountryISO1336[] newArray(int size) {
            return new CountryISO1336[size];
        }
    };
}
