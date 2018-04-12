package com.magicalrice.adolph.kmovie.data.remote.services;

/**
 * Created by Adolph on 2018/4/12.
 */

public class BaseResult<T> {
    private boolean success;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
