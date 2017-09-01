package com.example.drawerlayout_test;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
	private DrawerLayout idDrawerLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		idDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
		idDrawerLayout.setScrimColor(Color.TRANSPARENT);
	}
}
