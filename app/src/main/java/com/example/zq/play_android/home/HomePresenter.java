package com.example.zq.play_android.home;

import android.util.Log;

import com.example.zq.play_android.BuildConfig;
import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.bean.Home;
import com.example.zq.play_android.data.source.remote.UserDataSource;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter implements HomeContract.Presenter{
    private UserDataSource mUserDataRepository;
    private HomeContract.View mView;

    public HomePresenter(UserDataSource userDataRepository) {
        this.mUserDataRepository=userDataRepository;
    }

    @Override
    public void homeCarouselData() {
        mUserDataRepository.getDataCarousel((RxFragment) mView, new Observer<List<Carousel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Carousel> carousels) {
                mView.getCarouselSucess(carousels);
            }

            @Override
            public void onError(Throwable e) {
                mView.getCarouselFail(e.getMessage());
                Log.e("HomePresenter", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void homeData(int id) {
        mUserDataRepository.getDataHomeMain((RxFragment) mView, new Observer<Home>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Home home) {
                mView.getHomeSucess(home);
            }

            @Override
            public void onError(Throwable e) {
                mView.getCarouselFail(e.getMessage());
                if (BuildConfig.DEBUG) Log.e("HomePresenter--22", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void attachView(HomeContract.View baseview) {
        mView=baseview;
    }

    @Override
    public void detachView() {
        mView=null;
    }
}

