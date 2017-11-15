package com.example.myflowerlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerlayout.view.FlowLayout;
import com.example.myflowerlayout.view.FlowLayout_1;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {
	private LinearLayout scroll;
	private LinearLayout linear1;
	private ArrayList<String> strings;
	private FlowLayout flowerLayout2;
	private Button btButton;
	private TextView textView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scroll = (LinearLayout) findViewById(R.id.scroll);
		linear1 = (LinearLayout) findViewById(R.id.linear_1);
	
		btButton = (Button) findViewById(R.id.bt_button);
		textView = (TextView) findViewById(R.id.text);

		FlowLayout_1 flowerLayout1 = new FlowLayout_1(this);
		flowerLayout1.setPadding(20, 20, 20, 20);
		ArrayList<String> list = new ArrayList<>();
		list.add("111");
		list.add("222");
		list.add("333");
		list.add("444");
		list.add("555");
		list.add("666");
		list.add("777");
		list.add("888");
		list.add("999");
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		strings = new ArrayList<>();
		
		final FlowLayout_1 flowerLayout2 = new FlowLayout_1(MainActivity.this);
		flowerLayout2.setPadding(20, 20, 20, 20);

		//====切换语言
		Locale _UserLocale1 = LocaleUtils.getCurrentLocale(this);
		switch (_UserLocale1.getLanguage()) {
			case "ar":
				flowerLayout2.setArCountry(true);
				break;
			default:
				flowerLayout2.setArCountry(false);
				break;
		}
		Locale _UserLocale = LocaleUtils.getUserLocale(this);
		if (null != _UserLocale) {
			switch (_UserLocale.getLanguage()) {
				case "ar":
					flowerLayout2.setArCountry(true);
					break;
				default:
					flowerLayout2.setArCountry(false);
					break;
			}
		}






		for ( int i = 0; i < list.size(); i++) {
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
				public void onClick(final View firstView) {
					firstView.setVisibility(View.INVISIBLE);
					TextView v1 = (TextView) firstView;
					Toast.makeText(MainActivity.this, "v1.getText():" + v1.getText(), Toast.LENGTH_SHORT).show();
					strings.clear();
					strings.add(v1.getText().toString());
					//
					
					final View inflate_1 = View.inflate(MainActivity.this, R.layout.text_layout, null);
					final TextView textView_1 = (TextView) inflate_1.findViewById(R.id.text);
					textView_1.setBackgroundColor(Color.GREEN);
					textView_1.setText(strings.get(0));
					textView_1.setGravity(Gravity.CENTER);
					textView_1.setTextColor(Color.WHITE);
					flowerLayout2.addView(inflate_1);
					textView_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							TextView textView = (TextView) view;
							//strings.remove(textView.getText().toString());
							inflate_1.setVisibility(View.GONE);
							flowerLayout2.removeView(inflate_1);
							firstView.setVisibility(View.VISIBLE);
						}
					});
					linear1.removeAllViews();
					linear1.addView(flowerLayout2);
					
				}
			});
		}
		scroll.addView(flowerLayout1);
		
		
		btButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				textView.setText("");
				int childCount = flowerLayout2.getChildCount();
				for (int i = 0; i <childCount; i++) {
					View childAt = flowerLayout2.getChildAt(i);
					TextView textView_child = (TextView) childAt.findViewById(R.id.text);
					textView.append(textView_child.getText().toString()+"-");
				}
			}
		});
	}
	
	
	
	
}
