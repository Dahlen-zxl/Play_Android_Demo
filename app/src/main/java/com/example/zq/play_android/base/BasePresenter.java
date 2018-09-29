package com.example.zq.play_android.base;

public interface BasePresenter <T extends BaseView>{
    void attachView(T baseview);

    void detachView();
}
