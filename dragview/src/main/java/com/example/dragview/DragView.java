package com.example.dragview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by GYX on 2016/10/16.
 */

public class DragView extends View {

    private int lastX;
    private int lastY;
    private Scroller mScroller ;


    public DragView(Context context) {
        super(context);
        initView(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public DragView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initView(context);

    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("onTouchEvent", "**********>开始");


        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //按下
                Log.e("onTouchEvent", "------->按下");
                lastX = x;
                lastY = y;

                break;


            case MotionEvent.ACTION_MOVE:
                Log.e("onTouchEvent", "------->移动");
                Log.e("onTouchEvent", "-------X"+lastX+"-----------Y"+lastY);


                //移动,计算偏移量
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //方法1：在当前的基础上增加偏移量
                /*layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);*/
                //方法2：
                /*offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/
/*
                Log.e("onTouchEvent", "getRight"+getRight() +"------getBottom-----Y"+getBottom());
                Log.e("onTouchEvent", "getLeft"+getLeft() +"------getTop-----Y"+getTop());*/


                //方法3：layoutParams方法
             /*   ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();

                //layoutParams.rightMargin = getRight() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                //layoutParams.bottomMargin = getBottom() + offsetY;
                layoutParams.leftMargin = getLeft() +offsetX;

                setLayoutParams(layoutParams);*/

                //方法4：scrollBy（）
                ((View)getParent()).scrollBy(-offsetX,-offsetY);

                break;

            case MotionEvent.ACTION_UP:
                //抬起
                Log.e("onTouchEvent", "------->抬起");

                //方法5：
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),-viewGroup.getScrollX(),-viewGroup.getScrollY());
                invalidate();

                break;

        }


        return true;//true就自己消耗掉了，不给父控件处理了；
        // false，就自己不处理，代码不调用，传递给父控件的onTouchEvent方法处理
    }

    /**
     * 方法5：Scroller方法
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过重绘 不断调用computeScroll
            invalidate();

        }
    }
}
