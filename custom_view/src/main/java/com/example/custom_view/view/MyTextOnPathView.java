package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/20.
 */
public class MyTextOnPathView extends View {
	public MyTextOnPathView(Context context) {
		super(context);
	}

	public MyTextOnPathView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);



		Paint paint=new Paint();
		paint.setColor(Color.RED);  //设置画笔颜色
		canvas.drawRGB(155,200,226);
		paint.setStrokeWidth (2);//设置画笔宽度
		paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
		paint.setTextSize(20);//设置文字大小
		paint.setStyle(Paint.Style.STROKE);//绘图样式，设置为填充

		String string="好好学习，天天向上";

		/**
		 *
		 void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
		 void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)

		 参数说明：

		 有关截取部分字体绘制相关参数（index,count），没难度，就不再讲了，下面首重讲hOffset、vOffset
		 float hOffset  : 与路径起始点的水平偏移距离
		 float vOffset  : 与路径中心的垂直偏移量
		 */
		//先创建两个相同的圆形路径，并先画出两个路径原图
		Path circlePath=new Path();
		circlePath.addCircle(150,200, 150, Path.Direction.CW);//逆向绘制,还记得吗,上篇讲过的
		canvas.drawPath(circlePath, paint);//绘制出路径原形

		Path circlePath2=new Path();
		circlePath2.addCircle(500,200, 150, Path.Direction.CW);
		canvas.drawPath(circlePath2, paint);//绘制出路径原形

		paint.setColor(Color.RED);
		//hoffset、voffset参数值全部设为0，看原始状态是怎样的
		canvas.drawTextOnPath(string, circlePath, 0, 0, paint);
		//第二个路径，改变hoffset、voffset参数值
		canvas.drawTextOnPath(string, circlePath2, 80, 30, paint);
	}
}
