package com.example.navigationtest;

import android.view.View;
import android.widget.TextView;

/**
 * 智慧服务
 * @author Administrator
 *
 */
public class SmartServiceFragment extends BaseFragment {

	@Override
	public View initview() {
		TextView textView = new TextView(getActivity());
		textView.setText("智慧服务");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
