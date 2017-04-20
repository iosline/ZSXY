package com.beijing.zzu.zsxy.presenter;

import com.beijing.zzu.zsxy.api.HttpService;
import com.beijing.zzu.zsxy.model.GankItem;
import com.beijing.zzu.zsxy.net.RetrofitManager;
import com.beijing.zzu.zsxy.rx.RxManager;
import com.beijing.zzu.zsxy.rx.RxSubscriber;
import com.beijing.zzu.zsxy.view.GankItemView;

import java.util.List;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class GankItemPresenter extends BasePresenter<GankItemView>{

    public void getGankItemData(String type,String page){

        HttpService sercice = RetrofitManager.getInstance().create(HttpService.class);
        mSubscription= RxManager.getInstance().doSubscribe1(sercice.getGankItemList(type,page), new RxSubscriber<List<GankItem>>(true) {
            @Override
            protected void _onError() {
                mView.showNetError();
            }

            @Override
            protected void _onNext(List<GankItem> gankItems) {
                mView.onSuccess(gankItems);
            }
        });
    }
}
