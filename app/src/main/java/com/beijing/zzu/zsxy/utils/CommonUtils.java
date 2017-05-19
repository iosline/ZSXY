package com.beijing.zzu.zsxy.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.beijing.zzu.zsxy.BaseApplication;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiayongkai on 2017/4/12.
 */

public class CommonUtils {

    public static final String MOBILE = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";

    public static boolean isMobile(String phoneNum){
        return Regular(phoneNum, MOBILE);
    }

    private static boolean Regular(String str, String pattern) {
        if (null == str || str.trim().length() <= 0)
            return false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void showToast(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String msg){
        Toast.makeText(BaseApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    /**
     * 获取屏幕宽度，单位pix
     */

    public static int getScreenWidth(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels - getStatusBarHeight(context);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
