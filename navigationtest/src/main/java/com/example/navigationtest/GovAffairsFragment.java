package com.example.navigationtest;

import android.view.View;
import android.widget.TextView;

/**
 * 政务指南
 * @author Administrator
 *
 */
public class GovAffairsFragment extends BaseFragment {

	@Override
	public View initview() {
		TextView textView = new TextView(getActivity());
		textView.setText("政务指南");
		return textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
