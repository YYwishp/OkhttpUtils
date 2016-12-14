package com.example.navigationtest;

import android.view.View;
import android.widget.TextView;

/**
 * 设置
 * @author Administrator
 *
 */
public class SettingFragment extends BaseFragment {

	@Override
	public View initview() {
		TextView textView = new TextView(getActivity());
		textView.setText("设置");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
