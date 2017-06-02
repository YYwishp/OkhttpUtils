package com.example.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by GYX on 2017/5/25.
 */
public class MyText extends android.support.v7.widget.AppCompatTextView {
	public MyText(Context context) {
		super(context);
	}

	public MyText(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
}
