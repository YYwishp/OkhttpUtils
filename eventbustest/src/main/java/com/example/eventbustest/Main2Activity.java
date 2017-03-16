package com.example.eventbustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Main2Activity extends AppCompatActivity {
	private Button buttonSend;

	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		EventBus.getDefault().register(this);

		textView = (TextView) findViewById(R.id.textView);

		buttonSend = (Button) findViewById(R.id.button_send);
		buttonSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TestMessageEvent testMessageEvent = new TestMessageEvent();
				testMessageEvent.setMessage("我是第2界面发给第1界面的消息本体");
				EventBus.getDefault().post(testMessageEvent);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//EventBus.getDefault().unregister(this);
		TestMessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(TestMessageEvent.class);
		if (stickyEvent!=null) {
			EventBus.getDefault().removeStickyEvent(stickyEvent);
		}

	}
	@Subscribe(sticky = true)
	public void onMessageEvent(TestMessageEvent event) {
		String message = event.getMessage();
//		textView.setText(message);
		Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
	}

}
