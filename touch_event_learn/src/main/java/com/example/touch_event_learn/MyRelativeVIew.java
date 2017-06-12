package com.example.touch_event_learn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.RelativeLayout;

/**
 * Created by GYX on 2017/6/12.
 */
public class MyRelativeVIew extends RelativeLayout {
	public MyRelativeVIew(Context context) {
		super(context);
	}

	public MyRelativeVIew(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyRelativeVIew(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		ViewParent parent = getParent();
		//true 父类禁止拦截，也就是说父类不会走ontouch方法，侧拉菜单就不会出现
		//false 父类就会拦截，也就是说，父类的ontouch方法会执行，就会拉出侧拉菜单
		parent.requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);


//		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
//		return false;


	}

}
