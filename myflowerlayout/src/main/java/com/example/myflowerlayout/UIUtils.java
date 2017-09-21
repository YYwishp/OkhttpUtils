package com.example.myflowerlayout;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

public class UIUtils {
	public static Context getContext(){
		return MyApplication.getContext();
	}
	
	public static Handler getHandler(){
		return MyApplication.getHandler();
	}
	
	public static Thread getMainThread(){
		return MyApplication.getMainThread();
	}
	public static int getMainThreadId(){
		return MyApplication.getMainThreadId();
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
	//将任务放置在主线程中运行的方法
	public static void runInMainThread(Runnable runnable){
		//如果当前任务就是在主线程中的,直接运行
		if(getMainThreadId() == android.os.Process.myTid()){
			runnable.run();
		}else{
			//确保传递进来的任务在主线程中运行
			//将任务传递到主线程的消息队列中,让其运行
			getHandler().post(runnable);
		}
	}

	/**
	 * @param mTabTextColorResId	传递文字选择器id
	 * @return	文字选择器xml转换成的java对象
	 */
	public static ColorStateList getColorStateList(int mTabTextColorResId) {
		return getResources().getColorStateList(mTabTextColorResId);
	}

	/**
	 * @param dimensId	传递一个dimens的索引id
	 * @return	将其转换成像素大小
	 */
	public static int getDimens(int dimensId) {
		return getResources().getDimensionPixelSize(dimensId);
	}

	/**
	 * @param runnableTask	延期执行的任务
	 * @param delayTime		延时的时间
	 * 延时执行任务方法
	 */
	public static void postDelayed(Runnable runnableTask, int delayTime) {
		getHandler().postDelayed(runnableTask, delayTime);
	}

	/**
	 * @param runnableTask	需要移除的任务
	 * 在handler中移除参数对应的任务
	 */
	public static void removeCallBack(Runnable runnableTask) {
		getHandler().removeCallbacks(runnableTask);
	}
}
