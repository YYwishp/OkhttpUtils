package com.example.myloadingbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyx on 2017/11/4.
 */
public class MyInputPassward extends android.support.v7.widget.AppCompatEditText {
	private Paint rectPaint;
	private Paint textPaint;
	private Rect textRect;
	private boolean isFocus = false;
	private String text = "";
	private List<Rect> list = new ArrayList<>();
	public MyInputPassward(Context context) {
		super(context);

		init();
	}



	public MyInputPassward(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyInputPassward(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private onInputOverListener onInputOverListener;
	public interface onInputOverListener {
		void onInputOver(String text);
	}
	public void setOnInputOverListener(onInputOverListener onInputOverListener) {
		this.onInputOverListener = onInputOverListener;
	}
	@Override
	protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);


		if (this.text == null) {
			return;
		}

		if (this.text.length() < 6) {
			this.text = this.text + text.toString();
		} else {
			if (onInputOverListener != null) {
				onInputOverListener.onInputOver(this.text);
				if (true) {
					closeKeybord();
				}
			}

		}

		if (text.toString().length() != 0) {
			setText("");
		}

	}
	/**
	 * 关闭软键盘
	 */
	public void closeKeybord() {
		InputMethodManager imm = (InputMethodManager) getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(getWindowToken(), 0);
	}

	private void init() {
		rectPaint = new Paint();
		textPaint = new Paint();
		textRect = new Rect();

		//setBackgroundDrawable(null);
		setLongClickable(false);
		setTextIsSelectable(false);
		setCursorVisible(false);
		textPaint.setAntiAlias(true);
		textPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		isFocus = focused;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		switch (heightMode){
			case MeasureSpec.EXACTLY:
				heightSize = MeasureSpec.getSize(heightMeasureSpec);
				break;
			case MeasureSpec.AT_MOST:
				heightSize = widthSize/6;
				break;
		}
		setMeasuredDimension(widthSize,heightSize);

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 67 && text.length() != 0) {
			text = text.substring(0, text.length() - 1);
			invalidate();
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		rectPaint.setStrokeWidth(10);
		textPaint.setColor(0xffe3ab00);
		textPaint.setTextSize(30);
		int width = Math.min(getMeasuredHeight(), getMeasuredWidth() / 2);

		for (int i = 0; i < 6; i++) {
			if (i <= text.length() && isFocus) {
				rectPaint.setColor(0xff28262f);
			} else {
				rectPaint.setColor(0xff28262f);
			}
			Rect rect = new Rect(i * width + 10, 10, i * width + width - 10, width - 10);
			canvas.drawRect(rect, rectPaint);

			list.add(rect);
		}

		for (int i = 0; i < text.length(); i++) {

			textPaint.getTextBounds(text.substring(i, i + 1), 0, 1, textRect);
			canvas.drawText(text.substring(i, i + 1), list.get(text.length()-i).left + (list.get(text.length()-i).right - list.get(text.length()-i).left) / 2 - textRect.width() / 2,
					list.get(i).top + ((list.get(i).bottom - list.get(i).top) / 2) + textRect.height() / 2, textPaint);

		}
	}

}





























