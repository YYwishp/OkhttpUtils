package com.example.touch_event_learn;

import android.app.Application;
import android.content.Context;

/**
 * Created by GYX on 2017/6/7.
 */
public class MyApplication extends Application {
	public MyApplication() {
	}

	public static MyApplication  mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static Context getContext() {
		return mInstance;
	}
}
