package com.example.refresh_module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button btListview;
	private Button btRecyclerview;
	private Button btViewpager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		initView();
	}

	private void initView() {


		btListview = (Button) findViewById(R.id.bt_listview);
		btRecyclerview = (Button) findViewById(R.id.bt_recyclerview);

		btViewpager = (Button) findViewById(R.id.bt_viewpager);

		btListview.setOnClickListener(this);
		btViewpager.setOnClickListener(this);
		btRecyclerview.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_listview:

				startActivity(new Intent(this,ListViewActivity.class));


				break;
			case R.id.bt_recyclerview:

				startActivity(new Intent(this,RecyclerViewActivity.class));

				break;

			case R.id.bt_viewpager:




				break;
		}

	}
}
