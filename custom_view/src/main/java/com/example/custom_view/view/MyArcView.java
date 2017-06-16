package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/16.
 */
public class MyArcView extends View {

	private Paint mPaint;

	public MyArcView(Context context) {
		this(context, null);
	}

	public MyArcView(Context context, @Nullable AttributeSet attrs) {
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
		mPaint.setStyle(Paint.Style.STROKE);
		// 设置画笔颜色为红色
		mPaint.setColor(Color.RED);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
		mPaint.setStrokeWidth(5);
		//
//		mPaint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画布背景颜色
		canvas.drawRGB(150,250, 120);
		/**
		 * 弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；

		 void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)

		 参数：
		 RectF oval:生成椭圆的矩形
		 float startAngle：弧开始的角度，以X轴正方向为0度
		 float sweepAngle：弧持续的角度,顺时针
		 boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
		 */
		mPaint.setStyle(Paint.Style.STROKE);
		RectF rectF = new RectF(30, 30, 150, 100);
		canvas.drawArc(rectF, -30, 90, true, mPaint);
		RectF rectF1 = new RectF(200, 30, 350, 100);
		canvas.drawArc(rectF1, -30, 90, false, mPaint);
		mPaint.setStyle(Paint.Style.FILL);
		RectF rectF2 = new RectF(30, 200, 150, 230);
		canvas.drawArc(rectF2, -30, 90, true, mPaint);
		RectF rectF3 = new RectF(200, 200, 350, 230);
		canvas.drawArc(rectF3, -30, 90, false, mPaint);

	}






}
