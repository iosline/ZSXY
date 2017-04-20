package com.beijing.zzu.zsxy.utils;

import android.widget.Toast;

import com.beijing.zzu.zsxy.BaseApplication;

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
}
