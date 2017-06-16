package com.example.custom_view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.custom_view.R;
import com.example.custom_view.view.CustomViewLine;

public class BaseGeometricActivity extends AppCompatActivity {
	private LinearLayout linearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_geometric);

		initView();
	}

	private void initView() {


		linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
	}
}
