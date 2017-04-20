package com.beijing.zzu.zsxy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiayongkai on 2017/4/18.
 */

public class GankItemFactory {

    public static Map<String,BaseFragment> map=new HashMap<>();

    public static BaseFragment getGankItmeFragment(String type){
        if (map.get(type) == null){
            GankItemFragment fragment=new GankItemFragment();
            Bundle args=new Bundle();
            args.putString("type",type);
            fragment.setArguments(args);
            map.put(type,fragment);
            return fragment;
        }else {
            return map.get(type);
        }
    }
}
