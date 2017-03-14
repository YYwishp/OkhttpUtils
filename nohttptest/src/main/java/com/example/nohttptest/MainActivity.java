package com.example.nohttptest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.io.File;

public class MainActivity extends AppCompatActivity {
	private SimpleDraweeView imgHeader;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		imgHeader = (SimpleDraweeView) findViewById(R.id.img_header);
		button = (Button) findViewById(R.id.button);


		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				load();
			}
		});


	}

	private void load() {
		RequestQueue requestQueue = NoHttp.newRequestQueue();
		Request<Bitmap> request = NoHttp.createImageRequest("http://www.lifemenu.net/images/city_job_icon/154.png", RequestMethod.GET);
		// 发起请求
		requestQueue.add(0, request, new OnResponseListener<Bitmap>() {

			@Override
			public void onStart(int what) {
			}

			@Override
			public void onSucceed(int what, Response<Bitmap> response) {
				int responseCode = response.getHeaders().getResponseCode();
				if (responseCode == 200) {
					Toast.makeText(MainActivity.this, "200", Toast.LENGTH_SHORT).show();
					Bitmap bitmap = response.get();
					imgHeader.setImageBitmap(bitmap);
					String saveBitmapFile = PhotoUtils.saveBitmapFile(bitmap, MainActivity.this);
					Log.e("文件路径",saveBitmapFile);
//					SPUtils.put("photo",saveBitmapFile);
					SharedPreferences share = getSharedPreferences("share", Context.MODE_PRIVATE);
					SharedPreferences.Editor edit = share.edit();

					edit.putString("photo",saveBitmapFile);
					edit.commit();
				} else if (responseCode == 304) {
					Toast.makeText(MainActivity.this, "304", Toast.LENGTH_SHORT).show();
//					String photoPath = (String) SPUtils.get("photo", null);
					//Log.e("读取文件路径",photoPath);
					//Uri uri = Uri.parse("file://" + photoPath);
					SharedPreferences share = getSharedPreferences("share", Context.MODE_PRIVATE);
					String photo = share.getString("photo", "");
					Uri uri = Uri.fromFile(new File(photo));
					imgHeader.setImageURI(uri);

				}
			}

			@Override
			public void onFailed(int what, Response<Bitmap> response) {
			}

			@Override
			public void onFinish(int what) {
			}
		});
	}
}
