package com.itheima.createdatabase;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//创建一个帮助类的对象
		MyOpenHelper myOpenHelper = new MyOpenHelper(this);
		//执行帮助类的getReadableDatabase方法或getWritableDatabase方法，帮助我们创建一个数据
		SQLiteDatabase db = myOpenHelper.getReadableDatabase();
	}



}
