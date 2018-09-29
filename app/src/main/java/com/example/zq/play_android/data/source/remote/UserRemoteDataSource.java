package com.example.zq.play_android.data.source.remote;

import com.example.zq.play_android.bean.Home;
import com.example.zq.play_android.data.HttpResult;
import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.data.source.remote.retrofit.DataRetrofit;
import com.example.zq.play_android.data.source.remote.retrofit.PlayServer;
import com.example.zq.play_android.exception.ServerException;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserRemoteDataSource implements UserDataSource{
    private static UserRemoteDataSource INSTANCE;
    private PlayServer service;
    public UserRemoteDataSource() {
        service = DataRetrofit.getRetrofitServer();
    }

    public static synchronized UserRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }
    @Override
    public void getDataCarousel(RxFragment fragment, Observer<List<Carousel>> observer) {
        Observable<HttpResult<List<Carousel>>> httpResultObservable = service.getCarousel();
        httpResultObservable.flatMap(new Function<HttpResult<List<Carousel>>, ObservableSource<List<Carousel>>>() {
            @Override
            public ObservableSource<List<Carousel>> apply(HttpResult<List<Carousel>> listHttpResult) throws Exception {
                if (listHttpResult.getErrorCode()==0){
                    return Observable.just(listHttpResult.getData());
                }
                return Observable.error(new ServerException(listHttpResult.getErrorCode(),listHttpResult.getErrorMsg()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<List<Carousel>>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);
    }

    @Override
    public void getDataHomeMain(RxFragment fragment, Observer<Home> observer) {
        Observable<HttpResult<Home>> serviceHomeData = service.getHomeData();
        serviceHomeData.flatMap(new Function<HttpResult<Home>, ObservableSource<Home>>() {
            @Override
            public ObservableSource<Home> apply(HttpResult<Home> homeHttpResult) throws Exception {
                if (homeHttpResult.getErrorCode()==0){
                    return Observable.just(homeHttpResult.getData());
                }
                return Observable.error(new ServerException(homeHttpResult.getErrorCode(),homeHttpResult.getErrorMsg()));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.<Home>bindUntilEvent(FragmentEvent.DETACH))
                .subscribe(observer);
    }


}
