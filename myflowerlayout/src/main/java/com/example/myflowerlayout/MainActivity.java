package com.example.myflowerlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerlayout.view.FlowLayout;
import com.example.myflowerlayout.view.FlowerLayout;
import com.example.myflowerlayout.view.FlowerLayout1;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private ScrollView scroll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		
		scroll = (ScrollView) findViewById(R.id.scroll);
		FlowLayout flowerLayout1 = new FlowLayout(this);
		flowerLayout1.setPadding(20,20,20,20);
		ArrayList<String> list = new ArrayList<>();
		list.add("阿斗的放");
		list.add("阿斗erefsdgdfgxc的放");
		list.add("阿斗的dfsdfsdfsd放");
		list.add("阿斗的放");
		list.add("阿斗的sdfsdafgdsfg34534534放");
		list.add("阿斗dfgdfg的放");
		list.add("阿斗的sdfsdf放");
		list.add("xczvzxcvzxcvzxcv");
		list.add("erre");
		list.add("阿斗的zvxcvzxcv放");
		list.add("12");
		list.add("tyrt");
		list.add("vvb");
		
		

		for (int i = 0; i < list.size(); i++) {
			View inflate = View.inflate(this, R.layout.text_layout, null);
			final TextView textView = (TextView) inflate.findViewById(R.id.text);
//			TextView textView= new TextView(this);
			textView.setBackgroundColor(Color.BLUE);
			textView.setText(list.get(i));
			
			textView.setGravity(Gravity.CENTER);
			textView.setTextColor(Color.WHITE);
			flowerLayout1.addView(inflate);
			
			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					v.setVisibility(View.INVISIBLE);
					TextView v1 = (TextView) v;
					Toast.makeText(MainActivity.this, "v1.getText():" + v1.getText(), Toast.LENGTH_SHORT).show();
				}
			});
			
		}
		scroll.addView(flowerLayout1);
	}
}
