package com.beijing.zzu.zsxy.fragment;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beijing.zzu.zsxy.BaseApplication;
import com.beijing.zzu.zsxy.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 声明周期 onAttach()——>onCreate()——>onCreateView()——>onActivityCreated()
 * Created by jiayongkai on 2017/4/12.
 */

public abstract class BaseMvpFragment<V,T extends BasePresenter<V>> extends BaseFragment{

    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    protected abstract void initPresenter();

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }
}
