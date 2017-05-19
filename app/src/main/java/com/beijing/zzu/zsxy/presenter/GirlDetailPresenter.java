package com.beijing.zzu.zsxy.presenter;

import com.beijing.zzu.zsxy.api.GirlDetailService;
import com.beijing.zzu.zsxy.net.RetrofitManager;
import com.beijing.zzu.zsxy.rx.RxManager;
import com.beijing.zzu.zsxy.rx.RxSubscriber;
import com.beijing.zzu.zsxy.utils.JsoupUtil;
import com.beijing.zzu.zsxy.view.GirlDetailView;

/**
 * Created by jiayongkai on 2017/5/17.
 */

public class GirlDetailPresenter extends BasePresenter<GirlDetailView>{

     public void getGirlDetailData(String id){
         GirlDetailService service = RetrofitManager.getInstance().create1(GirlDetailService.class);
         mSubscription= RxManager.getInstance().doSubscribe(service.getGirlDetailData(id), new RxSubscriber<String>(false) {
             @Override
             protected void _onError() {
                 mView.showNetError();
             }

             @Override
             protected void _onNext(String s) {
                 mView.onSuccess(JsoupUtil.parseGirlDetail(s));
             }
         });
     }
}
