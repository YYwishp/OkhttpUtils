package com.example.dontai_add_view;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends Activity {

	private LinearLayout linear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {

		linear = (LinearLayout) findViewById(R.id.linear);
		linear.setOrientation(LinearLayout.HORIZONTAL);

		for (int n = 0; n<3;n++) {
			SimpleDraweeView img = new SimpleDraweeView(this);
			GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
			GenericDraweeHierarchy hierarchy = builder

					//设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
					.setRoundingParams(RoundingParams.asCircle())
					.setPlaceholderImage(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))

					.build();
			img.setHierarchy(hierarchy);
			img.setImageURI("http://static.lifemenu.net:8181/1/123.jpg");

			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200,200);
			lp.leftMargin = 50;

			img.setLayoutParams(lp);
			linear.addView(img);
		}



		/*linear.addView(img);
		linear.addView(img);*/
	}


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

	private int calculateDpToPx(int padding_in_dp){
		final float scale = getResources().getDisplayMetrics().density;
		return  (int) (padding_in_dp * scale + 0.5f);
	}
}
