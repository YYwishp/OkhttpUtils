package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/16.
 */
public class MyDrawRectView extends View {

	private Paint mPaint;

	public MyDrawRectView(Context context) {
		this(context, null);
	}

	public MyDrawRectView(Context context, @Nullable AttributeSet attrs) {
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
		canvas.drawRGB(150,150, 120);
		/**
		 *
		 void addRect (float left, float top, float right, float bottom, Path.Direction dir)
		 void addRect (RectF rect, Path.Direction dir)

		 这里Path类创建矩形路径的参数与上篇canvas绘制矩形差不多，唯一不同的一点是增加了Path.Direction参数；
		 Path.Direction有两个值：
		 Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
		 Path.Direction.CW： 是clockwise的缩写，指创建顺时针方向的矩形路径；
		 */
		//先创建两个大小一样的路径
		//第一个逆向生成
		Path CCWRectpath = new Path();
		RectF rect1 =  new RectF(50, 50, 240, 200);
		CCWRectpath.addRect(rect1, Path.Direction.CCW);

		//第二个顺向生成
		Path CWRectpath = new Path();
		RectF rect2 =  new RectF(290, 50, 480, 200);
		CWRectpath.addRect(rect2, Path.Direction.CW);

		//先画出这两个路径
		canvas.drawPath(CCWRectpath, mPaint);
		canvas.drawPath(CWRectpath, mPaint);







	}

}
