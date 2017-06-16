package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/5/25.
 */
public class CustomViewLine extends View {
	private Paint mPaint;

	public CustomViewLine(Context context) {
		this(context, null);
	}

	/**
	 * 要在xml中使用必须要有这个构造方法
	 *
	 * @param context
	 * @param attrs
	 */
	public CustomViewLine(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		//因为draw或layout的过程有可能是一个频繁重复执行的过程,避免浪费内存】
		initPaint();
	}

	private void initPaint() {
		// 实例化画笔并打开抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		/*
	   * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.RED);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
		mPaint.setStrokeWidth(10);
		//
//		mPaint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		//设置画布背景颜色
		canvas.drawRGB(255, 255,255);
		/**
		 * startX:开始点X坐标
		 startY:开始点Y坐标
		 stopX:结束点X坐标
		 stopY:结束点Y坐标
		 */
		canvas.drawLine(100, 100, 250, 250, mPaint);
	}
}
