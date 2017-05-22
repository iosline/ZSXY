package com.beijing.zzu.zsxy.presenter;

import android.util.Log;

import com.beijing.zzu.zsxy.Constants;
import com.beijing.zzu.zsxy.api.VideoService;
import com.beijing.zzu.zsxy.net.RetrofitManager;
import com.beijing.zzu.zsxy.rx.RxManager;
import com.beijing.zzu.zsxy.rx.RxSubscriber;
import com.beijing.zzu.zsxy.utils.JsonUtil;
import com.beijing.zzu.zsxy.utils.JsoupUtil;
import com.beijing.zzu.zsxy.view.VideoView;

/**
 * Created by jiayongkai on 2017/5/20.
 */

public class VideoPresenter extends BasePresenter<VideoView>{



    public void getVideoListData(String cateid,int page){

        VideoService service = RetrofitManager.getInstance().create1(VideoService.class);

        mSubscription=RxManager.getInstance().doSubscribe(service.getVideoListData(cateid, Constants.VIDEO_PAGE_SIZE,page),new RxSubscriber<String>(false){
            @Override
            protected void _onError() {
                mView.showNetError();
            }

            @Override
            protected void _onNext(String s) {
                mView.onSuccess(JsonUtil.parseVideoList(s));
            }
        });
    }
}
