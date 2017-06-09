package com.example.touch_event_learn;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by GYX on 2017/6/9.
 */
public class MyRecyclerView extends RecyclerView {
	public MyRecyclerView(Context context) {
		super(context);
	}

	public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("分发事件", "dispatchTouchEvent");


		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e("分发事件", "按下");
				break;
			case MotionEvent.ACTION_HOVER_MOVE:
				Log.e("分发事件", "移动");
				break;
			case MotionEvent.ACTION_UP:
				Log.e("分发事件", "抬起");
				break;
		}
		return super.dispatchTouchEvent(ev);
//				return false;
//		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.e("拦截", "onInterceptTouchEvent");
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e("拦截", "按下");
				break;
			case MotionEvent.ACTION_HOVER_MOVE:
				Log.e("拦截", "移动");
				break;
			case MotionEvent.ACTION_UP:
				Log.e("拦截", "抬起");
				break;
		}
//		return super.onInterceptTouchEvent(ev);
//		return false;
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.e("触发", "onTouchEvent");
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e("触发", "按下");
				break;
			case MotionEvent.ACTION_HOVER_MOVE:
				Log.e("触发", "移动");
				break;
			case MotionEvent.ACTION_UP:
				Log.e("触发", "抬起");
				break;
		}
//		return super.onTouchEvent(ev);
				return false;
//		return true;
	}

}
