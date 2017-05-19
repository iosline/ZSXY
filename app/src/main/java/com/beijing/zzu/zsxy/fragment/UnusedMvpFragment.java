package com.beijing.zzu.zsxy.fragment;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.UnusedPresenter;
import com.beijing.zzu.zsxy.view.UnusedView;

/**
 * Created by jiayongkai on 2017/4/14.
 */

public class UnusedMvpFragment extends BaseMvpFragment<UnusedView,UnusedPresenter> implements UnusedView{
    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        presenter=new UnusedPresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_unused;
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
