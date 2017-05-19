package com.beijing.zzu.zsxy.api;

import com.beijing.zzu.zsxy.net.Api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jiayongkai on 2017/5/19.
 */

public interface GirlDetailService {

    String BASE_URL= Api.URL_GET_GIRL;

    @GET("{id}")
    Observable<String> getGirlDetailData(@Path("id") String id);
}
