package com.example.toast;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by GYX on 2017/3/8.
 */
public class MyViewGroup_2 extends FrameLayout {
	public MyViewGroup_2(Context context) {
		super(context);
	}

	public MyViewGroup_2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyViewGroup_2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}



	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("-----","ViewGroup_2-----dispatchTouchEvent---分发");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("-----","ViewGroup_2-----onInterceptTouchEvent---拦截");
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.e("-----","ViewGroup_2-----onTouchEvent---触摸");
		return super.onTouchEvent(event);
	}
}
