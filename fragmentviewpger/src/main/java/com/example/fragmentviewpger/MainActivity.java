package com.example.fragmentviewpger;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	private ViewPager mViewPager;
	private MyFragmentPageAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		//这里因为是3.0一下版本，所以需继承FragmentActivity，通过getSupportFragmentManager()获取FragmentManager
		//3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
		FragmentManager fm = getSupportFragmentManager();
		//初始化自定义适配器
		mAdapter =  new MyFragmentPageAdapter(fm);
		//绑定自定义适配器
		mViewPager.setAdapter(mAdapter);
	}
}
