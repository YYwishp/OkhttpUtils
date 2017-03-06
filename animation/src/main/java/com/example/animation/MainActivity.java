package com.example.animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * View动画
 */
public class MainActivity extends AppCompatActivity {
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		textview = (TextView) findViewById(R.id.textview);

		textview.setOnTouchListener(new View.OnTouchListener() {

			int startX;
			int startY;

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						//按下的事件
						Log.e("测试","按下....");
						//1.按下控件,记录开始的x和y的坐标
						startX = (int) event.getRawX();
						startY = (int) event.getRawY();
						break;
					case MotionEvent.ACTION_MOVE:
						//移动的事件
						Log.e("测试","移动了....");
						//2.移动到新的位置记录新的位置的x和y的坐标
						//int newX = (int) event.getRawX();
						//int newY = (int) event.getRawY();
						//3.计算移动的偏移量
						//int dX = newX - startX;
						//int dY = newY - startY;
						//4.移动相应的偏移量,重新绘制控件
						//获取的时候原控件距离父控件左边和顶部的距离
//						int l = textview.getLeft();
//						int t = textview.getTop();
//						//获取新的控件的距离父控件左边和顶部的距离
//						//l += dX;
//						//t += dY;
//						int r = l + textview.getWidth();
////						int b = t + textview.getHeight();
//						int b = 20 + textview.getHeight();


						//5.更新开始的坐标
						//tartX = newX;
						//startY = newY;
						break;
					case MotionEvent.ACTION_UP:
						//抬起的事件
					Log.e("测试","抬起了....");
						int newY = (int) event.getRawY();
						//3.计算移动的偏移量
						//int dX = newX - startX;
						int dY = newY - startY;
						if (dY >30) {
							trans(view,0,textview.getTop()+50);
						} else if (dY < -30) {
							trans2(view,0,textview.getTop()-50);
						}
						break;
				}




				return true;
			}
		});


	}

	public void trans(View view,int start ,int end){
		//iv.setTranslationX(translationX);
		int i = view.getHeight() / 3;

		ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationY", start,end);
		oa.setDuration(1000);
//		oa.setRepeatCount(ObjectAnimator.INFINITE);
//		oa.setRepeatMode(ObjectAnimator.REVERSE);
		oa.start();
	}
	public void trans2(View view,int start ,int end){
		int i = view.getHeight() / 3;
		//iv.setTranslationX(translationX);
		ObjectAnimator oa = ObjectAnimator.ofFloat(view, "translationY", start,end);
		oa.setDuration(1000);
//		oa.setRepeatCount(ObjectAnimator.INFINITE);
//		oa.setRepeatMode(ObjectAnimator.REVERSE);
		oa.start();
	}
}
