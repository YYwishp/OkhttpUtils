package com.example.timecount;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private TextView tvTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		tvTime = (TextView) findViewById(R.id.tv_time);

		tvTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CountDownTimerUtil countDownTimerUtil = new CountDownTimerUtil(tvTime, 60000, 1000);
				countDownTimerUtil.start();
			}
		});

	}
}
