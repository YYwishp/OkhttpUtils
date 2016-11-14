package com.example.swiperecycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * ================================
 * -个人页面
 * Created by GYX on 2016/11/2.
 */
public class PersonalPager extends BasePager {
	public PersonalPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("个人页面");
		return textView;
	}

	@Override
	public void initData() {
	}

	@Override
	public View getRootView() {
		return mView;
	}
}
