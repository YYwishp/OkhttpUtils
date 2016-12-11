package com.example.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyToast.toast(this,"sdfgsdfsdfsdfasdsdfsdfsdfasdfasdfasdfasdfasdfsadfasdfasdfasdfsadfsdfasdfsdfsadfsadfasdfsadfsadfsdf");
	}
}
