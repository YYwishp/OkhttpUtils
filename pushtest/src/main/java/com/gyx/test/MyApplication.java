package com.gyx.test;

import android.app.Application;
import android.widget.Toast;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * Created by GYX on 2017/2/20.
 */
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		PushAgent mPushAgent =  PushAgent.getInstance(this);
		//注册推送服务，每次调用register方法都会回调该接口
		mPushAgent.register(new IUmengRegisterCallback() {

			@Override
			public void onSuccess(String deviceToken) {
				//注册成功会返回device token
				Toast.makeText(MyApplication.this, "application的"+deviceToken, Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onFailure(String s, String s1) {

			}
		});
	}
}
