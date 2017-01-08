package com.example.dontai_add_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

/**
 * Created by GYX on 2017/1/5.
 */
public class MyScrollView extends ScrollView {
	private static final int MAX_X_OVERSCROLL_DISTANCE = 100;
	private Context mContext;
	private int mMaxXOverscrollDistance;

	public MyScrollView(Context context) {
		super(context);
		mContext = context;
		initBounceDistance();
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initBounceDistance();
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		initBounceDistance();
	}

	private void initBounceDistance() {
		final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
		mMaxXOverscrollDistance = (int) (metrics.density * MAX_X_OVERSCROLL_DISTANCE);
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		//这块是关键性代码
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxXOverscrollDistance, isTouchEvent);
	}
}
