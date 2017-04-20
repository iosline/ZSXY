package com.beijing.zzu.zsxy.net;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class ApiException extends RuntimeException{

    public ApiException(){
        this("服务器异常....");
    }

    public ApiException(String meessage){
        super(meessage);
    }
}
