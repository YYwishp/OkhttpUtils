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
public class MyDrawTextOnPathView extends View {
	private Paint mPaint;
	public MyDrawTextOnPathView(Context context) {
		this(context, null);
	}

	public MyDrawTextOnPathView(Context context, @Nullable AttributeSet attrs) {
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
		mPaint.setStrokeWidth(0);
		//
//		mPaint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画布背景颜色
		canvas.drawRGB(150,150, 150);
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

		//依据路径写出文字
		String text="风萧萧兮易水寒，壮士一去兮不复返,---逆时针";
		String text1="风萧萧兮易水寒，壮士一去兮不复返, ---顺时针";
		mPaint.setColor(Color.BLACK);
		mPaint.setTextSize(25);
		canvas.drawTextOnPath(text, CCWRectpath, 0, 18, mPaint);//逆时针生成
		canvas.drawTextOnPath(text1, CWRectpath, 0, 18, mPaint);//顺时针生成

	}
}
