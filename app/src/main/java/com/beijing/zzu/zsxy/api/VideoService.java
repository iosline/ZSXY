package com.beijing.zzu.zsxy.api;

import com.beijing.zzu.zsxy.model.VideoItemData;
import com.beijing.zzu.zsxy.net.Api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiayongkai on 2017/5/20.
 */

public interface VideoService {

    String BASE_URL= Api.URL_GET_VIDEO;


    //cateid=124&per=%22+0+%22&page=%22+1
    @GET("index_api")
    Observable<String> getVideoListData(@Query("cateid") String cateid, @Query("per") int per, @Query("page") int page);
}
