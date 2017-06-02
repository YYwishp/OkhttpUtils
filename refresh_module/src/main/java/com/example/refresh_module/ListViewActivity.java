package com.example.refresh_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

public class ListViewActivity extends AppCompatActivity {
	private TwinklingRefreshLayout refresh;
	private ListView listview;
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
	}






	class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
	}
}
