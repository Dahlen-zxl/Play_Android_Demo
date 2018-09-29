package com.example.zq.play_android.home;

import com.example.zq.play_android.base.BasePresenter;
import com.example.zq.play_android.base.BaseView;
import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.bean.Home;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void getCarouselSucess(List<Carousel> carousel);
        void getCarouselFail(String error);

        void getHomeSucess(Home home);
        void getHomeFail(String error);

    }
    interface Presenter extends BasePresenter<HomeContract.View>{

        void homeCarouselData();

        void homeData(int id);
    }
}
