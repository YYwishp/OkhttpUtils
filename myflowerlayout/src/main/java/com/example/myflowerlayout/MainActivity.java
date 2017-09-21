package com.example.myflowerlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myflowerlayout.view.FlowerLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {
	private int paddingPx = 20;
	private RelativeLayout recy;
	private FlowerLayout flower;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FlowerLayout flowerLayout1 = new FlowerLayout(this);
		recy = (RelativeLayout) findViewById(R.id.recy);

		ArrayList<String> list = new ArrayList<>();
		list.add("阿斗的放");
		list.add("78978");
		list.add("阿斗的放");
		list.add("sndfsanssdmnklnkl");
		list.add("密码vb");
		list.add("阿斯顿飞吧");
		list.add("收到了");
		list.add("io品味及欧普");
		list.add("婆婆");
		list.add("sdfsd");
		list.add("蛇魔女");
		list.add("阿斯顿飞林恺伦");

		for (int i = 0; i < list.size(); i++) {
			View inflate = View.inflate(this, R.layout.text_layout, null);
			TextView text = (TextView) inflate.findViewById(R.id.text);
			text.setText(list.get(i));
			text.setTextColor(Color.RED);
			//4,将文字居中,并且设置和背景的内间距
			text.setGravity(Gravity.CENTER);
			text.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

			//没有点击事件,textView ImageView
			text.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				}
			});

		}
		recy.addView(flowerLayout1);
	}
}
