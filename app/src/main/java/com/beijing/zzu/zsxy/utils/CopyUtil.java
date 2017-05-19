package com.beijing.zzu.zsxy.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by jiayongkai on 2017/5/5.
 */

public class CopyUtil {
    public static void copy(Context context,String plaintText){
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText(null,plaintText));
    }
}
