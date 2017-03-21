package com.example.toast;

import android.app.Application;

import com.tendcloud.tenddata.TCAgent;

/**
 * Created by GYX on 2017/2/13.
 */
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		TCAgent.init(this);
		TCAgent.setReportUncaughtExceptions(true);
	}
}
