package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Adolph on 2018/5/24.
 */

public class MainMeViewModule extends AndroidViewModel {
    public MainMeViewModule(@NonNull Application application) {
        super(application);
    }

    public UserInfo getUserInfo() {
        return JMessageClient.getMyInfo();
    }
}
