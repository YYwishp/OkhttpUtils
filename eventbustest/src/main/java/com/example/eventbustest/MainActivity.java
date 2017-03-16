package com.example.eventbustest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends Activity implements View.OnClickListener{
		private Button button;
		private Button button2;
		private TextView text;
		private Button buttonSend;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			EventBus.getDefault().register(this);
			button = (Button) findViewById(R.id.button);
			button2 = (Button) findViewById(R.id.button2);
			text = (TextView) findViewById(R.id.text);
			buttonSend = (Button) findViewById(R.id.button_send);
			button.setOnClickListener(this);
			button2.setOnClickListener(this);
			buttonSend.setOnClickListener(this);
		}

		@Override
		protected void onDestroy() {
			super.onDestroy();
			EventBus.getDefault().unregister(this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.button:
					Intent intent = new Intent(this, Main2Activity.class);
					startActivity(intent);
					break;
				case R.id.button2:
					Intent intent2 = new Intent(this, Main2Activity.class);
					startActivity(intent2);
					break;
				case R.id.button_send:
					TestMessageEvent testMessageEvent = new TestMessageEvent();
					testMessageEvent.setMessage("我是第一界面发给第二界面的消息本体----黏性");
					text.setText(testMessageEvent.getMessage());
					EventBus.getDefault().postSticky(testMessageEvent);
					break;
			}
		}
	@Subscribe
	public void onMessageEvent(TestMessageEvent event) {
		/*String message = event.getMessage();
		text.setText(message);*/
	}
}





























