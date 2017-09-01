package com.example.drawerlayout_test;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by GYX on 2017/7/18.
 */
public class MyLayout extends DrawerLayout {
	public MyLayout(Context context) {
		super(context);
	}

	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch(ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				final float x = ev.getX();
				final float y = ev.getY();
				final View touchedView = findTopChildUnder((int) x, (int) y);
				if (touchedView != null && this.isDrawerOpen(GravityCompat.START)) {
					Log.e("不拦截", "----不拦截");
					return false;

				}
				break;

			default:
				break;
		}
		Log.e("正常", "----正常");
		return super.onInterceptTouchEvent(ev);
	}

	boolean isContentView(View child) {
		return ((LayoutParams) child.getLayoutParams()).gravity == Gravity.NO_GRAVITY;
	}
	private View findTopChildUnder(int x, int y) {
		int childCount = getChildCount();
		Log.e("孩子有几个", childCount + "");
		View childAt = getChildAt(childCount - 1);

		return childAt;
	}
}
