package com.example.myloadingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.myloadingbar.ZProgressHUD.FADED_ROUND_SPINNER;
import static com.example.myloadingbar.ZProgressHUD.GEAR_SPINNER;
import static com.example.myloadingbar.ZProgressHUD.SIMPLE_ROUND_SPINNER;

/**
 * 阿拉伯语的东西，还有loading...
 */
public class MainActivity extends AppCompatActivity {

	private MyInputPassward input;
	private EditText edittext;
	private String final_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		input = (MyInputPassward) findViewById(R.id.inputview);


		edittext = (EditText) findViewById(R.id.edittext);

		input.setOnInputOverListener(new MyInputPassward.onInputOverListener() {
			@Override
			public void onInputOver(String text) {
				final_text = text;
			}
		});

	}

	public void loading(View view) {
		ZProgressHUD instance = ZProgressHUD.getInstance(this);
		instance.setMessage("正在加载...");
		Toast.makeText(this, edittext.getText().toString().substring(1,3), Toast.LENGTH_SHORT).show();
		//SIMPLE_ROUND_SPINNER
		instance.setSpinnerType(FADED_ROUND_SPINNER);
//		instance.setSpinnerType(SIMPLE_ROUND_SPINNER);
//		instance.setSpinnerType(GEAR_SPINNER);
		instance.show();
	}
}
