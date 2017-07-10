package com.example.elasticlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 弹性的listVIew
 */
public class MainActivity extends AppCompatActivity {
	private ElasticListView elstic_listview;

	private ArrayList<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = new ArrayList<>();
		for (int n=0;n<5;n++) {
			list.add("第" + n);
		}
		elstic_listview = (ElasticListView)findViewById(R.id.elstic_listview);
		MyAdapter myAdapter = new MyAdapter();
		elstic_listview.setAdapter(myAdapter);
	}
	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public String getItem(int i) {
			return list.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			View inflate = View.inflate(MainActivity.this, R.layout.item_list, null);
			TextView textView = (TextView) inflate.findViewById(R.id.text);
			textView.setText(getItem(i));
			if (list.get(i).equals("第2")) {
				TextView textView1 = new TextView(MainActivity.this);
				//textView1.setText("lalalala");
				return textView1;
			}
			return inflate;
		}


	}
}
