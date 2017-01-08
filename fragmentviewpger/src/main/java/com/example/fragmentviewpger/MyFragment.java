package com.example.fragmentviewpger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by GYX on 2016/12/5.
 */
public class MyFragment extends Fragment {

	int mNum; //页号
	public static MyFragment newInstance(int num) {
		MyFragment fragment = new MyFragment();
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//这里我只是简单的用num区别标签，其实具体应用中可以使用真实的fragment对象来作为叶片
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}
	/**为Fragment加载布局时调用**/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_pager_list, null);
		TextView tv = (TextView) view.findViewById(R.id.text);
		tv.setText("fragmentdfsdfsdfsdfsdfsdfsd");
		return view;
	}
}
