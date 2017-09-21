package com.example.myflowerlayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {
	/**
	 * @param rgb	随机背景色
	 * @param r		圆角半径
	 * @return		按照随机背景色和指定圆角半径绘制出来的圆角矩形图片
	 */
	/*public static GradientDrawable getGradientDrawable(int rgb,int r){
		GradientDrawable gradientDrawable = new GradientDrawable();
		//1,画一个矩形
		gradientDrawable.setGradientType(GradientDrawable.GRADIENT);
		//2,什么颜色的矩形
		gradientDrawable.setColor(rgb);
		//3,矩形的圆角半径是多少
		gradientDrawable.setCornerRadius(r);
		return gradientDrawable;
	}*/
	
	/**
	 * @param pressDrawable		选中图片
	 * @param normalDrawable	未选中图片
	 * @return					选中图片和选中状态绑定,未选中图片和未选中状态绑定
	 */
	public static StateListDrawable getStateListDrawable(Drawable pressDrawable,Drawable normalDrawable){
		StateListDrawable stateListDrawable = new StateListDrawable();
		//将图片和状态绑定
		stateListDrawable.addState(
				new int[]{android.R.attr.state_enabled,android.R.attr.state_pressed}, pressDrawable);
		stateListDrawable.addState(
				new int[]{android.R.attr.state_enabled}, normalDrawable);
		stateListDrawable.addState(
				new int[]{}, normalDrawable);
		return stateListDrawable;
	}
}
