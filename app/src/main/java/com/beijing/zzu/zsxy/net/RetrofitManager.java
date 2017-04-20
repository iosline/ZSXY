package com.beijing.zzu.zsxy.net;


import com.beijing.zzu.zsxy.BuildConfig;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class RetrofitManager {

    private static final int DEFAULT_TIMEOUT = 10;

    public static RetrofitManager retrofitManager;

    private RetrofitManager(){}

    public static RetrofitManager getInstance(){
        if (retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager=new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public <S> S create(Class<S> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl(service))
                .client(getOkhttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient getOkhttpClient() {

        //配置超时拦截器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别

            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    /**
     * 解析接口中的BASE_URL，解决BASE_URL不一致的问题
     * @param service
     * @param <S>
     * @return
     */
    private <S> String getBaseUrl(Class<S> service) {
        try {
            Field field = service.getField("BASE_URL");
            return (String) field.get(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
