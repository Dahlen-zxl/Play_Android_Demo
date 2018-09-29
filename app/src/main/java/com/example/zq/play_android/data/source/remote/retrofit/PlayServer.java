package com.example.zq.play_android.data.source.remote.retrofit;

import com.example.zq.play_android.bean.Home;
import com.example.zq.play_android.data.HttpResult;
import com.example.zq.play_android.bean.Carousel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PlayServer {
    //轮播图
    @GET("banner/json")
    Observable<HttpResult<List<Carousel>>> getCarousel();

    //首页数据
    @GET("article/list/0/json")
    Observable<HttpResult<Home>> getHomeData();
}
