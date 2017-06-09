package com.example.touch_event_learn;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
	private MyViewPager viewpager;
	private SlidingTabLayout intestTab;
	private final String[] mTitles = new String[]{"我的需求", "我的订单","fsdfsdf"};
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		initView();
	}

	private void initView() {


		viewpager = (MyViewPager) findViewById(R.id.viewpager);
		intestTab = (SlidingTabLayout) findViewById(R.id.intest_tab);

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TouchEventActivity.class);
			}
		});
		List<Fragment> fragments= new ArrayList<Fragment>();
		fragments.add(new Fragment_1());
		fragments.add(new Fragment_2());
		fragments.add(new Fragment_3());
		MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);

		viewpager.setAdapter(adapter);
		viewpager.setOffscreenPageLimit(2);
		//tab设置viewpager
		intestTab.setViewPager(viewpager, mTitles);

	}


	class MyFragmentAdapter extends FragmentPagerAdapter {
		private List<Fragment> mFragments;

		public MyFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
			super(fm);

			mFragments=fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTitles[position];
		}

	}

}
