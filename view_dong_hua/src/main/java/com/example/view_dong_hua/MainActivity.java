package com.example.view_dong_hua;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

	private ImageView map1;
	private ImageView close;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		text = (TextView) findViewById(R.id.text);

		/*map1 = (ImageView) findViewById(R.id.map1);

		scale(map1);
		map1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//ap1.setVisibility(View.GONE);
				map1.setImageResource(R.drawable.map2);

				scale(map1);
			}
		});*/

		/*View inflate = getLayoutInflater().inflate(R.layout.layout_test, null);
		map1 = (ImageView) inflate.findViewById(R.id.map1);
		close = (ImageView) inflate.findViewById(R.id.close);
		scale(map1);
		map1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//ap1.setVisibility(View.GONE);
				map1.setImageResource(R.drawable.map2);

			}
		});
		setContentView(inflate);*/
		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final MyDialog myDialog = new MyDialog(MainActivity.this);
				Window window = myDialog.getWindow();
				//window.setGravity(Gravity.CENTER);
				window.setWindowAnimations(R.style.mystyle);

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
				myDialog.show();
			}
		});

	}

	/**
	 * 缩放动画
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
