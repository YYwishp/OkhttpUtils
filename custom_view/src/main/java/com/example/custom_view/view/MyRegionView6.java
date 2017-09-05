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
	public MyRegionView6(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyRegionView6(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		//构造两个矩形
		Rect rect1 = new Rect(100,100,400,200);
		Rect rect2 = new Rect(200,0,300,300);

		//构造一个画笔，画出矩形轮廓
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);

		canvas.drawRect(rect1, paint);
		canvas.drawRect(rect2, paint);



		//构造两个Region
		Region region = new Region(rect1);
		Region region2= new Region(rect2);

		//取两个区域的交集
		region.op(region2, Region.Op.REVERSE_DIFFERENCE);

		//再构造一个画笔,填充Region操作结果
		Paint paint_fill = new Paint();
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
