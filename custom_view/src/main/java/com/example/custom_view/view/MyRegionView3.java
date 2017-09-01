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
public class MyRegionView3 extends View {
	public MyRegionView3(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyRegionView3(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//初始化Paint
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		//构造一个椭圆路径
		Path ovalPath = new Path();
		RectF rect =  new RectF(50, 50, 200, 500);
		ovalPath.addOval(rect, Path.Direction.CCW);
		//SetPath时,传入一个比椭圆区域大的矩形区域,让其取交集，也就是椭圆的大小
		Region rgn = new Region();
		rgn.setPath(ovalPath,new  Region(50, 50, 200, 600));
		//画出路径
		drawRegion(canvas, rgn, paint);
	}


	//对于特定的区域，我们都可以使用多个矩形来表示其大致形状。事实上，如果矩形足够小，一定数量的矩形就能够精确表示区域的形状，也就是说，一定数量的矩形所合成的形状，
	// 也可以代表区域的形状。RegionIterator类，实现了获取组成区域的矩形集的功能，其实RegionIterator类非常简单，
	// 总共就两个函数，一个构造函数和一个获取下一个矩形的函数；
	// RegionIterator(Region region) //根据区域构建对应的矩形集
	// boolean	next(Rect r) //获取下一个矩形，结果保存在参数Rect r 中
	private void drawRegion(Canvas canvas,Region rgn,Paint paint)
	{
		RegionIterator iter = new RegionIterator(rgn);
		Rect r = new Rect();

		while (iter.next(r)) {
			canvas.drawRect(r, paint);
		}
	}
	//上面我们也都看到了它的用法，首先，根据区域构建一个矩形集，然后利用next(Rect r)来逐个获取所有矩形，绘制出来，
	// 最终得到的就是整个区域，如果我们将上面的画笔Style从FILL改为STROKE，重新绘制椭圆路径，会看得更清楚。
}
