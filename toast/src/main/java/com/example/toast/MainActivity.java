package com.example.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyToast.toast(this,"sdfgsdfsdfsdfasdsdfsdfsdfasdfasdfasdfasdfasdfsadfasdfasdfasdfsadfsdfasdfsdfsadfsadfasdfsadfsadfsdf");


		ArrayList <Bean> list1 =new ArrayList<Bean>();
		list1.add(new Bean(10,"男"));
		list1.add(new Bean(11,"男"));
		list1.add(new Bean(12,"男"));
		list1.add(new Bean(13,"男"));


		ArrayList <Bean> list2 =new ArrayList<Bean>();

		list2.add(new Bean(10,"男"));
		list2.add(new Bean(11,"男"));

		list1.removeAll(list2);
		for (int n = 0;n<list1.size();n++) {
			Log.e("测试",list1.get(n).getAge()+"---"+list1.get(n).getName());
		}
	}
}
