package com.example.timecount;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by GYX on 2017/4/6.
 */
public class CountDownTimerUtil extends CountDownTimer {
	private TextView mTextView;

	/**
	 * 构造函数
	 * @param textView 控件
	 * @param millisInFuture 持续时间
	 * @param countDownInterval 间隔时间
	 */
	public CountDownTimerUtil(TextView textView,long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		mTextView = textView;
	}

	/**
	 * 每隔间隔时间，就会回调这
	 * @param millisUntilFinished 剩余时间
	 */
	@Override
	public void onTick(long millisUntilFinished) {
		mTextView.setClickable(false); //设置不可点击
		String time;
		if (millisUntilFinished / 1000 < 10) {
			time = "0" + millisUntilFinished / 1000;
			mTextView.setText(time + "秒后可重新发送");  //设置倒计时时间
		} else {
			mTextView.setText(millisUntilFinished / 1000 + "秒后可重新发送");  //设置倒计时时间
		}

		SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
		ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
		spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
		mTextView.setText(spannableString);

	}

	/**
	 * 结束的时候回调
	 */
	@Override
	public void onFinish() {
		mTextView.setText("重新获取验证码");
		mTextView.setClickable(true);//重新获得点击
	}
}
