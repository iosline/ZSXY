package com.beijing.zzu.zsxy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.beijing.zzu.zsxy.presenter.BasePresenter;
import com.beijing.zzu.zsxy.swipeback.SwipeBackActivity;

import butterknife.ButterKnife;

/**
 * Created by jiayongkai on 2017/4/11.
 */

public abstract class BaseMvpAcitivity<V,T extends BasePresenter<V>> extends BaseAcitivity{

    public T presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        fetchData();
    }



    protected abstract void fetchData();

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
