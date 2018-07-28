package com.example.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GYX on 2016/12/11.
 */
public class MyToast {
	private static View view;

	public static void toast(Context context, String content)
	{
		view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);
		TextView textView=(TextView) view.findViewById(R.id.toast_text);    // 得到textview
		textView.setText(content);     //设置文本类荣，就是你想给用户看的提示数据
		Toast toast=new Toast(context);     //创建一个toast

		toast.setDuration(Toast.LENGTH_SHORT);          //设置toast显示时间，整数值
		toast.setGravity(Gravity.CENTER,0,0);    //toast的显示位置，这里居中显示

		toast.setView(view);     //設置其显示的view,
		toast.show();             //显示toast
	}
}
