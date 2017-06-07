package com.example.touch_event_learn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;



/**
 * ===============================
 * -UI工具类
 * Created by GYX on 2016/10/31.
 */

public class UIUtils {
    public static Context getContext(){
        return MyApplication.getContext();
    }



    /**
     * @param layoutId	布局id
     * @return	参数指向的布局转换成的view对象
     */
    public static View inflate(int layoutId){
        return View.inflate(getContext(), layoutId, null);
    }

    public static Resources getResources(){
        return getContext().getResources();
    }

    public static Drawable getDrawable(int drawableId){
        return getResources().getDrawable(drawableId);
    }
    public static String getString(int stringId){
        return getResources().getString(stringId);
    }

    /**
     * @param stringArrayId	传递在xml中定义字符串数组id
     * @return	通过id获取到的字符串数组
     */
    public static String[] getStringArray(int stringArrayId){
        return getResources().getStringArray(stringArrayId);
    }

    //1dp = 0.75px
    //1dp = 1px		480*320
    //1dp = 1.5px
    //1dp = 2px
    //1dp = 3px

    //dip-->px	dpi==ppi(像素密度)
    /**
     * @param dip	接受的dp值
     * @return		此dp在当前手机上转换成的像素值
     */
    public static int dip2px(int dip){
        //1,获取不同手机对应的dip和px的比例转换关系
        float density = getResources().getDisplayMetrics().density;
        return (int)(dip*density+0.5);
    }
    //px--->dip
    /**
     * @param px	接受的像素值
     * @return		上诉像素值,在当前手机上转换成的dp大小,不同的手机得到的dp会不一致
     */
    public static int px2dip(int px){
        //1,获取不同手机对应的dip和px的比例转换关系
        float density = getResources().getDisplayMetrics().density;
        return (int)(px/density+0.5);
    }



    /**
     * @param dimensId	传递一个dimens的索引id
     * @return	将其转换成像素大小
     */
    public static int getDimens(int dimensId) {
        return getResources().getDimensionPixelSize(dimensId);
    }



    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
