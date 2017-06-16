package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/15.
 */
public class MyPointView extends View {
	private Paint mPaint;
	public MyPointView(Context context) {
		this(context, null);
	}

	public MyPointView(Context context, @Nullable AttributeSet attrs) {
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
		canvas.drawRGB(255, 255,180);
		//画一个点
		canvas.drawPoint(55, 55, mPaint);

		//画多个点
		/***
		 * 参数：
		 float[] pts:点的合集，与上面直线一直，样式为｛x1,y1,x2,y2,x3,y3,……｝
		 int offset:集合中跳过的数值个数，注意不是点的个数！一个点是两个数值；
		 count:参与绘制的数值的个数，指pts[]里人数值个数，而不是点的个数，因为一个点是两个数值

		 下面举例说明上面offset与count的含义：（跳过第一个点，画出后面两个点，第四个点不画），注意一个点是两个数值！
		 */


		float []pts={10,10,100,100,200,200,400,400};
		//2代表跳过10,10,4代表参与画画的有4个数，也就是100,100,200,200，也就是画出来的就两个点
		canvas.drawPoints(pts, 2, 4, mPaint);

	}

}
