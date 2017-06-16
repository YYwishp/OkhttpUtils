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
public class MyOvalView extends View {


	private Paint mPaint;
	public MyOvalView(Context context) {
		this(context, null);
	}

	public MyOvalView(Context context, @Nullable AttributeSet attrs) {
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
		canvas.drawRGB(150, 180, 120);
		/**
		 * 椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形

		 void drawOval (RectF oval, Paint paint)

		 参数：
		 RectF oval：用来生成椭圆的矩形
		 */
		mPaint.setColor(Color.RED);//更改画笔颜色
		RectF rect = new RectF(100, 10, 300, 100);
		canvas.drawRect(rect, mPaint);//画矩形

		mPaint.setColor(Color.GREEN);//更改画笔颜色
		canvas.drawOval(rect, mPaint);//同一个矩形画椭圆

	}












}
