package com.example.timecount;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {
	private TextView tvTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		tvTime = (TextView) findViewById(R.id.tv_time);

		tvTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CountDownTimerUtil countDownTimerUtil = new CountDownTimerUtil(tvTime, 5000, 1000);
				countDownTimerUtil.start();
			}
		});


		///////////////////////////////////////////////////////////////////////////
		// 动态解析，判断是否有哪一个字段
		///////////////////////////////////////////////////////////////////////////

		String json1 = "{\"data\":null,\"info\":\"success\",\"status\":10000}";
		String json2 = "{\"data\":null,\"info\":{\"mobile\":10021},\"status\":50001}";
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(json2);
		JsonObject asJsonObject = element.getAsJsonObject();
		//
		/*boolean jsonObject = element.isJsonObject();
		Log.e("isJsonObject", String.valueOf(jsonObject));//
		boolean jsonPrimitive = element.isJsonPrimitive();
		Log.e("isJsonPrimitive", String.valueOf(jsonPrimitive));*/
		//
		JsonElement element1 = asJsonObject.get("info");
		boolean jsonObject = element1.isJsonObject();
		Log.e("isJsonObject", String.valueOf(jsonObject));//
		boolean jsonPrimitive = element1.isJsonPrimitive();
		Log.e("isJsonPrimitive", String.valueOf(jsonPrimitive));
		JsonObject asJsonObject1 = element1.getAsJsonObject();

		Log.e("有没有mobile", String.valueOf(asJsonObject1.has("mobile")));
		Log.e("有没有email", String.valueOf(asJsonObject1.has("email")));

	}

}
