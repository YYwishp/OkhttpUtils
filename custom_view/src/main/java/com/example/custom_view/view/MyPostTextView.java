package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/20.
 */
public class MyPostTextView extends View {



	public MyPostTextView(Context context) {
		super(context);
	}

	public MyPostTextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRGB(192,132,60);
		/**
		 *
		 void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
		 void drawPosText (String text, float[] pos, Paint paint)

		 说明：
		 第一个构造函数：实现截取一部分文字绘制；

		 参数说明：
		 char[] text：要绘制的文字数组
		 int index:：第一个要绘制的文字的索引
		 int count：要绘制的文字的个数，用来算最后一个文字的位置，从第一个绘制的文字开始算起
		 float[] pos：每个字体的位置，同样两两一组，如｛x1,y1,x2,y2,x3,y3……｝
		 */

		Paint paint=new Paint();
		paint.setColor(Color.RED);  //设置画笔颜色

		paint.setStrokeWidth (5);//设置画笔宽度
		paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
		paint.setTextSize(80);//设置文字大小
		paint.setStyle(Paint.Style.FILL);//绘图样式，设置为填充

		float []pos=new float[]{80,100,
				80,200,
				80,300,
				80,400};
		canvas.drawPosText("画图示例", pos, paint);//两个构造函数



	}
}










































