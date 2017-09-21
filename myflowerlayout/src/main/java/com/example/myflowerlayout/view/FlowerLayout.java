package com.example.myflowerlayout.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowerLayout extends ViewGroup {
	private static final String tag = "FlowerLayout";
	//行对象,用于添加TextView
	private Line line;
	//一行中已经使用的宽度大小
	private int usedWidth = 0;
	//控件和控件间的水平间距
	private int horizontalSpacing = 20;
	//行根行间的竖直间距
	private int verticalSpacing = 20;
	//行所在的集合
	private List<Line> lineList = new ArrayList<Line>();
	
	public FlowerLayout(Context context) {
		super(context);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//1,获取用户设置的内间距
		int left = getPaddingLeft();
		int top = getPaddingTop();
		//2,放置lineList中的每一个行对象的位置
		for (int i = 0; i < lineList.size(); i++) {
			Line line = lineList.get(i);
			line.layout(left, top);
			top += line.lineHeight + verticalSpacing;
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//1,获取此自定义控件原有的宽高模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		//2,获取此自定义控件原有的宽高大小
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		//3,获取当前自定义控件的可用的宽度
		int invalidWidth = widthSize - getPaddingLeft() - getPaddingRight();
		//4,获取此自定义控件内部的所有的TextView,然后计算其换行规则
		int count = getChildCount();
		//创建一个行对象,
		if (line == null) {
			line = new Line();
		}
		for (int i = 0; i < count; i++) {
			View childView = getChildAt(i);
			//5,逐个处理孩子节点,让其不能超过父控件的宽高
			//5.1如果父控件的模式是精确的invalidWidth像素,孩子节点的要求就是不能超过父控件invalidWidth宽度,所以孩子节点的模式至多
			//5.2如果夫控件的模式是至多,或者未定义,孩子节点的模式和父控件的模式,保持一致,子控件宽度依然不会超过夫控件
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(invalidWidth, widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
			int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode);
			//6,告知孩子节点,安装上诉定义出来的宽高模式和大小控件的显示
			childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
			//7,行对象添加相应TextView,并且处理器换行规则
			int childWidth = childView.getMeasuredWidth();
			usedWidth += childWidth;
			if (usedWidth <= invalidWidth) {//匹配图片中的第2中情况
				//此控件可以添加到当前行
				line.addView(childView);
				//拿已使用的宽度+水平间距>当前行的可用宽度,则后续控件需要换行添加
				usedWidth += horizontalSpacing;
				if (usedWidth > invalidWidth) {
					//换行放置后续的控件
					if (!newLine()) {
						break;
					}
				}
			} else {
				//已使用的行的宽度,大于或者等于当前行的可用宽度(第一种情况(放控件放不下),第三中情况(一行只能放置一个控件))
				if (line.getViewCount() == 0) {//图片中的第三种情况
					//此控件添加到当前行,后续的控件就需要换行放置
					line.addView(childView);
					if (!newLine()) {
						break;
					}
				} else {//图片中的第1中情况
					if (!newLine()) {
						break;
					}
					line.addView(childView);
					usedWidth += childWidth + horizontalSpacing;
				}
			}
		}
		//8,将最后的一行添加到行的集合中去
		if (line != null && line.getViewCount() > 0 && !lineList.contains(line)) {
			lineList.add(line);
		}
		//9,计算获取自定义控件的宽度和高度,然后让此控件按照计算出来的精确度,宽高去做展示
		int flowWidth = MeasureSpec.getSize(widthMeasureSpec);
		//10,控件的高度 = 所有行的高度和+行间距+此控件的顶部和底部的内间距
		//所有行的高度和
		int totalLineHeight = 0;
		for (int i = 0; i < lineList.size(); i++) {
			Line line = lineList.get(i);
			totalLineHeight += line.lineHeight;
		}
		//所有行的高度和+行间距+
		int flowHeight = (lineList.size() - 1) * verticalSpacing + totalLineHeight + getPaddingBottom() + getPaddingTop();
		//宽高的大小都得到精确值,宽高的模式也需要维护成精确
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(flowWidth, MeasureSpec.EXACTLY);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(flowHeight, MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		setMeasuredDimension(flowWidth, resolveSize(flowHeight, heightMeasureSpec));
	}
	
	/**
	 * 换行方法
	 */
	public boolean newLine() {
		//1,将换行的前一行,添加到行的集合中,存储
		lineList.add(line);
		if (lineList.size() < 50) {
			//2,创建一个新的行对象
			line = new Line();
			//3,将新行的已使用宽度设置为0
			usedWidth = 0;
			return true;
		}
		return false;
	}
	
	class Line {
		//当前行的行高度大小
		private int lineHeight = 0;
		//当前行中所有控件的宽度和
		private int totalWidth = 0;
		//维护当前行,有多少个控件的集合,此集合用于计数行对象中的控件的个数
		private List<View> viewList = new ArrayList<View>();
		
		/**
		 * @param childView 向行对象中添加一个view
		 */
		public void addView(View childView) {
			//测量后的高度大小
			int measuredHeight = childView.getMeasuredHeight();
			int measuredWidth = childView.getMeasuredWidth();
			totalWidth += measuredWidth;
			lineHeight = lineHeight < measuredHeight ? measuredHeight : lineHeight;
			viewList.add(childView);
		}
		
		public int getViewCount() {
			return viewList.size();
		}
		
		//放置行对象中控件的方法
		public void layout(int left, int top) {
			//可用宽度大小
			int validWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
			//获取当前行的总的剩余宽度大小
			int surplusTotalWidth = validWidth - totalWidth - (viewList.size() - 1) * horizontalSpacing;
			//剩余宽度大小需要对所有的控件进行平均分配
			int surplusWidth = surplusTotalWidth / getViewCount();
			if (surplusWidth > 0) {
				//将此需要给每个控件添加的宽度,添加到控件身上
				for (int i = 0; i < viewList.size(); i++) {
					View view = viewList.get(i);
					int viewWidth = view.getMeasuredWidth() + surplusWidth;
					int viewHeight = view.getMeasuredHeight();
					//view.getLayoutParams().width = viewWidth;
					int widthMeasureSpec = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
					int heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
					view.measure(widthMeasureSpec, heightMeasureSpec);
					int surplusHeightHalf = (lineHeight - viewHeight) / 2;
					view.layout(left, top + surplusHeightHalf, left + viewWidth, top + surplusHeightHalf + viewHeight);
					//循环过程中后一个控件的左侧坐标 = 上一个控件的左侧坐标+上一个控件的宽度+水平间距
					left += viewWidth + horizontalSpacing;
				}
			} else {
				if (getViewCount() == 1) {
					//一行只放置一个控件的时候,需要将此控件指定左上右下的坐标位置
					View view = viewList.get(0);
					view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
				}
			}
		}
	}
}
