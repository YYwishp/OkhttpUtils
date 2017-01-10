package com.example.view_dong_hua;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by GYX on 2017/1/9.
 */
public class MyDialog extends Dialog /*implements View.OnClickListener*/ {
	private ImageView map1;

	public MyDialog(Context context) {
		//1,这里添加的是style风格
		super(context, R.style.mystyle);
//		super(context);

	}

	public MyDialog(Context context, int themeResId) {
		super(context, themeResId);
	}

	protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

/*	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.my_dialog, null);
		map1 = (ImageView) view.findViewById(R.id.map1);

		map1.setOnClickListener(this);
		Window window = getWindow();
		//window.setGravity(Gravity.CENTER);
		window.setWindowAnimations(R.style.mystyle);
		setContentView(view);


	}

	public void setImage(int resourse) {
		map1.setImageResource(resourse);
	}

	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		super.setCanceledOnTouchOutside(cancel);
	}

	@Override
	public void onClick(View v) {
		if (myDialogClickListener!=null) {
			myDialogClickListener.onClick();
		}
	}*/

	/**
	 * 采用builder方式
	 */
	public static class Builder{
		private  Context mContext;
		private int resourse;
		private ImageView map1;
		public interface OnMyDialogClickListener {
			void onClick();
		}
		private OnMyDialogClickListener myDialogClickListener;
		public Builder setOnMyDialogClickListener(OnMyDialogClickListener listener) {
			myDialogClickListener = listener;
			return this;
		}

		public Builder(Context mContext) {
			this.mContext = mContext;
		}
		public Builder setImage(int resourse) {//设置dialig的内容
			this.resourse = resourse;
			return this;
		}

		public MyDialog build() {
			LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			MyDialog myDialog = new MyDialog(mContext);//默认调用带style的构造

			View view = mInflater.inflate(R.layout.my_dialog, null);
			map1 = (ImageView) view.findViewById(R.id.map1);
			map1.setImageResource(resourse);
			//图片点击
			map1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (myDialogClickListener!=null) {
						myDialogClickListener.onClick();
					}
				}
			});
			//设置动画
			myDialog.getWindow().setWindowAnimations(R.style.mystyle);
			myDialog.setContentView(view);
			return myDialog;

		}

	}

}
