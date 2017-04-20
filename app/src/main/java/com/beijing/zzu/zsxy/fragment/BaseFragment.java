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

public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment{

    protected T presenter;

    protected Unbinder mUnbinder;

    protected Context mContext;
    //缓存Fragment view
    protected View mRootView;
    
    private boolean mIsMulti = false;  //懒加载
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mContext= BaseApplication.getContext();
        initData();
    }

    protected abstract void initData() ;

    protected abstract void initPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), null);
            mUnbinder=ButterKnife.bind(this,mRootView);
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    protected abstract void initViews() ;

    protected abstract int getLayoutRes();
    

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 获取网络数据
     */
    protected abstract void fetchData();;

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        presenter.dettach();
        mUnbinder.unbind();
        super.onDestroy();
    }
}
