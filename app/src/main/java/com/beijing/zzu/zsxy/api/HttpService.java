package com.beijing.zzu.zsxy.api;


import com.beijing.zzu.zsxy.model.GankItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public interface HttpService {

    public static String BASE_URL="http://gank.io/api/";

//    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
//
//    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
//    请求个数： 数字，大于0
//    第几页：数字，大于0

    @GET("data/{type}/10/{page}")
    Observable<HttpResult<List<GankItem>>> getGankItemList(@Path("type") String type,@Path("page") String page);



}
