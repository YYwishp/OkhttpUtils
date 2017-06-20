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
 * Created by GYX on 2017/6/19.
 */
public class MyAddArc extends View {

	private Paint mPaint;
	public MyAddArc(Context context) {
		this(context, null);
	}

	public MyAddArc(Context context, @Nullable AttributeSet attrs) {
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
		canvas.drawRGB(200, 60, 200);
		/**
		 *
		 *
		 *
		 void addArc (RectF oval, float startAngle, float sweepAngle)

		 参数：
		 RectF oval：弧是椭圆的一部分，这个参数就是生成椭圆所对应的矩形；
		 float startAngle：开始的角度，X轴正方向为0度
		 float sweepAngel：持续的度数；

		 */




		Path path = new Path();
		RectF rectF = new RectF(100, 50, 300, 150);
		path.addArc(rectF, 0, 135);
		canvas.drawPath(path,mPaint);

	}





}
