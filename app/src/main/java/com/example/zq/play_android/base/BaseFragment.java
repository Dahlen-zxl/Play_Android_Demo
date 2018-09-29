package com.example.zq.play_android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment{
    public BaseActivity baseActivity;
    private String TAG;

    protected boolean isNeedAddToBackStack(){
        return true;
    }

    protected boolean isNeedHidePreFragment(){
        return true;
    }

    protected int getEnterAnimId(){
        return 0;
    }
    protected int getPopPreExistAnimId(){
        return 0;
    }

    protected int getPopPreEnterAnimId(){
        return 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity=(BaseActivity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseFragment", "");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
