package com.beijing.zzu.zsxy.fragment;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.MySelfPresenter;
import com.beijing.zzu.zsxy.view.MySelfView;

/**
 * Created by jiayongkai on 2017/4/14.
 */

public class MySelfFragment extends BaseFragment<MySelfView,MySelfPresenter> implements MySelfView{
    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
      presenter=new MySelfPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_myslef;
    }

    @Override
    protected void fetchData() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }
}
