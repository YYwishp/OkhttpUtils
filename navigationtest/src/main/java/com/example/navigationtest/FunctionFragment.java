package com.example.navigationtest;

import android.view.View;
import android.widget.TextView;

//因为首页,新闻中心等fragment都需要显示界面,填充数据,相同的操作,抽取到父类中
//首页
public class FunctionFragment extends BaseFragment {

	@Override
	public View initview() {
		TextView textView = new TextView(getActivity());
		textView.setText("首页");
		return  textView;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	

}
