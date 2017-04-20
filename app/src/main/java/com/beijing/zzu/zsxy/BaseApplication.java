package com.beijing.zzu.zsxy;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


/**
 * Created by jiayongkai on 2017/4/11.
 */

public class BaseApplication extends Application{

    protected static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        MultiDex.install(this);
    }

    public static Context getContext(){
        return context;
    }
}
