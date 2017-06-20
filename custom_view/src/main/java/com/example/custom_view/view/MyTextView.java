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
public class MyTextView extends View {


	private Paint mPaint;

	public MyTextView(Context context) {
		this(context, null);
	}

	public MyTextView(Context context, @Nullable AttributeSet attrs) {
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
		/*//普通设置
		mPaint.setStrokeWidth (5);//设置画笔宽度
		mPaint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
		mPaint.setStyle(Paint.Style.FILL);//绘图样式，对于设文字和几何图形都有效
		mPaint.setTextAlign(Paint.Align.CENTER);//设置文字对齐方式，取值：align.CENTER、align.LEFT或align.RIGHT
		mPaint.setTextSize(12);//设置文字大小

		//样式设置
		mPaint.setFakeBoldText(true);//设置是否为粗体文字
		mPaint.setUnderlineText(true);//设置下划线
		mPaint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
		mPaint.setStrikeThruText(true);//设置带有删除线效果

		//其它设置
		mPaint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变  */
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画布背景颜色
		canvas.drawRGB(229, 239, 211);
		mPaint.setTextSize(80);//设置文字大小
		//样式设置
		mPaint.setFakeBoldText(false);//设置是否为粗体文字
		mPaint.setUnderlineText(false);//设置下划线
		mPaint.setStrikeThruText(false);//设置带有删除线效果
		mPaint.setTextSkewX((float) 0);
		//绘图样式，设置为填充
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawText("好好学习，天天向上", 10,100, mPaint);

		//绘图样式设置为描边
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawText("好好学习，天天向上", 10,200, mPaint);

		//绘图样式设置为填充且描边
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawText("好好学习，天天向上", 10,300, mPaint);
		//
		//样式设置
		mPaint.setFakeBoldText(true);//设置是否为粗体文字
		mPaint.setUnderlineText(true);//设置下划线
		mPaint.setStrikeThruText(true);//设置带有删除线效果
		//设置字体水平倾斜度，普通斜体字是-0.25，可见往右斜
		mPaint.setTextSkewX((float) -0.25);
		canvas.drawText("好好学习，天天向上", 10,400, mPaint);
		//水平倾斜度设置为：0.25，往左斜
		mPaint.setTextSkewX((float) 0.25);
		canvas.drawText("好好学习，天天向上", 10,500, mPaint);


	}


}
