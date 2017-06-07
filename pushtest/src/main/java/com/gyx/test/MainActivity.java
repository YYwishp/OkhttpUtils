package com.gyx.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmengMessageDeviceConfig;

public class MainActivity extends AppCompatActivity {
	private Button button;
	private PushAgent mPushAgent;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPushAgent = PushAgent.getInstance(this);


		text = (TextView) findViewById(R.id.text);


		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String registrationId = mPushAgent.getRegistrationId();
				text.setText("Device_Token:"+registrationId);
				Toast.makeText(MainActivity.this, "主界面"+registrationId, Toast.LENGTH_SHORT).show();
			}
		});


		String test = "{\"service_sn\":\"sdfklgnkadfnjksdnfjk\",\"mobile\":\"\",\"wechat\":\"\",\"agree\":\"null\"}";
		ChatBean chatBean = Convert.fromJson(test, ChatBean.class);
		String agree = chatBean.getAgree();
		Boolean aBoolean = Boolean.valueOf(agree);
		if (aBoolean) {
			Toast.makeText(this, agree, Toast.LENGTH_SHORT).show();
		} else {

		}

	}





	private void appInfo() {
		String pkgName = getApplicationContext().getPackageName();
		String info = String.format("DeviceToken:%s\n" + "SdkVersion:%s\nAppVersionCode:%s\nAppVersionName:%s",
				mPushAgent.getRegistrationId(), MsgConstant.SDK_VERSION,
				UmengMessageDeviceConfig.getAppVersionCode(this), UmengMessageDeviceConfig.getAppVersionName(this));
		//updatelog("应用包名:" + pkgName + "\n" + info);
	}
}
