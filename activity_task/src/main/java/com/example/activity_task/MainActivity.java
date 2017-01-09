package com.example.activity_task;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckedTextView;

public class MainActivity extends Activity {
    private CheckedTextView checktextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initState();

    }

    private void initState() {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/


        checktextview = (CheckedTextView) findViewById(R.id.checktextview);

        checktextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checktextview.toggle();
            }
        });


    }
}
