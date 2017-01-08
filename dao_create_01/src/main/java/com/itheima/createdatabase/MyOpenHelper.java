package com.itheima.createdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context) {
		
		
		/**
		 * context:
		 * name:���ݿ������
		 * factory:��������cursor����Ĭ�ϴ�null
		 * version�����ݿ�İ汾�ţ���1��ʼ�����version�����ı䣬OnUpgrade�������ᱻ���� ;4.0֮�����ݿ�İ汾ֻ�������ܽ�
		 */
		super(context, "user.db", null, 1);
		
	}

	//oncreate�����ݿ��һ�δ�����ʱ����ø÷����� �ر��ʺ�����ṹ�ĳ�ʼ��.�÷����ᴫ��һ��SqliteDatabase���ݿ�������ǿ���ʹ�øö���ִ��sql���
	@Override
	public void onCreate(SQLiteDatabase db) {

		//ͨ��SQLiteDatabase���ݿ����ִ�д������sql���
		db.execSQL("create table info (_id integer primary key autoincrement ,name varchar(20) )");
		
	}

	//onUpgrade:�÷��������ݿ�汾�����ı�ʱ���á��ر��ʺ�����ṹ���޸�.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//ִ��sql������һ��phone�ֶ�
		db.execSQL("alter table  info add phone  varchar(11)");
	}

}
