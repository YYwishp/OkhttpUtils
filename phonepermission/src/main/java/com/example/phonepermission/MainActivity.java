package com.example.phonepermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static final int REQUEST_CODE_ASK_CALL_PHONE = 123;
	private Button button;
	public String callPhoneNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		button = (Button) findViewById(R.id.button);


		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setBaseCallPhoneNum("15806120723");
			}
		});

	}
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CODE_ASK_CALL_PHONE:
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// Permission Granted授权
					//已经写好的拨号方法
					baseCallPhone();
				} else {
					// Permission Denied
					boolean isSecondRequest = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
					if (isSecondRequest) {
						/**重新请求授予权限，显示权限说明（该说明属于系统UI内容，区别第一次弹窗）**/
						ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_ASK_CALL_PHONE);
					} else {
						Toast.makeText(this, "录音权限被禁用，请在权限管理修改", Toast.LENGTH_SHORT).show();
					}
					//Toast.makeText(this, "未授权", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}

	}

	private void baseCallPhone() {
		if (!TextUtils.isEmpty(callPhoneNum)) {
			//判断权限是否添加
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
				//未授权
				Toast.makeText(this, "拨打电话权限被禁用，请在权限管理修改", Toast.LENGTH_SHORT).show();
			} else {
				//权限已经添加
				//用intent启动拨打电话
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callPhoneNum));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		} else {
			Toast.makeText(this, "对方未留联系方式", Toast.LENGTH_SHORT).show();
		}
	}

	//1，先设置号码，然后调用baseCallPhone
	public void setBaseCallPhoneNum(String num) {
		callPhoneNum = num;
		if (Build.VERSION.SDK_INT >= 23) {
			int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
			if (checkCallPhonePermission == PackageManager.PERMISSION_DENIED) {
				//1,请求权限
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
				//return;
			} else {
				//已经写好的拨号方法
				baseCallPhone();
			}
		} else {
			//写好的拨号方法
			baseCallPhone();

		}
	}
}
