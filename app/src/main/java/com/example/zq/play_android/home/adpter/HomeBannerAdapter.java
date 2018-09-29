package com.example.zq.play_android.home.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zq.play_android.R;
import com.example.zq.play_android.bean.DatasBean;

import java.util.List;

public class HomeBannerAdapter extends BaseQuickAdapter<DatasBean,BaseViewHolder> {

    private String name;
    public HomeBannerAdapter(@Nullable List<DatasBean> data) {
        super(R.layout.article_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DatasBean item) {
        helper.setText(R.id.home_item_title,item.getAuthor());
        helper.setText(R.id.home_item_content,item.getSuperChapterName());
        helper.setText(R.id.home_item_contentlife,item.getChapterName());
        helper.setText(R.id.home_item_comtentbelw,item.getTitle());
        helper.setText(R.id.home_item_shi,item.getNiceDate());
        for (int i = 0; i <item.getTags().size() ; i++) {
            name = item.getTags().get(i).getName();
        }
        helper.setText(R.id.home_ite_xiang,name);

    }

}
