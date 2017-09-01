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
 * Created by GYX on 2017/9/1.
 */
public class MyRegionView extends View {
	private Paint paint;
	private Region rgn;

	public MyRegionView(Context context) {
		this(context, null);
	}

	public MyRegionView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	private void initPaint() {
		//初始化画笔
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(2);
		rgn = new Region(10,10,100,100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);


		//使用Set函数后，替换为新区域
//      rgn.set(100, 100, 200, 200);
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
