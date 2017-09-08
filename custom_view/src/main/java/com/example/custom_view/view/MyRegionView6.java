package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/9/5.
 */
public class MyRegionView6 extends View {
	private Rect rect1;
	private Rect rect2;
	private Paint paint;
	private Region region;
	private Region region2;
	private Paint paint_fill;

	public MyRegionView6(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyRegionView6(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);


		//构造两个矩形
		rect1 = new Rect(100,100,400,200);
		rect2 = new Rect(200,0,300,300);

		//构造一个画笔，画出矩形轮廓
		paint = new Paint();
		//构造两个Region
		region = new Region(rect1);
		region2 = new Region(rect2);
		//最终区域为region2 与 region1不同的区域
		region.op(region2, Region.Op.REVERSE_DIFFERENCE);
		//再构造一个画笔,填充Region操作结果
		paint_fill = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);


		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);

		canvas.drawRect(rect1, paint);
		canvas.drawRect(rect2, paint);




		paint.setTextSize(30);
		paint.setAntiAlias(true);
		canvas.drawText("最终区域为region2 与 region1不同的区域",100,350,paint);




		paint_fill.setColor(Color.GREEN);
		paint_fill.setStyle(Paint.Style.FILL);
		drawRegion(canvas, region, paint_fill);

	}


	private void drawRegion(Canvas canvas,Region rgn,Paint paint)
	{
		RegionIterator iter = new RegionIterator(rgn);
		Rect r = new Rect();

		while (iter.next(r)) {
			canvas.drawRect(r, paint);
		}
	}
}
