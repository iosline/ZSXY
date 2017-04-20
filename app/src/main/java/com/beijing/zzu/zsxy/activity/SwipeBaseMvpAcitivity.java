package com.beijing.zzu.zsxy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beijing.zzu.zsxy.presenter.BasePresenter;
import com.beijing.zzu.zsxy.swipeback.SwipeBackActivity;

import butterknife.ButterKnife;

/**
 * Created by jiayongkai on 2017/4/11.
 */

public abstract class SwipeBaseMvpAcitivity<V,T extends BasePresenter<V>> extends SwipeBackActivity{

    public T presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initViews();
        setSaveInstanceState(savedInstanceState);
        updateViews();
    }

    protected  void setSaveInstanceState(Bundle savedInstanceState){

    }

    protected abstract void updateViews();

    protected abstract void initViews();


    protected abstract int getLayoutRes();

    protected abstract void initPresenter() ;

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null){
            presenter.attach((V) this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            presenter.dettach();
        }

    }

}
