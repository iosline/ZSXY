package com.beijing.zzu.zsxy.presenter;

import android.util.Log;

import com.beijing.zzu.zsxy.api.GirlService;
import com.beijing.zzu.zsxy.net.RetrofitManager;
import com.beijing.zzu.zsxy.rx.RxManager;
import com.beijing.zzu.zsxy.rx.RxSubscriber;
import com.beijing.zzu.zsxy.utils.JsoupUtil;
import com.beijing.zzu.zsxy.view.GirlItemView;

import java.util.List;

/**
 * Created by jiayongkai on 2017/5/8.
 */

public class GirlItemPresenter extends BasePresenter<GirlItemView>{

    public void getGirlItemData(String cid,int page){
        GirlService service = RetrofitManager.getInstance().create1(GirlService.class);

        mSubscription= RxManager.getInstance().doSubscribe(service.getGirlItemData(cid, page), new RxSubscriber<String>(false) {
            @Override
            protected void _onError() {
                mView.showNetError();
            }

            @Override
            protected void _onNext(String s) {
                mView.onSuccess(JsoupUtil.parseGirls(s));
            }


        });
    }
}
