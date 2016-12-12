package com.example.twofinish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		AppManager2.getAppManager().addActivity(this);
		findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
		findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AppManager2.getAppManager().finishAllActivity();
			}
		});
	}
}
