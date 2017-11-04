package com.example.commons_long;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {
	private LinearLayout activityMain;
	private EditText edittext;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {


		activityMain = (LinearLayout) findViewById(R.id.activity_main);
		edittext = (EditText) findViewById(R.id.edittext);


		activityMain.addView(new FontView(this));


		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String trim = edittext.getText().toString().trim();
				if (!TextUtils.isEmpty(trim)) {
					if (TextUtils.isDigitsOnly(trim)) {
						Toast.makeText(MainActivity.this, "全是数字", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "为空", Toast.LENGTH_SHORT).show();
				}

			}
		});
		int[] ints = randomCommon(0, 11, 11);
		for (int i = 0; i < ints.length; i++) {
			Log.e("随机数----",ints[i]+"");
		}
	}

	public  int[] randomCommon(int min, int max, int n){
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while(count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if(num == result[j]){
					flag = false;
					break;
				}
			}
			if(flag){
				result[count] = num;
				count++;
			}
		}
		return result;
	}
}
