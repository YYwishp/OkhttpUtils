package com.example.newfragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {

	private String mTextviewArray[] = {"租房", "管家", "我的"};
	private Class fragmentArray[] = {RentFragment.class, SecondFragment.class, RentFragment.class};
	private int mImageViewArray[] = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};


	//定义FragmentTabHost对象,,不会重复开
	private CustomFragmentTabHost mTabHost;
	//定义一个布局
	private LayoutInflater layoutInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}


	/**
	 * 初始化组件
	 */
	private void initView() {
		//实例化布局对象
		layoutInflater = LayoutInflater.from(this);
		//实例化TabHost对象，得到TabHost
		mTabHost = (CustomFragmentTabHost) findViewById(R.id.tabhost);
		//设置替换布局
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		//去掉分割线
		mTabHost.getTabWidget().setDividerDrawable(null);
		//得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {

			//为每一个Tab按钮设置图标、文字和内容
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			//将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}


		mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String s) {
				Log.e("什么鬼", s);
			}
		});
	}
	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);


		return view;
	}

}
