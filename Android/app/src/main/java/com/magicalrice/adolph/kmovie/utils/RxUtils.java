package com.magicalrice.adolph.kmovie.utils;

import com.magicalrice.adolph.kmovie.data.remote.services.BaseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/4/12.
 */

public class RxUtils {
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<BaseResult<T>, T> handleResult() {
        return upstream -> upstream.flatMap(
                tBaseResult -> {
                    if (tBaseResult.isSuccess()) {
                        return Observable.just(tBaseResult.getData());
                    } else {
                        return Observable.empty();
                    }
                }
        );
    }
}
