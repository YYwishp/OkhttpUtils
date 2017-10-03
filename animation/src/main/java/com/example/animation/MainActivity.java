package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * View动画
 */
public class MainActivity extends Activity {
	private ImageView imgLogo;
	private Button button;
	private int height;
	private final int finalHeight = 0;
	private int measuredHeight;
	private int realHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		button = (Button) findViewById(R.id.button);
		
		
		
		
		imgLogo = (ImageView) findViewById(R.id.img_logo);
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		measuredHeight = imgLogo.getMeasuredHeight();
		
		ViewGroup.LayoutParams layoutParams = imgLogo.getLayoutParams();
		height = layoutParams.height;
		realHeight = imgLogo.getHeight();
	}
	
	public void startAnimation(View view) {
		Log.e("tag","measuredHeight"+measuredHeight);
		Log.e("tag","layoutParamsheight"+height);
		Log.e("tag","realHeight"+realHeight);
		
		HiddenAnimUtils.newInstance(this,imgLogo,button,realHeight).toggle();
	}
	
	public void startAnimationY(View view) {
		if (imgLogo.getVisibility() == View.VISIBLE) {
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgLogo, "translationY", 0.0f, -realHeight);
			imgLogo.setPivotX(0);
			imgLogo.setPivotY(0);
			imgLogo.invalidate();
			scaleY.setDuration(1000);
			scaleY.start();
			scaleY.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					imgLogo.setVisibility(View.GONE);
				}
			});
			
		} else {
			
			imgLogo.setVisibility(View.VISIBLE);
			ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgLogo, "translationY", -realHeight, 0.0f);
			imgLogo.setPivotX(0);
			imgLogo.invalidate();
			imgLogo.setPivotY(0);
			scaleY.setDuration(1000);
			scaleY.start();
			scaleY.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					
				}
			});
		}
		
		
		
		
	}
}
