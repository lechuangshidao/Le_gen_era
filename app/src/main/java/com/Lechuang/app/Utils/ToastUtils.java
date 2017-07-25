package com.Lechuang.app.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiaowei on 16/2/23.
 */
public class ToastUtils {
    private static Toast mToast;

    public static void showShort(Context context, String text){
        if (mToast==null) {
            mToast=Toast.makeText(context, text,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showShort(Context context, int text){
        if (mToast == null) {
            mToast = Toast.makeText(context, text+"", Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
    public static void showLong(Context context, String text){
        if (mToast==null) {
            mToast=Toast.makeText(context, text,Toast.LENGTH_LONG);
        }else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
    public static void showLong(Context context, int text){
        if (mToast == null) {
            mToast = Toast.makeText(context, text+"", Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}
