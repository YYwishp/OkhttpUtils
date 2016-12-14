package com.example.navigationtest;

import android.view.View;
import android.widget.TextView;

/**
 * 新闻中心
 * @author Administrator
 *
 */
public class NewsCenterFragment extends BaseFragment {

	@Override
	public View initview() {
		TextView textView = new TextView(getActivity());
		textView.setText("新闻中心");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
