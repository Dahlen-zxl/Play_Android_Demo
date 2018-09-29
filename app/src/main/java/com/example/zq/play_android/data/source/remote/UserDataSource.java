package com.example.zq.play_android.data.source.remote;

import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.bean.Home;
import com.trello.rxlifecycle2.components.support.RxFragment;


import java.util.List;

import io.reactivex.Observer;

public interface UserDataSource{

    //请求轮播图数据
    void getDataCarousel(RxFragment fragment, Observer<List<Carousel>> observer);

    //首页请求数据
    void getDataHomeMain(RxFragment fragment, Observer<Home> observer);


}
