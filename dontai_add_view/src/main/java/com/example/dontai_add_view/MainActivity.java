package com.example.dontai_add_view;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends Activity {
	private LinearLayout linear;
	private LinearLayout linHorizontal;
	private TextView text;

	private SimpleDraweeView imgHeader;
	private TextView textname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		linear = (LinearLayout) findViewById(R.id.linear);
		linear.setOrientation(LinearLayout.VERTICAL);

		//


		for (int i = 0; i < 12; i++) {
			View inflate = LayoutInflater.from(this).inflate(R.layout.item_test,null);
			text = (TextView)  inflate.findViewById(R.id.text);
			linHorizontal = (LinearLayout) inflate.findViewById(R.id.lin_horizontal);

			for (int n = 0; n < 6; n++) {
				View inflate2 = LayoutInflater.from(this).inflate(R.layout.item_test2, null);

				imgHeader = (SimpleDraweeView) inflate2.findViewById(R.id.img_header);
				textname = (TextView) inflate2.findViewById(R.id.textname);
				imgHeader.setImageURI("");
//				imgHeader.setImageURI("http://www.91relax.com/images/activity/1_2.5.0.png");
				//picture=http://www.91relax.com/images/activity/1_2.5.0.png

				imgHeader.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
				linHorizontal.addView(inflate2);
			}

			linear.addView(inflate);

		}

	}
	//纯代码写的
	/*private void initView() {

		linear = (LinearLayout) findViewById(R.id.linear);
		linear.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < 12; i++) {
			BouncyHScrollView horizontalScrollView = new BouncyHScrollView(this);
			horizontalScrollView.setHorizontalScrollBarEnabled(false);
			LinearLayout linearVertical = new LinearLayout(this);
			linearVertical.setOrientation(LinearLayout.VERTICAL);
			LinearLayout linearHorizontal = new LinearLayout(this);
			linearHorizontal.setOrientation(LinearLayout.HORIZONTAL);
			for (int n = 0; n < 6; n++) {
				SimpleDraweeView img = new SimpleDraweeView(getApplicationContext());
				GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getApplicationContext().getResources());
				GenericDraweeHierarchy hierarchy = builder
						//设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
						//.setRoundingParams(RoundingParams.asCircle())
						.setPlaceholderImage(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
						.build();
				img.setHierarchy(hierarchy);
				img.setImageURI("http://static.lifemenu.net:8181/1/123.jpg");
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200);
				lp.leftMargin = 50;
				img.setLayoutParams(lp);
				//

				linearHorizontal.addView(img);
				final int finalN = n;
				img.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast.makeText(MainActivity.this, "这是第" + finalN + "个", Toast.LENGTH_SHORT).show();
					}
				});
			}
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			TextView textView = new TextView(this);
			textView.setText("这是第"+i+"行");
			textView.setLayoutParams(layoutParams);
			//
			horizontalScrollView.addView(linearHorizontal);
			linearVertical.addView(textView);
			linearVertical.addView(horizontalScrollView);
			linear.addView(linearVertical);
		}









	}*/

	/*private View createView2(String txt){
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		View view = LayoutInflater.from(this).inflate(R.layout.view_item, null);//也可以从XML中加载布局

		view.setLayoutParams(lp);//设置布局参数

		TextView tv1 = (TextView) view.findViewById(R.id.tv_1);
		TextView tv2 = (TextView) view.findViewById(R.id.tv_2);
		tv1.setText("姓名: ");
		tv2.setText(txt);

		return view;
	}*/
	private View createView(String txt) {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		// View view =LayoutInflater.from(this).inflate(R.layout.view_item, null);//也可以从XML中加载布局
		LinearLayout view = new LinearLayout(this);
		view.setLayoutParams(lp);//设置布局参数
		view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
		//定义子View中两个元素的布局
		ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView tv1 = new TextView(this);
		TextView tv2 = new TextView(this);
		tv1.setLayoutParams(vlp);//设置TextView的布局
		tv2.setLayoutParams(vlp2);
		tv1.setText("姓名: ");
		tv2.setText(txt);
		tv2.setPadding(calculateDpToPx(50), 0, 0, 0);//设置边距
		view.addView(tv1);//将TextView 添加到子View 中
		view.addView(tv2);//将TextView 添加到子View 中
		return view;
	}

	private int calculateDpToPx(int padding_in_dp) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (padding_in_dp * scale + 0.5f);
	}
}
