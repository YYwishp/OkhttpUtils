package com.example.navigationtest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {


	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private FrameLayout layoutContent;
	//标示按钮的索引值
	private int index = 0;
	//=======================================================================================
	FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
		//设置fragment个数
		@Override
		public int getCount() {
			return 5;
		}

		//根据索引值position去获取相应的fragment
		@Override
		public Fragment getItem(int position) {
			BaseFragment baseFragment = null;
			//根据索引值获取fragment
			switch (position) {
				case 0:
					//首页
					baseFragment = new FunctionFragment();
					break;
				case 1:
					//新闻中心
					baseFragment = new NewsCenterFragment();
					break;
				case 2:
					//智慧服务
					baseFragment = new SmartServiceFragment();
					break;
				case 3:
					//政务指南
					baseFragment = new GovAffairsFragment();
					break;
				case 4:
					//设置
					baseFragment = new SettingFragment();
					break;
			}
			return baseFragment;
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layoutContent = (FrameLayout) findViewById(R.id.layout_content);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
	/*	mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
		mNavigationView.setItemIconTintList(null);
		//
		setupDrawerContent(mNavigationView);
		//
		mNavigationView.setCheckedItem(R.id.nav_home);
		//设置成首页
		Fragment framFragment = (Fragment) fragmentPagerAdapter.instantiateItem(layoutContent, 0);
		fragmentPagerAdapter.setPrimaryItem(null, 0, framFragment);
		fragmentPagerAdapter.finishUpdate(null);
*/



		//点击弹出侧拉菜单
		/*idTvContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});*/
	}

	private void setupDrawerContent(NavigationView navigationView) {
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			private MenuItem mPreMenuItem;

			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
						/*if (mPreMenuItem != null) {
							mPreMenuItem.setChecked(false);
						} else {
							menuItem.setChecked(true);
							mDrawerLayout.closeDrawers();
							mPreMenuItem = menuItem;
						}*/
				int itemId = menuItem.getItemId();
				switch (itemId) {
					case R.id.nav_home:
						index = 0;
						break;
					case R.id.nav_messages:
						index = 1;
						break;
					case R.id.nav_friends:
						index = 2;
						break;
					case R.id.nav_discussion:
						index = 3;
						break;
					case R.id.nav_friends1:
						index = 4;
						break;
				}
				Fragment framFragment = (Fragment) fragmentPagerAdapter.instantiateItem(layoutContent, index);
				fragmentPagerAdapter.setPrimaryItem(null, 0, framFragment);
				fragmentPagerAdapter.finishUpdate(null);

				menuItem.setChecked(true);
				mDrawerLayout.closeDrawers();
				return true;
			}
		});
	}
}
