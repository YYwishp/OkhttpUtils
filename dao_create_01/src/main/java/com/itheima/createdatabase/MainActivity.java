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
		
		//����һ��������Ķ���
		MyOpenHelper myOpenHelper = new MyOpenHelper(this);
		//ִ�а������getReadableDatabase������getWritableDatabase�������������Ǵ���һ������
		SQLiteDatabase db = myOpenHelper.getReadableDatabase();
	}



}
