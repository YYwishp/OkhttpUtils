package com.example.nohttptest;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by GYX on 2016/12/15.
 */
public class MyApplication extends Application {
	private static MyApplication mInstance;

	public static Context getContext() {
		return mInstance;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		//图片初始化
		Fresco.initialize(this);
		mInstance = this;
		NoHttp.initialize(this);
		Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
		Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。


	}
}
