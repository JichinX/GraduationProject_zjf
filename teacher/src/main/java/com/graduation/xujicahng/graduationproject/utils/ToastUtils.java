package com.graduation.xujicahng.graduationproject.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * XXXX.java是趣猜APP的XXXX类
 *
 * @author xujichang
 * @version 2016/5/16.
 */
public class ToastUtils {
    public static void Toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
