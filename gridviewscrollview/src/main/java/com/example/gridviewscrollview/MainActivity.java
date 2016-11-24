package com.example.gridviewscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");

		myAdapter = new MyAdapter();
		myGridview.setAdapter(myAdapter);
		myGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, "我是 +:" + position, Toast.LENGTH_SHORT).show();
				for (int n = 0; n < list.size(); n++) {
					list.set(n, "1");
				}
				list.set(position, "2");



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
		public RadioButton getRadio() {
			return radio;
		}

		public void setRadio(RadioButton radio) {
			this.radio = radio;
		}

		private RadioButton radio;

		@Override
		public int getCount() {
			LogUtil.e("getCount");
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			LogUtil.e("getItem");
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			LogUtil.e("getItemId");
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = View.inflate(MainActivity.this, R.layout.test, null);
			radio = (RadioButton) inflate.findViewById(R.id.radio);
			String s = list.get(position);
			if (s.equals("1")) {
				radio.setChecked(false);
			} else {
				radio.setChecked(true);
			}
			return inflate;
		}
	}
}
