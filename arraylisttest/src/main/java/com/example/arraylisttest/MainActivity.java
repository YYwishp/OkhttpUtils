package com.example.arraylisttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<Object> strings = new ArrayList<Object>();
		strings.add("11111");
		strings.add(true);
		Toast.makeText(this,"选中的是什么"+ strings.get(0),Toast.LENGTH_SHORT).show();
		Toast.makeText(this,"选中的是什么"+ strings.get(1),Toast.LENGTH_SHORT).show();
	}
}
