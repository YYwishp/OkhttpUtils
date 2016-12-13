package com.example.arraylisttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

	private LinearLayout activityMain;
	private SuperSwipeRefreshLayout swipeLayout;
	private ListView listview;
	private ImageView emptyView;
	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<Object> strings = new ArrayList<Object>();
		strings.add("11111");
		strings.add(true);
		Toast.makeText(this,"选中的是什么"+ strings.get(0),Toast.LENGTH_SHORT).show();
		Toast.makeText(this,"选中的是什么"+ strings.get(1),Toast.LENGTH_SHORT).show();
/*

		activityMain = (LinearLayout) findViewById(R.id.activity_main);
		//swipeLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_layout);
		listview = (ListView) findViewById(R.id.listview);
		emptyView = (ImageView) findViewById(R.id.empty_view);
		listview.setEmptyView(emptyView);
		list = new ArrayList<>();
		for (int n=0;n<50;n++) {
			//list.add("1");

		}
		listview.setAdapter(new MyAdapter());*/
	}

	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int i) {
			return list.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			View inflate = View.inflate(MainActivity.this, R.layout.item_lsit, null);
			TextView viewById = (TextView) inflate.findViewById(R.id.text);


			return inflate;
		}
	}
}
