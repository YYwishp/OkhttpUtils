package com.example.myflowerlayout;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by GYX on 2017/9/21.
 */
public class MyApplication extends Application {
	private static Handler handler;
	private static Context context;
	private static Thread mainThread;
	private static int mainThreadId;
	@Override
	public void onCreate() {
		super.onCreate();
		//handler,Context,主线程,主线程id
		handler = new Handler();
		context = getApplicationContext();
		mainThread = Thread.currentThread();
		//获取当前线程的id,当前线程主线程
		mainThreadId = android.os.Process.myTid();
	}
	public static Handler getHandler() {
		return handler;
	}
	public static Context getContext() {
		return context;
	}
	public static Thread getMainThread() {
		return mainThread;
	}
	public static int getMainThreadId() {
		return mainThreadId;
	}


}
