package com.example.zq.play_android.base;

import android.app.Activity;

public interface BaseView <T extends BasePresenter>{
    void setPersenter(T presenter);

    Activity getActivtity();
}
