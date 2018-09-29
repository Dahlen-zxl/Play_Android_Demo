package com.example.zq.play_android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

public abstract class BaseActivity extends RxAppCompatActivity{

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }
    public <T extends BaseFragment,E extends BasePresenter> void addFragment(@NonNull Class<T> tClass,E presenter,int containerId,String tag,Bundle args){
        if (TextUtils.isEmpty(tag)){
            tag=tClass.getName();
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment addFragment = mFragmentManager.findFragmentByTag(tag);
        BaseFragment targetFragmen=null;
        if (addFragment==null){
            try {
                targetFragmen=tClass.newInstance();
                if (targetFragmen instanceof BaseView){
                    ((BaseView)targetFragmen).setPersenter(presenter);
                }
                addFragmentAnimation(transaction,targetFragmen);
                transaction.add(containerId,targetFragmen,tag);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else {
            targetFragmen=(BaseFragment) addFragment;
            if (targetFragmen.isHidden()){
                addFragmentAnimation(transaction,targetFragmen);
            }
            transaction.show(targetFragmen);
        }

        if (targetFragmen!=null){
            addFragmentAnimation(transaction,targetFragmen);
            hidePreFragment(transaction,targetFragmen);
            if (targetFragmen.isNeedAddToBackStack()){
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }

    }

    private void addFragmentAnimation(FragmentTransaction transaction,BaseFragment baseFragment){
        transaction.setCustomAnimations(baseFragment.getEnterAnimId(),baseFragment.getPopPreExistAnimId(),baseFragment.getPopPreEnterAnimId(),baseFragment.getPopPreExistAnimId());
    }
    //隐藏Fragment
    private void hidePreFragment(FragmentTransaction transaction,BaseFragment baseFragment) {
        if (baseFragment.isNeedHidePreFragment()) {
            List<Fragment> addedfragment = mFragmentManager.getFragments();
            for (Fragment fragment : addedfragment) {
                if (fragment != baseFragment) {
                    transaction.hide(fragment);
                }
            }
        }
    }
}
