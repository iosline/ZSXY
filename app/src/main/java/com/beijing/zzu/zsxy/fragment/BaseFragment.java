package com.beijing.zzu.zsxy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beijing.zzu.zsxy.activity.BaseAcitivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jiayongkai on 2017/5/5.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseAcitivity mActivity;
    protected View mRootView;
    protected Unbinder mUnbinder;
    private boolean mIsMulti = false;  //懒加载

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (BaseAcitivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    protected abstract void initData() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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

    protected abstract void initViews();

    protected abstract int getLayoutRes();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        }
    }

    protected abstract void fetchData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            fetchData();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
