package com.example.myrqscan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button createRqcode;
	private Button readRqCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		createRqcode = (Button) findViewById(R.id.create_rqcode);
		readRqCode = (Button) findViewById(R.id.read_rq_code);

		createRqcode.setOnClickListener(this);
		readRqCode.setOnClickListener(this);



	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.create_rqcode:




				break;
			case R.id.read_rq_code:



				break;
		}
	}
}
