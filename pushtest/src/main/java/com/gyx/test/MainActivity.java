package com.gyx.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.umeng.message.PushAgent;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		PushAgent mPushAgent =  PushAgent.getInstance(this);
		String registrationId = mPushAgent.getRegistrationId();
		Toast.makeText(this, "主界面"+registrationId, Toast.LENGTH_SHORT).show();
	}
}
