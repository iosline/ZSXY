package com.beijing.zzu.zsxy.fragment;

import com.beijing.zzu.zsxy.R;
import com.beijing.zzu.zsxy.presenter.MessagePresenter;
import com.beijing.zzu.zsxy.view.MessageView;

/**
 * Created by jiayongkai on 2017/4/14.
 */

public class MessageFragment extends BaseFragment<MessageView,MessagePresenter> implements MessageView{
    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {
        presenter=new MessagePresenter();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
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
