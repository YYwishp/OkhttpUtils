package com.example.fragmentchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GYX on 2016/12/20.
 */
public class Fragment1 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.e("Fragment1","onCreateView");
		return inflater.inflate(R.layout.fragment1, container, false);

	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("Fragment1","onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e("Fragment1","onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e("Fragment1","onResume");
	}
}