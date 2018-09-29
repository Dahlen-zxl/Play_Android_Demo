package com.example.zq.play_android.home;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zq.play_android.R;
import com.example.zq.play_android.base.BaseFragment;
import com.example.zq.play_android.bean.Carousel;
import com.example.zq.play_android.bean.DatasBean;
import com.example.zq.play_android.bean.Home;
import com.example.zq.play_android.data.source.remote.UserDataRepository;
import com.example.zq.play_android.home.adpter.HomeBannerAdapter;
import com.example.zq.play_android.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private Banner head_banner;
    private RecyclerView home_recy;
    private HomeContract.Presenter mpresenter;
    private ArrayList<DatasBean> list=new ArrayList<>();
    private View view1;
    private SmartRefreshLayout home_smartrsfrsh;
    private int path=0;
    private List<DatasBean> datas;
    private HomeBannerAdapter homeBannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view1 = inflater.inflate(R.layout.banner_layout, null);
        initView(view);

        HomePresenter hoemPresenter = new HomePresenter(UserDataRepository.getInstance());

        head_banner = view1.findViewById(R.id.main_banner);
        setPersenter(hoemPresenter);
        mpresenter.homeCarouselData();
        mpresenter.homeData(path);

        return view;
    }


    @Override
    public void getCarouselSucess(List<Carousel> carousel) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (Carousel bannerData : carousel) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        head_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        head_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        head_banner.setImages(bannerImageList);
        //设置banner动画效果
        head_banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        head_banner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        head_banner.isAutoPlay(true);
        //设置轮播时间
        head_banner.setDelayTime(carousel.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        head_banner.setIndicatorGravity(BannerConfig.CENTER);

       /* mBanner.setOnBannerListener(i -> JudgeUtils.startArticleDetailActivity(_mActivity, null,
                0, mBannerTitleList.get(i), mBannerUrlList.get(i),
                false, false, true));*/
        //banner设置方法全部调用完毕时最后调用
        head_banner.start();
    }

    @Override
    public void getCarouselFail(String error) {
        Toast.makeText(baseActivity, error, Toast.LENGTH_SHORT).show();
    }

    //新闻列表
    @Override
    public void getHomeSucess(Home home) {
        datas = home.getDatas();
        list.addAll(datas);
        Log.e("有数据了",list.toString());
        homeBannerAdapter.notifyDataSetChanged();

    }

    @Override
    public void getHomeFail(String error) {

        Toast.makeText(baseActivity, "----2222" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPersenter(HomeContract.Presenter presenter) {
        mpresenter = presenter;
        mpresenter.attachView(this);
    }

    @Override
    public Activity getActivtity() {
        return null;
    }

    private void initView(View view) {
        home_smartrsfrsh = (SmartRefreshLayout) view.findViewById(R.id.home_smartrsfrsh);
        home_recy = (RecyclerView) view.findViewById(R.id.home_recy);
        home_recy.setLayoutManager(new LinearLayoutManager(getContext()));
        homeBannerAdapter=new HomeBannerAdapter(list);
        homeBannerAdapter.addHeaderView(view1);
        home_recy.setAdapter(homeBannerAdapter);


        home_smartrsfrsh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mpresenter.homeData(path);
                home_smartrsfrsh.finishRefresh();
            }
        });
        home_smartrsfrsh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                path++;
                mpresenter.homeData(path);
                home_smartrsfrsh.finishLoadMore();
            }
        });

    }
}
