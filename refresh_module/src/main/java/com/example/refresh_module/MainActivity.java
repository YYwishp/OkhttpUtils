package com.example.refresh_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button btListview;
	private Button btRecyclerview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		initView();
	}

	private void initView() {


		btListview = (Button) findViewById(R.id.bt_listview);
		btRecyclerview = (Button) findViewById(R.id.bt_recyclerview);

		btListview.setOnClickListener(this);
		btRecyclerview.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_listview:




				break;
			case R.id.bt_recyclerview:
				break;
		}

	}
}
