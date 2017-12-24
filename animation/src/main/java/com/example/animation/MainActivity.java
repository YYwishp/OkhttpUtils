package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * View动画
 */
public class MainActivity extends Activity {
	private final int finalHeight = 0;
	private ImageView imgLogo;
	private Button button;
	private int height;
	private int measuredHeight;
	private int realHeight;
	private CustomListView listview;
	private ImageView img2;
	//private TwinklingRefreshLayout refreshLayout;
	private ArrayList<String> strings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		imgLogo = (ImageView) findViewById(R.id.img_logo);
		img2 = (ImageView) findViewById(R.id.img2);
		//refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
		listview = (CustomListView) findViewById(R.id.listview);
		//
		strings = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			strings.add("我是第" + i);
		}
		final MyListViewAdapter adapter = new MyListViewAdapter();
		listview.setAdapter(adapter);
//		refreshLayout.setOverScrollRefreshShow(true);
//		refreshLayout.setOverScrollBottomShow(true);
		listview.setFocusable(false);
		//scroll_gym.smoothScrollTo(0, 0);
		
	/*	refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
			@Override
			public void onRefresh(TwinklingRefreshLayout refreshLayout1) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						
						
						
						strings.clear();
						for (int i = 0; i < 20; i++) {
							strings.add("I am " + i);
						}
						adapter.notifyDataSetChanged();
						refreshLayout.finishRefreshing();
					}
				}, 1000);
			}
			
			@Override
			public void onLoadMore(final TwinklingRefreshLayout refreshLayout1) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						strings.add("我是增加的");
						strings.add("我是增加的");
						strings.add("我是增加的");
						strings.add("我是增加的");
						strings.add("我是增加的");
						strings.add("我是增加的");
						
						adapter.notifyDataSetChanged();
						
						
						
						
						refreshLayout.finishLoadmore();
					}
				}, 1000);
			}
		});*/
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		measuredHeight = imgLogo.getMeasuredHeight();
		ViewGroup.LayoutParams layoutParams = imgLogo.getLayoutParams();
		height = layoutParams.height;
		realHeight = imgLogo.getHeight();
	/*	img2.layout(0,0,400,500);
		img2.invalidate();*/
	}
	
	public void startAnimation(View view) {
		Log.e("tag", "measuredHeight" + measuredHeight);
		Log.e("tag", "layoutParamsheight" + height);
		Log.e("tag", "realHeight" + realHeight);
		HiddenAnimUtils.newInstance(this, imgLogo, button, realHeight).toggle();
	}
	
	public void startAnimationY(View view) {
		/*if (imgLogo.getVisibility() == View.VISIBLE) {
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgLogo, "translationY", 0.0f, -realHeight);
			imgLogo.setPivotX(0);
			imgLogo.setPivotY(0);
			imgLogo.invalidate();
			scaleY.setDuration(1000);
			scaleY.start();
			scaleY.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					imgLogo.setVisibility(View.GONE);
				}
			});
			
		} else {
			
			imgLogo.setVisibility(View.VISIBLE);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgLogo, "translationY", -realHeight, 0.0f);
			imgLogo.setPivotX(0);
			imgLogo.invalidate();
			imgLogo.setPivotY(0);
			scaleY.setDuration(1000);
			scaleY.start();
			scaleY.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					
				}
			});
		}*/
		if (listview.getVisibility() == View.VISIBLE) {
			listview.setVisibility(View.GONE);
		} else {
			listview.setVisibility(View.VISIBLE);
		}
	}
	
	public class MyListViewAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return strings.size();
		}
		
		@Override
		public Object getItem(int position) {
			return strings.get(position);
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = View.inflate(MainActivity.this, R.layout.item, null);
			final TextView text = (TextView) inflate.findViewById(R.id.text);
			final LinearLayout lin = (LinearLayout) inflate.findViewById(R.id.lin);
			
			text.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//HiddenAnimUtils.newInstance(MainActivity.this,lin,text,400).toggle();
					if (lin.getVisibility() == View.GONE) {
						lin.setVisibility(View.VISIBLE);
					} else {
						lin.setVisibility(View.GONE);
					}
				}
			});
			return inflate;
		}
	}
}
