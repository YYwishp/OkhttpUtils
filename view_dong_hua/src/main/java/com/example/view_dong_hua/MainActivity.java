package com.example.view_dong_hua;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * dialog动画
 */
public class MainActivity extends AppCompatActivity {

	private ImageView map1;
	private ImageView close;
	private TextView text;
	private MyDialog build;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		text = (TextView) findViewById(R.id.text);
		text.setOnClickListener(new View.OnClickListener() {
			private MyDialog build;

			@Override
			public void onClick(View v) {
				//方式1
				/*final MyDialog myDialog = new MyDialog(MainActivity.this);
				Window window = myDialog.getWindow();
				//window.setGravity(Gravity.CENTER);
				window.setWindowAnimations(R.style.mystyle);
				//点击外部关闭dialog
//				myDialog.setCanceledOnTouchOutside(true);
				myDialog.setImage(R.drawable.map2);
				myDialog.setOnMyDialogClickListener(new MyDialog.OnMyDialogClickListener() {
					@Override
					public void onClick() {
						myDialog.dismiss();
						final MyDialog myDialog1 = new MyDialog(MainActivity.this);
						Window window = myDialog1.getWindow();
						//window.setGravity(Gravity.CENTER);
						window.setWindowAnimations(R.style.mystyle);
						myDialog1.show();
					}
				});
				myDialog.show();*/
				//方式2
				build = new MyDialog.Builder(MainActivity.this)
						.setImage(R.drawable.map1)
						.setOnMyDialogClickListener(new MyDialog.Builder.OnMyDialogClickListener() {
							@Override
							public void onClick() {
								build.dismiss();
								showDialogint(R.drawable.map2);
							}
						})
						.build();
				build.show();

//				build.getWindow().setWindowAnimations(R.style.mystyle);
			}
		});



	}

	public void showDialogint(int resource) {
		build = new MyDialog.Builder(MainActivity.this)
				.setImage(resource)
				.setOnMyDialogClickListener(new MyDialog.Builder.OnMyDialogClickListener() {
					@Override
					public void onClick() {
						build.dismiss();
					}
				})
				.build();
		build.show();
	}






	/**
	 * 缩放动画（view动画）
	 */
	public void scale(View view){
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0,1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

		//动画播放的时间长度
		sa.setDuration(500);
		//设置重复播放的次数
		//设置重复播放的模式
		//让iv播放aa动画
		view.startAnimation(sa);
	}
}
