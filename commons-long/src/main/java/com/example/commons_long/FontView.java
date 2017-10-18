package com.example.commons_long;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by gyx on 2017/10/17.
 */
public class FontView extends android.support.v7.widget.AppCompatTextView {
	private static final String TEXT = "88＊＊**＊******ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
	private Paint mPaint;// 画笔
	private Paint.FontMetrics mFontMetrics;// 文本测量对象

	public FontView(Context context) {
		this(context, null);
	}

	public FontView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 初始化画笔
		initPaint();
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint() {
		// 实例化画笔
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(50);
		mPaint.setColor(Color.BLACK);

		mFontMetrics = mPaint.getFontMetrics();

		Log.e("Aige", "ascent：" + mFontMetrics.ascent);
		Log.e("Aige", "top：" + mFontMetrics.top);
		Log.e("Aige", "leading：" + mFontMetrics.leading);
		Log.e("Aige", "descent：" + mFontMetrics.descent);
		Log.e("Aige", "bottom：" + mFontMetrics.bottom);
	}

	//Math.abs(mFontMetrics.top)
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(TEXT, 0,getMeasuredHeight()/2+Math.abs((mFontMetrics.ascent+mFontMetrics.descent)/2),mPaint);
		canvas.drawLine(0,getMeasuredHeight()/2,getMeasuredWidth(),getMeasuredHeight()/2,mPaint);
		canvas.drawLine(0,getMeasuredHeight()/2+Math.abs((mFontMetrics.ascent+mFontMetrics.descent)/2),getMeasuredWidth(),getMeasuredHeight()/2+Math.abs((mFontMetrics.ascent+mFontMetrics.descent)/2),mPaint);
		canvas.drawLine(0,getMeasuredHeight()/2+Math.abs((mFontMetrics.ascent+mFontMetrics.descent)/2)-Math.abs(mFontMetrics.ascent),getMeasuredWidth(),getMeasuredHeight()/2+Math.abs((mFontMetrics.ascent+mFontMetrics.descent)/2)-Math.abs(mFontMetrics.ascent),mPaint);
	}
}

