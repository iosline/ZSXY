package com.beijing.zzu.zsxy.rx;


import com.beijing.zzu.zsxy.api.HttpResult;
import com.beijing.zzu.zsxy.net.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class RxManager {
    public static RxManager rxManager;

    private RxManager(){}

    public  static RxManager getInstance(){
        if (rxManager == null){
            synchronized (RxManager.class){
                if (rxManager == null){
                    return new RxManager();
                }
            }
        }
        return rxManager;
    }


    public <T> Subscription doSubscribe(Observable<T> observable, Subscriber<T> subscriber){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> Subscription doSubscribe1(Observable<HttpResult<T>> observable, Subscriber<T> subscriber){
        return observable.map(new Func1<HttpResult<T>, T>() {

            @Override
            public T call(HttpResult<T> httpResult) {
                if (httpResult.isError()){
                    throw new ApiException();
                }
                return httpResult.getResults();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
