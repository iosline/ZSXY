package com.beijing.zzu.zsxy.rx;

import com.beijing.zzu.zsxy.utils.CommonUtils;

import java.io.IOException;

import rx.Subscriber;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private boolean isShowLoading;

    public RxSubscriber(boolean isShowLoading){
        this.isShowLoading=isShowLoading;
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
    }

    /**
     * 可在此处统一显示loading view
     */
    private void showLoading() {
        if (isShowLoading){

        }
    }

    @Override
    public void onCompleted() {
        cancelLoading();
    }
    @Override
    public void onError(Throwable e) {
        //统一处理请求异常的情况
        if (e instanceof IOException){
            CommonUtils.showToast("网络连接异常...");
        }else {
            CommonUtils.showToast(e.toString());
        }

        _onError();
        cancelLoading();
    }

    protected abstract void _onError() ;

    @Override
    public void onNext(T t) {
       _onNext(t);
    }

    protected abstract void _onNext(T t);
    private void cancelLoading() {
    }

}
