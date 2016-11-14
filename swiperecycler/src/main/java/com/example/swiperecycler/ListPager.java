package com.example.swiperecycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * =======================================
 * -订单页面
 * <p>
 * Created by GYX on 2016/11/2.
 */
public class ListPager extends BasePager {
	public ListPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		TextView textView = new TextView(mContext);
		textView.setText("订单页面");
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
