package com.example.view_dong_hua;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by GYX on 2017/1/9.
 */
public class MyDialog extends Dialog implements View.OnClickListener {
	private ImageView map1;

	public interface OnMyDialogClickListener {
		void onClick();
	}
	private OnMyDialogClickListener myDialogClickListener;
	public void setOnMyDialogClickListener(OnMyDialogClickListener listener) {
		myDialogClickListener = listener;
	}



	public MyDialog(Context context) {
		super(context, R.style.mystyle);

	}

	public MyDialog(Context context, int themeResId) {
		super(context, themeResId);
	}

	protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.my_dialog, null);


		map1 = (ImageView) view.findViewById(R.id.map1);
		map1.setOnClickListener(this);
		setContentView(view);


	}

	@Override
	public void onClick(View v) {
		if (myDialogClickListener!=null) {
			myDialogClickListener.onClick();
		}
	}

}
