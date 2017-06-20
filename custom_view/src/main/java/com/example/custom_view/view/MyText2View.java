package com.example.custom_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GYX on 2017/6/19.
 */
public class MyText2View extends View {



	public MyText2View(Context context) {
		this(context, null);
	}

	public MyText2View(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		initPaint();
	}
	private void initPaint() {

		/*
	   * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */


    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */

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
		// 实例化画笔并打开抗锯齿
		 Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(80);//设置文字大小
		mPaint.setStyle(Paint.Style.FILL);
		// 设置画笔颜色为浅灰色
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(2);
		//设置画布背景颜色
		canvas.drawRGB(29, 239, 211);
		//变通样式字体
		canvas.drawText("好好学习，天天向上", 10,100, mPaint);

		//水平方向拉伸两倍
		mPaint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变
		canvas.drawText("好好学习，天天向上", 10,200, mPaint);

		//写在同一位置,不同颜色,看下高度是否看的不变
		mPaint.setTextScaleX(1);//先还原拉伸效果
		canvas.drawText("好好学习，天天向上", 10,300, mPaint);

		mPaint.setColor(Color.GREEN);
		mPaint.setTextScaleX(2);//重新设置拉伸效果
		canvas.drawText("好好学习，天天向上", 10,300, mPaint);
		//
		/**\
		 *
		 *
		 *
		 *
		 * （1）、普通水平绘制
		 构造函数：

		 void drawText (String text, float x, float y, Paint paint)
		 void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
		 void drawText (String text, int start, int end, float x, float y, Paint paint)
		 void drawText (char[] text, int index, int count, float x, float y, Paint paint)

		 说明：
		 第一个构造函数：最普通简单的构造函数；
		 第三、四个构造函数：实现截取一部分字体给图；
		 第二个构造函数：最强大，因为传入的可以是charSequence类型字体，所以可以实现绘制带图片的扩展文字（待续），而且还能截取一部分绘制

		 这几个函数就不再多说了，很简单，前面我们也一直在用第一个构造函数，文字截取一般用不到，我也不多说了，浪费时间，
		 可能大家看到有个构造函数中，可以传入charSequence类型的字符串，charSequence是可以利用spannableString来构造有图片的字符串的，
		 那这里是不是可以画出带有图片的字符串来呢 ，我想多了，实际证明，canvas画图是不支持Span替换的。所以这里的charSequence跟普通的String没有任何区别的。
		 *
		 *
		 *
		 */



	}

}




















































