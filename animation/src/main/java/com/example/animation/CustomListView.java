package com.example.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * ======================================
 * -兼容scrollView嵌套的Listview
 * Created by GYX on 2016/11/16.
 */
public class CustomListView extends ListView {
	public CustomListView(Context context) {
		super(context);
	}

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
