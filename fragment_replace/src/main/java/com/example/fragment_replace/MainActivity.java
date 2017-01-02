package com.example.fragment_replace;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
	private Button button1;
	private Button button2;
	private Button button3;
	private FrameLayout realtabcontent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EventBus.getDefault().register(this);
		initView();
	}

	private void initView() {

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		realtabcontent = (FrameLayout) findViewById(R.id.realtabcontent);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button1:
				FragmentManager manager = getSupportFragmentManager();

				//3.获取Fragment事务

				FragmentTransaction transaction = manager.beginTransaction();
				Fragment1 fragment1 = new Fragment1();
				transaction.replace(R.id.realtabcontent, fragment1);
				//5.提交事务
				transaction.commitAllowingStateLoss();


				break;
			case R.id.button2:
				changeFragment2();

				break;
			case R.id.button3:
				FragmentManager manager3 = getSupportFragmentManager();
				//3.获取Fragment事务
				FragmentTransaction transaction3 = manager3.beginTransaction();
				Fragment3 fragment3 = new Fragment3();
				transaction3.replace(R.id.realtabcontent, fragment3);
				//5.提交事务
				transaction3.commitAllowingStateLoss();
				break;
		}
	}

	private void changeFragment2() {
		FragmentManager manager2 = getSupportFragmentManager();
		//3.获取Fragment事务
		FragmentTransaction transaction2 = manager2.beginTransaction();
		Fragment2 fragment2 = new Fragment2();
		transaction2.replace(R.id.realtabcontent, fragment2);
		//5.提交事务(commit与commitAllowingStateLoss)区别
		transaction2.commitAllowingStateLoss();
	}

	@Subscribe
	public void onMessageEvent(LogoutEvent event) {
		Toast.makeText(this, "收到消息", Toast.LENGTH_SHORT).show();
		changeFragment2();
	}
}
