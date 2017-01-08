package com.example.refreshrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


	private RecyclerRefreshLayout refreshLayout;
	private ListView listview;
	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		refreshLayout = (RecyclerRefreshLayout) findViewById(R.id.refresh_layout);
		listview = (ListView) findViewById(R.id.listview);
		list = new ArrayList<>();

		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		Myadapter myadapter = new Myadapter();
		listview.setAdapter(myadapter);


		refreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {





			}
		});
	}


	public class Myadapter extends BaseAdapter{
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
			View inflate = View.inflate(MainActivity.this, R.layout.item_list, null);
			TextView viewById = (TextView) inflate.findViewById(R.id.text);
			viewById.setText(list.get(i));
			return inflate;
		}
	}
}
