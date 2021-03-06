package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
		mPaint.setStyle(Paint.Style.STROKE);
		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.RED);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
		mPaint.setStrokeWidth(2);
		//
//		mPaint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画布背景颜色
		canvas.drawRGB(255, 180, 120);
		/**
		 *
		 void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
		 void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)

		 这里有两个构造函数，部分参数说明如下：
		 第一个构造函数：可以定制每个角的圆角大小：
		 float[] radii：必须传入8个数值，分四组，分别对应每个角所使用的椭圆的横轴半径和纵轴半径，
		 如｛x1,y1,x2,y2,x3,y3,x4,y4｝，其中，x1,y1对应第一个角的（左上角）用来产生圆角的椭圆的横轴半径和纵轴半径，其它类推……
		 第二个构造函数：只能构建统一圆角大小
		 float rx：所产生圆角的椭圆的横轴半径；
		 float ry：所产生圆角的椭圆的纵轴半径；

		 */




		Path path = new Path();
		RectF rect1 =  new RectF(50, 50, 240, 200);
		path.addRoundRect(rect1, 10, 15 , Path.Direction.CCW);

		RectF rect2 =  new RectF(290, 50, 480, 200);
		float radii[] ={10,15,20,25,30,35,40,45};
		path.addRoundRect(rect2, radii, Path.Direction.CCW);

		canvas.drawPath(path, mPaint);

	}



}
