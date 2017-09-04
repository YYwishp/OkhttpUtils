package com.example.refresh_module;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
	private ArrayList<String> list;
	private TwinklingRefreshLayout refresh;
	private RecyclerView recyclerview;
	private MyAdapter myAdapter;
	private int n=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler_view);


		initView();
	}

	private void initView() {

		refresh = (TwinklingRefreshLayout) findViewById(R.id.refresh);
		recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
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
		myAdapter = new MyAdapter(this, list);
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		recyclerview.setAdapter(myAdapter);

		refresh.setOnRefreshListener(new RefreshListenerAdapter() {
			@Override
			public void onRefresh(TwinklingRefreshLayout refreshLayout) {
				super.onRefresh(refreshLayout);
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


	class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
		private final LayoutInflater mInflate;
		private  Context mContext;
		private  List<String> mDatas;
		public MyAdapter(Context context, List<String> datas) {

			this.mDatas = datas;
			this.mContext = context;
			mInflate = LayoutInflater.from(context);


		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = mInflate.inflate(R.layout.item_recycler, parent, false);

			MyViewHolder myViewHolder = new MyViewHolder(view);
			return myViewHolder;


		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.textview.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		TextView textview;

		public MyViewHolder(View itemView) {
			super(itemView);
			textview = (TextView) itemView.findViewById(R.id.tv);

		}
	}
}
