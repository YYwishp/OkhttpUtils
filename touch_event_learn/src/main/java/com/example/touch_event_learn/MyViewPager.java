package com.example.touch_event_learn;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by GYX on 2017/6/7.
 */
public class MyViewPager extends ViewPager {
	public MyViewPager(Context context) {
		super(context);
	}
	
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("MyViewPager", "onInterceptTouchEvent"+ev.getActionMasked());
		switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_HOVER_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				break;
		}
		return super.onInterceptTouchEvent(ev);
		
//		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		Log.e("MyViewPager", "onTouchEvent"+ev.getActionMasked());
		return super.onTouchEvent(ev);
		
		
//		return false;
	}
	
}
