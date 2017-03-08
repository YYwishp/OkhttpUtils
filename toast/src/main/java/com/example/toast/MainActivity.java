package com.example.toast;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{
	private GestureDetector gestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyToast.toast(this,"sdfgsdfsdfsdfasdsdfsdfsdfasdfasdfasdfasdfasdfsadfasdfasdfasdfsadfsdfasdfsdfsadfsadfasdfsadfsadfsdf");


		ArrayList <Bean> list1 =new ArrayList<Bean>();
		list1.add(new Bean(10,"男"));
		list1.add(new Bean(11,"男"));
		list1.add(new Bean(12,"男"));
		list1.add(new Bean(13,"男"));


		ArrayList <Bean> list2 =new ArrayList<Bean>();

		list2.add(new Bean(10,"男"));
		list2.add(new Bean(11,"男"));

		list1.removeAll(list2);
		for (int n = 0;n<list1.size();n++) {
			Log.e("测试",list1.get(n).getAge()+"---"+list1.get(n).getName());
		}
		gestureDetector = new GestureDetector(this, (GestureDetector.OnGestureListener) this);



	}

	int startY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				//开始坐标
				startY = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				int newY = (int) event.getRawY();
				int dexY = newY - startY;
				if (dexY > 10) {
					//手指向下滑
					Log.e("测试", "向下滑动-----");
				} else if (dexY < -10) {
					//手指向上滑
					Log.e("测试", "向上滑动-----");
				}
				break;
			case MotionEvent.ACTION_UP:

				break;
		}
		return false;*/
		// 将该Activity上的触碰事件交给GestureDetector处理
		return gestureDetector.onTouchEvent(event);
	}



	@Override
	public boolean onDown(MotionEvent e) {
		// 触碰时间按下时触发该方法
		Log.e("---", "按下");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// 当用户在触摸屏幕上按下、而且还未移动和松开时触发该方法
		Log.e("---", "按下未移动");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// 在屏幕上的轻击事件将会触发该方法
		Log.e("---", "轻击事件");
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// 当屏幕“滚动”时触发该方法
		Log.e("---", "屏幕滚动");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// 当用户在屏幕上长按时触发该方法
		Log.e("---", "长按");
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// 当用户在屏幕上“拖动”时触发该方法
		Log.e("---", "拖动");
		return false;
	}
}
