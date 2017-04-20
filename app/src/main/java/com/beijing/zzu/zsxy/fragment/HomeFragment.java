package com.beijing.zzu.zsxy.fragment;

import android.support.v4.app.Fragment;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.HomePresenter;
import com.beijing.zzu.zsxy.view.HomeView;

/**
 * Created by jiayongkai on 2017/4/14.
 */

public class HomeFragment extends BaseFragment<HomeView,HomePresenter> implements HomeView{

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
      presenter=new HomePresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
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
