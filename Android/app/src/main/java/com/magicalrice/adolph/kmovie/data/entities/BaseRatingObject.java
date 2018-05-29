package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseRatingObject implements Parcelable {
    private int rating;

    public static final Creator<BaseRatingObject> CREATOR = new Creator<BaseRatingObject>() {
        @Override
        public BaseRatingObject createFromParcel(Parcel in) {
            return new BaseRatingObject(in);
        }

        @Override
        public BaseRatingObject[] newArray(int size) {
            return new BaseRatingObject[size];
        }
    };

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.rating);
    }

    public BaseRatingObject() {
    }

    protected BaseRatingObject(Parcel in) {
        this.rating = in.readInt();
    }

}
