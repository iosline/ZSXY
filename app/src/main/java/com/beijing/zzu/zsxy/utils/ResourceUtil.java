package com.beijing.zzu.zsxy.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beijing.zzu.zsxy.BaseApplication;
import com.beijing.zzu.zsxy.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jiayongkai on 2017/4/19.
 */

public class ResourceUtil {

    /**
     * 解析String类型的arrays.xml元素
     * @param arrayId
     * @return
     */
    public static List<String> stringArrayToList(Context context,int arrayId){
        return Arrays.asList(context.getResources().getStringArray(arrayId));
    }

    public static String resToStr(Context context,int strId){
        return context.getString(strId);
    }

    public static View inflate(Context context,int viewId, ViewGroup parent){
        return LayoutInflater.from(context).inflate(viewId,parent);
    }

    public static int resToColor(Context context,int colorId){
        return context.getResources().getColor(colorId);
    }

}
