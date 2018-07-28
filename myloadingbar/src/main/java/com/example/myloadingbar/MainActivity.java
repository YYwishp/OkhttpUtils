package com.example.myloadingbar;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.myloadingbar.ZProgressHUD.FADED_ROUND_SPINNER;

/**
 * 阿拉伯语的东西，还有loading...
 */
public class MainActivity extends AppCompatActivity {
	private MyInputPassward edtPassword;
	private EditText edittext;
	private String final_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtPassword = (MyInputPassward) findViewById(R.id.inputview);
		edittext = (EditText) findViewById(R.id.edittext);
		//====切换语言
		Locale _UserLocale1 = LocaleUtils.getCurrentLocale(this);
		switch (_UserLocale1.getLanguage()) {
			case "ar":
				edtPassword.setArCountry(true);
				break;
			default:
				edtPassword.setArCountry(false);
				break;
		}
		Locale _UserLocale = LocaleUtils.getUserLocale(this);
		if (null != _UserLocale) {
			switch (_UserLocale.getLanguage()) {
				case "ar":
					edtPassword.setArCountry(true);
					break;
				default:
					edtPassword.setArCountry(false);
					break;
			}
		}
		//显示用数字键盘
		edtPassword.setRawInputType(Configuration.KEYBOARD_12KEY);

		edtPassword.setText("123");


		//
		edtPassword.setOnInputOverListener(new MyInputPassward.onInputOverListener() {
			@Override
			public void onInputOver(String text) {
				final_text = text;
			}
		});
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			               public void run() {
				               InputMethodManager inputManager =
						               (InputMethodManager) edtPassword.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				               inputManager.showSoftInput(edtPassword, 0);
			               }
		               },
				998);
	}

	public void loading(View view) {
		ZProgressHUD instance = ZProgressHUD.getInstance(this);
		instance.setMessage("正在加载，请稍后......");
		//Toast.makeText(this, edtPassword.getTextToStirng(), Toast.LENGTH_SHORT).show();
		//SIMPLE_ROUND_SPINNER
		instance.setSpinnerType(FADED_ROUND_SPINNER);
//		instance.setSpinnerType(SIMPLE_ROUND_SPINNER);
//		instance.setSpinnerType(GEAR_SPINNER);
		instance.show();
	}


	private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
		@Override
		public CharSequence getTransformation(CharSequence source, View view) {
			return source;
		}
	}
}
