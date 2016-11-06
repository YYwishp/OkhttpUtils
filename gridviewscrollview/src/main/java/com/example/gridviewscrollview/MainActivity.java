package com.example.gridviewscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private GridView myGridview;
	private TextView text;
	private ArrayList<String> list;
	private MyAdapter myAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		text = (TextView) findViewById(R.id.text);
		myGridview = (GridView) findViewById(R.id.gridview);
		list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		myAdapter = new MyAdapter();
		myGridview.setAdapter(myAdapter);
		myGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LogUtil.e("点击了"+id);

				list.remove(position);
				myAdapter.notifyDataSetChanged();
			}
		});
	}

	public void add(View view) {
		list.add("1");
		myAdapter.notifyDataSetChanged();
	}

	public void delete(View view) {
		list.remove(1);
		myAdapter.notifyDataSetChanged();
	}

	class MyAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = View.inflate(MainActivity.this, R.layout.test, null);
			TextView text= (TextView) inflate.findViewById(R.id.text);
			text.setText(list.get(position));
			return inflate;
		}
	}
}
