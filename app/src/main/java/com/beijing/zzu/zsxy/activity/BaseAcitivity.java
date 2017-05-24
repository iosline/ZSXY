package com.beijing.zzu.zsxy.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.beijing.zzu.zsxy.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jiayongkai on 2017/4/11.
 */

public abstract class BaseAcitivity extends AppCompatActivity{

    protected Context mContext;
    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mContext=this;
        mUnbinder=ButterKnife.bind(this);

        initData();
        initView();
    }

    protected abstract void initData();


    protected abstract void initView();


    protected abstract int getLayoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
