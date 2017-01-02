package com.example.fragment_replace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by GYX on 2017/1/2.
 */
public class Fragment3 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_3, container, false);
		TextView viewById = (TextView) inflate.findViewById(R.id.text3);
		viewById.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getContext(), SecondActivity.class);
				startActivity(intent);
			}
		});
		return inflate;
	}
}
