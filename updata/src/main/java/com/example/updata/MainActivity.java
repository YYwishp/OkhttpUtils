package com.example.updata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.entity.mime.Header;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*RequestParams params = new RequestParams();
		params.put("info", jsonObject);
		params.put("housingId", housingId);
		params.put("rentalType", "2");
		TwitterRestClient.post(Constant.LURUFANGYUAN, params, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int i, org.apache.http.Header[] headers, String s, Throwable throwable) {


				//失败
			}

			@Override
			public void onSuccess(int i, org.apache.http.Header[] headers, String s) {


			}


		});*/

	}
}
