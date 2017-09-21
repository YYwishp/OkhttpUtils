package com.example.myflowerlayout.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowerLayout extends ViewGroup {
	private Line line;
	//当前行已使用的宽度的具体值
	private int mUsedLineWidth = 0;
	//水平方向上面间距具体值
	private int horizontalSpacing = 20;
	//竖直方向上行根行的间距
	private int verticalSpacing = 20;
	//存储行对象的集合
	private List<Line> lineList = new ArrayList<Line>();
	//应用中,可以有的最大行数
	private int maxLine = 100;
	
	public FlowerLayout(Context context) {
		super(context);
	}
	
	//获取了控件的宽高以后,需要其排放位置的方法
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//获取每一个行对象所在的左上角的位置
		int left = getPaddingLeft();
		int top = getPaddingTop();
		//定义每一个行对象所在的位置
		for (int i = 0; i < lineList.size(); i++) {
			Line line = lineList.get(i);
			line.layoutView(left, top);
			//顶端的高度值,是以上所有行的高度的累加+行和行的间距
			top += line.lineHeight + verticalSpacing;
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获取屏幕的宽度 - 左右内存间距 = 可用宽度
		int widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
		//获取高度值
		int heightSize = MeasureSpec.getSize(heightMeasureSpec) - getPaddingBottom() - getPaddingTop();
		//获取宽高的模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		//处理自定义控件中的每一个内部的view,所有的textView都是FlowerLayout的子节点
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			//定义FlowerLayout子控件的最大宽高值
			//如果夫控件的模式是精确,子控件的模式就是至多
			//如果库控件的模式是至多或者未知(子控件的模式跟夫控件一致)
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize,
					widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
			int heightWidthMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize,
					heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode);
			//重新绘制对应的子控件
			childView.measure(childWidthMeasureSpec, heightWidthMeasureSpec);
			//获取索引指向childView的宽度值
			int childWidth = childView.getMeasuredWidth();
			//获取索引指向childView的高度值
			int childHeight = childView.getMeasuredHeight();
			//如果当前控件的宽度小于当前行的可用宽度,则可以添加到当前行中
			if (line == null) {
				line = new Line();
			}
			mUsedLineWidth += childWidth;
			if (mUsedLineWidth < widthSize) {
				//可以添加当前控件  对应图片情况 ============2
				line.addView(childView);
				mUsedLineWidth += horizontalSpacing;
				if (mUsedLineWidth > widthSize) {
					//换行,如果创建行成功,则代码运行结束,从而进入下一次的循环条件,也就是在创建的新行上去做相同的判断逻辑
					if (!newLine()) {
						break;
					}
				}
			} else {
				//有可能不能加到当前行 对应图片情况============3
				if (line.getLineViewCount() == 0) {
					//当前行还没有添加过任何的控件
					line.addView(childView);
					//如果前一个控件单独占有了一行,后续的控件就需要去新起一行做相应添加
					if (!newLine()) {
						break;
					}
				} else {
					// 对应图片情况============1
					//如果创建了新行,则需要将控件添加到新的行里面去
					if (!newLine()) {
						break;
					}
					line.addView(childView);
					mUsedLineWidth += childWidth + horizontalSpacing;
				}
			}
		}
		//如果现在所在的最后一行,还没有添加到行所在集合中,就需要做添加操作
		if (line != null && !lineList.contains(line) && line.getLineViewCount() > 0) {
			lineList.add(line);
		}
		//具体的计算FlowerLayout的宽高值,用于测量
		//自定义控件的宽度
		int flWidth = MeasureSpec.getSize(widthMeasureSpec);
		//自定义控件的高度
		//所有行的高度+行和行的总间距+flowerLayout内间距(顶端和底端) = 自定义空的高度
		int lineCount = lineList.size();
		int flHeight = 0;
		for (int i = 0; i < lineCount; i++) {
			Line line = lineList.get(i);
			flHeight += line.lineHeight;
		}
		flHeight = flHeight + (lineCount - 1) * verticalSpacing + getPaddingTop() + getPaddingBottom();
		//onMeasure方法具体实现
		setMeasuredDimension(flWidth, resolveSize(flHeight, heightMeasureSpec));
	}
	
	//换行的方法
	private boolean newLine() {
		//在上一行的基础上,没有办法去方向控件了,所以换行,记录之前的行对象
		lineList.add(line);
		//应用中一共不能超过多少行
		if (lineList.size() < 100) {
			line = new Line();
			//新开启一行后,需要将已使用的行宽度归0
			mUsedLineWidth = 0;
			return true;
		}
		return false;
	}
	
	//创建一个行对象
	class Line {
		//行对象中,每一个控件宽度值叠加得到的最终总宽度
		private int widthTotal;
		//行高度是由当前行中,最高的控件去填充起来
		private int lineHeight;
		private List<View> viewList = new ArrayList<View>();
		
		//告知当前行有多少方格的方法
		public int getLineViewCount() {
			return viewList.size();
		}
		
		//添加view的方法
		public void addView(View view) {
			//获取view对象的高度
			//获取view对象的宽度
			int width = view.getMeasuredWidth();
			int height = view.getMeasuredHeight();
			//每添加一个view到当前行,都需要去维护widthTotal
			widthTotal += width;
			//高度,添加进来最高的控件的高度,就是行高度
			lineHeight = lineHeight < height ? height : lineHeight;
			//将对应的view添加到行对象中,封装内部view的集合中去
			viewList.add(view);
		}
		
		//行中控件的一个排列方式(安放的位置),以及其留白区域的分配规则
		public void layoutView(int l, int t) {
			//l代表左侧的起始位置,t代表起始的顶端位置
			int left = l;
			int top = t;
			//计算水平方向上的留白区域
			//自定义控件的宽度-左右两次的内间距-当前行控件已经使用的宽度-当前行控件和控件间的间距 = 留白区域
			int viewCount = viewList.size();
			int validWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
			int surplusWidthTotal = validWidth - widthTotal - (viewCount - 1) * horizontalSpacing;
			//留白区域需要去给当前行的每一个控件做平均分配
			if (surplusWidthTotal > 0) {
				//每一个控件还可以去添加的额外分配的宽度值
				int surplusWidth = surplusWidthTotal / viewCount;
				//遍历获取当前行上每一个控件,然后将额外的宽度添加给每一个行中的view
				for (int i = 0; i < viewCount; i++) {
					View view = viewList.get(i);
					//获取原有的宽度+额外分配宽度 = 真实宽度
					int viewWidth = view.getMeasuredWidth() + surplusWidth;
					int viewHeight = view.getMeasuredHeight();
					//修改过宽度后,需要去重写构建view
					int viewMakeMeasureSpecWidth = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
					int viewMakeMeasureSpecHeight = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
					view.measure(viewMakeMeasureSpecWidth, viewMakeMeasureSpecHeight);
					//计算当前要去放置控件和默认顶端的额外间距
					int surplusHeightTotal = lineHeight - viewHeight;
					int surplusHeightTotalHalf = surplusHeightTotal / 2;
					if (surplusHeightTotalHalf < 0) {
						surplusHeightTotalHalf = 0;
					}
					//去指定每一个view的位置了
					view.layout(left, top + surplusHeightTotalHalf, left + viewWidth, top + surplusHeightTotalHalf + viewHeight);
					left += viewWidth + horizontalSpacing;
				}
			} else {
				//水平方向上没有留白,当前行就一个控件
				View view = viewList.get(0);
				view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
			}
		}
	}
}
