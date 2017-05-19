package com.beijing.zzu.zsxy.api;

import com.beijing.zzu.zsxy.net.Api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiayongkai on 2017/5/8.
 */

public interface GirlService {
    String BASE_URL= Api.URL_GET_GIRL;

    @GET("show.htm")
    Observable<String> getGirlItemData(@Query("cid") String cid,@Query("pager_offset") int pager_offset);

}
