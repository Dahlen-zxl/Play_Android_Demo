package com.example.zq.play_android.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.bean.Home;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.internal.Preconditions;

import java.util.List;

import io.reactivex.Observer;

public class UserDataRepository implements UserDataSource{
    @NonNull
    private UserDataSource mRemoteDataSource;

    @Nullable
    private static UserDataRepository INSTANCE=null;

    private UserDataRepository(@NonNull UserDataSource remoteDataSource){
        mRemoteDataSource= Preconditions.checkNotNull(remoteDataSource,"Login remote data source is not allowed null");
    }

    public static UserDataRepository getInstance(){
        if (INSTANCE==null){
            synchronized (UserDataRepository.class){
                if (INSTANCE==null){
                    INSTANCE = new UserDataRepository(new UserRemoteDataSource());
                }
            }
        }
        return INSTANCE;
    }


    //实现轮播图
    @Override
    public void getDataCarousel(RxFragment fragment, Observer<List<Carousel>> observer) {
        mRemoteDataSource.getDataCarousel(fragment,observer);
    }

    @Override
    public void getDataHomeMain(RxFragment fragment, Observer<Home> observer) {
        mRemoteDataSource.getDataHomeMain(fragment,observer);
    }


}
