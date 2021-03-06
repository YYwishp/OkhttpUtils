package com.example.myflowerlayout.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYX on 2017/9/21.
 */
public class FlowerLayout1 extends ViewGroup {
	//行对象，用于添加textveiw
	private Line line;
	//一行中已经使用的宽度大小
	private int usedWidth = 0;
	//子控件和子控件间的水平间距
	private int horizontalSpacing = 20;
	//行与行之间的间距
	private int verticalSpacing = 15;
	//行所在的集合
	private List<Line> lineList = new ArrayList<>();

	public FlowerLayout1(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*//1,获取此控件原有的宽高模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		//2,获取此控件原有的宽高大小
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		//3，获取此控件可用的宽度
		int invalidWidth = widthSize - getPaddingLeft() - getPaddingRight();
		//4,获取此控件内部所有的Textview，然后 计算其换行规则
		int count = getChildCount();
		
		
		
		for (int i = 0; i < count; i++) {
			View childView = getChildAt(i);
			//创建行对象
			if (line == null) {
				line = new Line();
			}
			//5,逐个处理孩子节点，让其不能超过父控件的宽高
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(invalidWidth, widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode);
			int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode);
			//6，告知孩子节点，按照上诉定义出来的宽高模式和大小，来显示孩子view
			childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
			//7,给行对象添加，添加相应的textview，并且处理其换行规则
			int childWidth = childView.getMeasuredWidth();
			usedWidth += childWidth;
			//已经使用的宽度，小于整个控件的可用宽度的时候
			if (usedWidth < invalidWidth) {   //匹配图片中的第二种情况
				//此控件可以添加到当前行
				line.addView(childView);
				//拿已使用的宽度+水平间距>当前行的总宽度，则后续控件需要换行添加
				usedWidth += horizontalSpacing;
				if (usedWidth > invalidWidth) {
					//换行放置后续的控件
					if (!newLine()) {
						break;
					}
				}
			} else {
				//已使用的行的宽度，大于或者等于当前行的可用宽度（第一种，添加的控件超出一行，第3种，一个行只能放一个控件）
				if (line.getViewCount() == 0) {
					//当前行的数量为0的时候，子控件添加到当前行，后续的控件就需要换行
					line.addView(childView);
					if (!newLine()) {
						break;
					}
				} else {
					//这里是第一种情况，已经添加了几个子view，再添加，放不下了，要先换行，在添加
					if (!newLine()) {
						break;
					}
					line.addView(childView);
					//计算已经使用的宽度
					usedWidth += childWidth + horizontalSpacing;


				}
			}
		}

		//8,将最后的一行点加到行的集合中
		if (line != null && line.getViewCount() > 0 && !lineList.contains(line)) {
			lineList.add(line);
		}
		//9, 计算获取自定义控件的宽度和高度，然后让此控件按照计算出出来的精确度，宽高去做展示
		int flowerWidth = MeasureSpec.getSize(widthMeasureSpec);
		//10,控件的高度 = 所有行的高度和+行间距和+此控件的顶部和底部的内间距
		int totalLineHeight = 0;
		for (int i = 0; i < lineList.size(); i++) {
			Line line = lineList.get(i);
			//所有行总高度
			totalLineHeight +=line.lineHeight;
		}
		//所有行间距+所有行高+上下的padding
		int flowerHeight= ((lineList.size() - 1) * verticalSpacing )+ totalLineHeight + getPaddingTop() + getPaddingBottom();
		//宽高的大小都得到精确值，宽高的模式也要维护成精确值
		 widthMeasureSpec= MeasureSpec.makeMeasureSpec(flowerWidth, MeasureSpec.EXACTLY);
		 heightMeasureSpec = MeasureSpec.makeMeasureSpec(flowerHeight, MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		*/
		
		
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingRight() - getPaddingLeft();
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
		
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		restoreLine();// 还原数据，以便重新记录
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}
			int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(sizeWidth, modeWidth == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : modeWidth);
			int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(sizeHeight, modeHeight == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : modeHeight);
			// 测量child
			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
			
			if (line == null) {
				line = new Line();
			}
			int childWidth = child.getMeasuredWidth();
			usedWidth += childWidth;// 增加使用的宽度
			if (usedWidth <= sizeWidth) {// 使用宽度小于总宽度，该child属于这一行。
				line.addView(child);// 添加child
				usedWidth += horizontalSpacing;// 加上间隔
				if (usedWidth >= sizeWidth) {// 加上间隔后如果大于等于总宽度，需要换行
					if (!newLine()) {
						break;
					}
				}
			} else {// 使用宽度大于总宽度。需要换行
				if (line.getViewCount() == 0) {// 如果这行一个child都没有，即使占用长度超过了总长度，也要加上去，保证每行都有至少有一个child
					line.addView(child);// 添加child
					if (!newLine()) {// 换行
						break;
					}
				} else {// 如果该行有数据了，就直接换行
					if (!newLine()) {// 换行
						break;
					}
					// 在新的一行，不管是否超过长度，先加上去，因为这一行一个child都没有，所以必须满足每行至少有一个child
					line.addView(child);
					usedWidth += childWidth + horizontalSpacing;
				}
			}
		}
		
		if (line != null && line.getViewCount() > 0 && !lineList.contains(line)) {
			// 由于前面采用判断长度是否超过最大宽度来决定是否换行，则最后一行可能因为还没达到最大宽度，所以需要验证后加入集合中
			lineList.add(line);
		}
		
		int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
		int totalHeight = 0;
		final int linesCount = lineList.size();
		for (int i = 0; i < linesCount; i++) {// 加上所有行的高度
			totalHeight += lineList.get(i).lineHeight;
		}
		totalHeight += verticalSpacing * (linesCount - 1);// 加上所有间隔的高度
		totalHeight += getPaddingTop() + getPaddingBottom();// 加上padding
		// 设置布局的宽高，宽度直接采用父view传递过来的最大宽度，而不用考虑子view是否填满宽度，因为该布局的特性就是填满一行后，再换行
		// 高度根据设置的模式来决定采用所有子View的高度之和还是采用父view传递过来的高度
		setMeasuredDimension(totalWidth, resolveSize(totalHeight, heightMeasureSpec));
		
	}
	
	/** 还原所有数据 */
	private void restoreLine() {
		lineList.clear();
		line = new Line();
		usedWidth = 0;
	}
	/**
	 * 换行方法
	 */
	public boolean newLine() {
		//1,将换行的前一行，添加到行的集合中，存储
		lineList.add(line);
		//限制下行数不超过49行
		if (lineList.size() < 50) {
			//2,创建一个新的行对象
			line = new Line();
			//3,将新行的已使用宽度设置为0
			usedWidth = 0;
			return true;
		}
		return false;

	}

	@Override
	protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
		//1.获取用户设置的内间距
		/*int left = getPaddingLeft();
		int top = getPaddingTop();
		//2,放置LineList集合中的每一个航对象的位置
		for (int j = 0; j < lineList.size(); j++) {
			Line line = lineList.get(i);
			line.layout(left, top);
			top += line.lineHeight + verticalSpacing;
		}*/
		
		
		int left = getPaddingLeft();//获取最初的左上点
		int top = getPaddingTop();
		final int linesCount = lineList.size();
		for (int j = 0; j < linesCount; j++) {
		Line oneLine = lineList.get(i);
			oneLine.layout(left, top);//布局每一行
			top += oneLine.lineHeight + verticalSpacing;//为下一行的top赋值
		}
	}

	class Line {
		//行高
		private int lineHeight = 0;
		//当前行中所有子空间宽度的和
		private int totalWidth = 0;

		//维护当前行，有多少控件的结合，此集合用于计数，航对象中的控件的个数
		private List<View> viewList = new ArrayList<View>();

		/**
		 * 向行对象中添加一个View
		 *
		 * @param childView
		 */
		public void addView(View childView) {
			//测量后的高度大小
			int measuredHeight = childView.getMeasuredHeight();
			int measuredWidth = childView.getMeasuredWidth();
			//当前行中所有子空间宽度的和
			totalWidth+=measuredWidth;
			lineHeight = lineHeight < measuredHeight ? measuredHeight : lineHeight;
			viewList.add(childView);
		}

		public int getViewCount() {
			return viewList.size();
		}

		/**
		 * 放置行对象中控制的方法
		 *
		 */
		public void layout(int l, int t) {
			/*//父控件可用宽度的大小
			int validWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
			//一行当中剩余的总宽度
			int surplusTotalWidth = validWidth - totalWidth - (viewList.size() - 1) * horizontalSpacing;
			//剩余空度的大小要对所有的控件进行平均分配
			int surplusWidth= surplusTotalWidth / getViewCount();
			//将此平均值添加给这行的每一个控件上，说白了就是平均分配下
			if (surplusWidth > 0) {
				for (int i = 0; i < viewList.size(); i++) {
					View view = viewList.get(i);
//					int viewWidth = view.getMeasuredWidth() + surplusWidth;
					int viewWidth = view.getMeasuredWidth();
					int viewHeight = view.getMeasuredHeight();
					int wideMeasureSpec = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
					int heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
					view.measure(wideMeasureSpec, heightMeasureSpec);
					//每个控件应该弥补的高度
					int surplusHeightHalf = (lineHeight - viewHeight) / 2;
					//布局
					view.layout(left, top + surplusHeightHalf, left + viewHeight, viewHeight + top + surplusHeightHalf);
					//循环过程中，后一个空间的左侧坐标 = 上一个左侧控件的坐标+上一个控件的宽度+水平间距
					left += viewWidth + horizontalSpacing;
				}
			} else {
				if (getViewCount() == 1) {
					//一行只放置一个空间的是偶，需要将此控件指定左上右下的坐标位置
					View view = viewList.get(0);
					view.layout(left,top,left+view.getMeasuredWidth(),top+view.getMeasuredHeight());
				}
			}*/
			
			int left = l;
			int top = t;
			int count = getViewCount();
			//总宽度
			int layoutWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
			//剩余的宽度，是除了View和间隙的剩余空间
			int surplusWidth = layoutWidth - totalWidth - horizontalSpacing * (count - 1);
			
			
			
			
			
			if (surplusWidth >= 0) {// 剩余空间
				// 采用float类型数据计算后四舍五入能减少int类型计算带来的误差
				int splitSpacing = (int) (surplusWidth / count + 0.5);
				for (int i = 0; i < count; i++) {
					final View view = viewList.get(i);
					int childWidth = view.getMeasuredWidth();
					int childHeight = view.getMeasuredHeight();
					//计算出每个View的顶点，是由最高的View和该View高度的差值除以2
					int topOffset = (int) ((lineHeight - childHeight) / 2.0 + 0.5);
					if (topOffset < 0) {
						topOffset = 0;
					}
					//把剩余空间平均到每个View上
					//childWidth = childWidth + splitSpacing;
					view.getLayoutParams().width = childWidth;
					if (splitSpacing > 0) {//View的长度改变了，需要重新measure
						int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
						int heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
						view.measure(widthMeasureSpec, heightMeasureSpec);
					}
					//布局View
					view.layout(left, top + topOffset, left + childWidth, top + topOffset + childHeight);
					left += childWidth + horizontalSpacing; //为下一个View的left赋值
				}
			} else {
				if (count == 1) {
					View view = viewList.get(0);
					view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
				} else {
					// 走到这里来，应该是代码出问题了，目前按照逻辑来看，是不可能走到这一步
				}
			}
		}

		

	}
























}
