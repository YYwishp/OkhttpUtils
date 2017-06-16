package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/16.
 */
public class MyRoundRectView extends View {
	private Paint mPaint;
	public MyRoundRectView(Context context) {
		this(context, null);
	}

	public MyRoundRectView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

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
		mPaint.setStyle(Paint.Style.FILL);
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
		canvas.drawRGB(255, 180, 120);
		//矩形
		RectF rect = new RectF(100, 10, 300, 100);
		/**
		 * void drawRoundRect (RectF rect, float rx, float ry, Paint paint)

		 参数：
		 RectF rect:要画的矩形
		 float rx:生成圆角的椭圆的X轴半径
		 float ry:生成圆角的椭圆的Y轴半径
		 */
		canvas.drawRoundRect(rect, 20, 20, mPaint);

	}



}
