package com.learn.cui19.myclipboard;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by cui19 on 2016/10/19.
 */

public class ScreenUtil {
    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return px
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 悬浮窗提示信息
     *
     * @param content
     */
    public static void snack(String content, View view) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }
}
