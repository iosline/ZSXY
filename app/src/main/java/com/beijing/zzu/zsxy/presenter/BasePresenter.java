package com.beijing.zzu.zsxy.presenter;

import rx.Subscription;

/**
 * Created by jiayongkai on 2017/4/11.
 */

public abstract class BasePresenter<V> {

    public V mView;

    protected Subscription mSubscription;
    //View的绑定
    public void attach(V view){
        this.mView=view;
    }

    //View的销毁
    public void dettach(){
        mView = null;
        if (mSubscription != null){
            mSubscription.unsubscribe();
        }
    }
}
