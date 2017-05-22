package com.example.view_dong_hua;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * dialog动画
 */
public class MainActivity extends AppCompatActivity {

	//private ImageView map1;
	private ImageView close;
	private TextView text;
	private MyDialog build;
	private ImageView map1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map1 = (ImageView) findViewById(R.id.map1);

		text = (TextView) findViewById(R.id.text);
		//ext.setText("阿斯顿上多发水电费撒旦法是打发斯蒂芬三大上第发四谛法四谛法四谛法是的发上多发水电费撒旦法上的圣达菲四谛法四谛法四谛法是的发");
		text.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
			@Override
			public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
				Layout layout = text.getLayout();
				int width = layout.getWidth();
				int lineCount = layout.getLineCount();

				if (lineCount > 1) {
					String trim = text.getText().toString().trim();
					int lineStart = layout.getLineStart(1);
					Log.e("第一行文字个数", lineStart + "");
					String substring = trim.substring(0, lineStart-3);
					text.setText(substring+"...");
				}

				/*Log.e("宽度", width + "");
				Log.e("行数", lineCount + "");
				int old_len = oldRight - oldLeft;
				int now_len = right - left;
				Log.e("测试", "old_len:" + old_len + "now_len:" + now_len);*/




			}
		});


		text.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				int lineCount = text.getLineCount();
				Log.e("有几行", lineCount + "");

			}
		});
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
				/*build = new MyDialog.Builder(MainActivity.this)
						.setImage(R.drawable.map1)
						.setOnMyDialogClickListener(new MyDialog.Builder.OnMyDialogClickListener() {
							@Override
							public void onClick() {
								build.dismiss();
								showDialogint(R.drawable.map2);
							}
						})
						.build();
				build.show();*/

//				build.getWindow().setWindowAnimations(R.style.mystyle);


			}
		});



		map1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				trans(map1);
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

	public void trans(View view){
		Animation ta = AnimationUtils.loadAnimation(this, R.anim.trans_demo);
		view.startAnimation(ta);
	}
}
