package com.itheima.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context) {
		
		
		/**
		 * context:
		 * name:数据库的名称
		 * factory:用来创建cursor对象，默认传null
		 * version：数据库的版本号，从1开始，如果version发生改变，OnUpgrade方法将会被调用 ;4.0之后数据库的版本只能升不能降
		 */
		super(context, "user.db", null, 1);
		
	}

	//oncreate：数据库第一次创建的时候调用该方法； 特别适合做表结构的初始化.该方法会传进一个SqliteDatabase数据库对象，我们可以使用该对象执行sql语句
	@Override
	public void onCreate(SQLiteDatabase db) {

		//通过SQLiteDatabase数据库对象执行创建表的sql语句
		db.execSQL("create table info (_id integer primary key autoincrement ,name varchar(20) )");
		
	}

	//onUpgrade:该方法在数据库版本发生改变时调用。特别适合做表结构的修改.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//执行sql语句添加一个phone字段
		db.execSQL("alter table  info add phone  varchar(11)");
	}

}
