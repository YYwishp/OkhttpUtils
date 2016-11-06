package com.example.dragview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private DragView dragview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dragview = (DragView) findViewById(R.id.dragview);

        dragview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.e("触摸监听", "------->触摸监听");



                return false;//返回true的话，dragview的onTouchEvent方法就不能调用了，被拦截了。因为setOnTouchListener优先级比onTouchEvent高。
            }
        });

    }

    public void open(View view) {
        startActivity(new Intent(this,SecondActivity.class));
    }





}
