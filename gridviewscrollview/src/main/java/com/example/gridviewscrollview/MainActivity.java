package com.example.gridviewscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private GridView myGridview;
	private TextView text;
	private ArrayList<String> list;
	private MyAdapter myAdapter;
	private SuperSwipeRefreshLayout swipeLayout;
	// Header View
	private ProgressBar progressBar;
	private TextView textView;
	private ImageView imageView;
	// Footer View
	private ProgressBar footerProgressBar;
	private TextView footerTextView;
	private ImageView footerImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		text = (TextView) findViewById(R.id.text);
		myGridview = (GridView) findViewById(R.id.gridview);


		swipeLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_layout);
		swipeLayout.setHeaderViewBackgroundColor(0xfff6f7fa);
		swipeLayout.setHeaderView(createHeaderView());// add headerView
		swipeLayout.setFooterView(createFooterView());
		swipeLayout.setTargetScrollWithLayout(true);//可以和手指一起滑动
		swipeLayout.setOnPullRefreshListener(onPullRefreshListener);
		swipeLayout.setOnPushLoadMoreListener(onPushLoadMoreListener);
		list = new ArrayList<>();
		list.add("1");
		list.add("1");
		list.add("1");
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
	//下拉刷新
	private SuperSwipeRefreshLayout.OnPullRefreshListener onPullRefreshListener= new SuperSwipeRefreshLayout.OnPullRefreshListener(){
		@Override
		public void onRefresh() {
			textView.setText("正在刷新");
			imageView.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			//刷新数据
			//requestData(page);


		}

		@Override
		public void onPullDistance(int distance) {
		}

		@Override
		public void onPullEnable(boolean enable) {
			textView.setText(enable ? "松开刷新" : "下拉刷新");
			imageView.setVisibility(View.VISIBLE);
			imageView.setRotation(enable ? 180 : 0);
		}
	};

	//上拉加载
	private SuperSwipeRefreshLayout.OnPushLoadMoreListener onPushLoadMoreListener = new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {
		@Override
		public void onLoadMore() {

			footerTextView.setText("正在加载...");
			footerImageView.setVisibility(View.GONE);
			footerProgressBar.setVisibility(View.VISIBLE);

			for (int n = 0;n<3;n++) {
				list.add("n");

			}
			myAdapter.notifyDataSetChanged();
			footerImageView.setVisibility(View.VISIBLE);
			footerProgressBar.setVisibility(View.GONE);
			swipeLayout.setLoadMore(false);

		}

		@Override
		public void onPushDistance(int distance) {
		}

		@Override
		public void onPushEnable(boolean enable) {
			footerTextView.setText(enable ? "松开加载" : "上拉加载");
			footerImageView.setVisibility(View.VISIBLE);
			footerImageView.setRotation(enable ? 0 : 180);
		}
	};
	private View createFooterView() {
		View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer, null);
		footerProgressBar = (ProgressBar) footerView.findViewById(R.id.footer_pb_view);
		footerImageView = (ImageView) footerView.findViewById(R.id.footer_image_view);
		footerTextView = (TextView) footerView.findViewById(R.id.footer_text_view);
		footerProgressBar.setVisibility(View.GONE);
		footerImageView.setVisibility(View.VISIBLE);
		footerImageView.setImageResource(R.drawable.down_arrow);
		footerTextView.setText("上拉加载更多...");
		return footerView;
	}

	private View createHeaderView() {
		View headerView = LayoutInflater.from(this).inflate(R.layout.layout_head, null);
		progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
		textView = (TextView) headerView.findViewById(R.id.text_view);
		textView.setText("下拉刷新");
		imageView = (ImageView) headerView.findViewById(R.id.image_view);
		imageView.setVisibility(View.VISIBLE);
		imageView.setImageResource(R.drawable.down_arrow);
		progressBar.setVisibility(View.GONE);
		return headerView;
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
