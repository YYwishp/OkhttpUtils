package com.example.touch_event_learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GYX on 2017/6/7.
 */
public class Fragment_2 extends Fragment {
	private ListView listview;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.fragment_2,container,false);


		listview = (ListView) inflate.findViewById(R.id.listview);
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i <40; i++) {

			list.add("222222");
		}
		MyAdapter myAdapter = new MyAdapter(list);
		listview.setAdapter(myAdapter);
		return inflate;

	}
	@Override
	public void setMenuVisibility(boolean menuVisible) {
		//super.setMenuVisibility(menuVisible);
	}

	class MyAdapter extends BaseAdapter {
		private  ArrayList<String> list;

		public MyAdapter(ArrayList<String> list) {
			this.list = list;
		}

		private TextView text;
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
			View inflate = View.inflate(getContext(), R.layout.item_list, null);


			text = (TextView) inflate.findViewById(R.id.text);
			text.setText(list.get(position));
			return inflate;
		}
	}
}
