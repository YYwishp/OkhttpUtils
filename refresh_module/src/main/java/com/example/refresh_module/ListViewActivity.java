package com.example.refresh_module;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
	private TwinklingRefreshLayout refresh;
	private ListView listview;
	private ArrayList<String> list;
	private int n=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);



		initView();


	}

	private void initView() {


		refresh = (TwinklingRefreshLayout) findViewById(R.id.refresh);
		listview = (ListView) findViewById(R.id.listview);
		//刷新头
		SinaRefreshView headerView = new SinaRefreshView(this);
		refresh.setHeaderView(headerView);
		//加载更多
		LoadingView loadingView = new LoadingView(this);
		refresh.setBottomView(loadingView);
		//自动加载更多
		refresh.setAutoLoadMore(true);
		refresh.startRefresh();
		list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(i + "-------");

		}
		final MyAdapter myAdapter = new MyAdapter();
		listview.setAdapter(myAdapter);
		refresh.setOnRefreshListener(new RefreshListenerAdapter() {
			@Override
			public void onRefresh(TwinklingRefreshLayout refreshLayout) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {

						list.clear();
						for (int i = 0; i < 200; i++) {
							list.add(i+"*******");
						}
						myAdapter.notifyDataSetChanged();
						refresh.finishRefreshing();
					}
				}, 2000);




			}

			@Override
			public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
				super.onLoadMore(refreshLayout);
				n++;
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);
				list.add("新数据"+n);

				myAdapter.notifyDataSetChanged();
				refresh.finishLoadmore();

			}
		});
	}






	class MyAdapter extends BaseAdapter{
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
			View inflate = View.inflate(ListViewActivity.this, R.layout.item_list, null);


			text = (TextView) inflate.findViewById(R.id.text);
			text.setText(list.get(position));
			return inflate;
		}
	}
}
