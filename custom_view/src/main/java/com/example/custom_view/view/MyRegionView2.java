package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/9/1.
 */
public class MyRegionView2 extends View {
	public MyRegionView2(Context context) {
		super(context);

	}

	public MyRegionView2(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//初始化Paint
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(2);
		//构造一个椭圆路径
		Path ovalPath = new Path();
		RectF rect =  new RectF(50, 50, 200, 500);
		ovalPath.addOval(rect, Path.Direction.CCW);
		//SetPath时,传入一个比椭圆区域小的矩形区域,让其取交集
		Region rgn = new Region();
		rgn.setPath(ovalPath,new  Region(50, 50, 200, 200));
		//画出路径
		drawRegion(canvas, rgn, paint);
	}

	//这个函数不懂没关系，下面会细讲
	private void drawRegion(Canvas canvas,Region rgn,Paint paint)
	{
		RegionIterator iter = new RegionIterator(rgn);
		Rect r = new Rect();

		while (iter.next(r)) {
			canvas.drawRect(r, paint);
		}
	}
}
