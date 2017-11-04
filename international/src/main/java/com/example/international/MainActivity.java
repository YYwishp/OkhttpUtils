package com.example.international;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button eng;
	private Button ch;

	private EditText num1;
	private EditText num2;
	private Button plus;
	private Button minus;
	private Button multiply;
	private Button devide;
	private TextView result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);




		eng = (Button) findViewById(R.id.eng);
		ch = (Button) findViewById(R.id.ch);


		eng.setOnClickListener(this);
		ch.setOnClickListener(this);




		num1 = (EditText) findViewById(R.id.num1);
		num2 = (EditText) findViewById(R.id.num2);
		plus = (Button) findViewById(R.id.plus);
		minus = (Button) findViewById(R.id.subtract);
		multiply = (Button) findViewById(R.id.multiply);
		devide = (Button) findViewById(R.id.devide);
		result = (TextView) findViewById(R.id.result);

		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
		multiply.setOnClickListener(this);
		devide.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.eng:
				if (LocaleUtils.needUpdateLocale(this, LocaleUtils.LOCALE_ENGLISH)) {
					LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_ENGLISH);
					restartAct();
				}


				break;
			case R.id.ch:
				if (LocaleUtils.needUpdateLocale(this, LocaleUtils.LOCALE_CHINESE)) {
					LocaleUtils.updateLocale(this, LocaleUtils.LOCALE_CHINESE);
					restartAct();
				}
				break;



			//加
			case R.id.plus:
				String num1 = this.num1.getText().toString().trim();
				String num2 = this.num2.getText().toString().trim();
				BigDecimal bigDecimal1 = new BigDecimal(num1);
				BigDecimal bigDecimal2 = new BigDecimal(num2);
				result.setText(bigDecimal1.add(bigDecimal2).toString());

				break;

			//减
			case R.id.subtract:

				BigDecimal b1 = new BigDecimal(this.num1.getText().toString().trim());
				BigDecimal b2 = new BigDecimal(this.num2.getText().toString().trim());
				result.setText(b1.subtract(b2).toString());
//				result.setText(String.format(b1.subtract(b2)));
				break;
			//乘
			case R.id.multiply:

				BigDecimal n1 = new BigDecimal(this.num1.getText().toString().trim());
				BigDecimal n2 = new BigDecimal(this.num2.getText().toString().trim());
				result.setText(n1.multiply(n2).toString());
				break;
			//除
			case R.id.devide:

				BigDecimal m1 = new BigDecimal(this.num1.getText().toString().trim());
				BigDecimal m2 = new BigDecimal(this.num2.getText().toString().trim());
				BigDecimal divide = m1.divide(m2);
				BigDecimal bigDecimal = divide.setScale(8, BigDecimal.ROUND_DOWN);
				result.setText(divide.toPlainString());
				break;
		}



	}

	/**
	 * 重启当前Activity
	 */
	private void restartAct() {
		finish();
		Intent _Intent = new Intent(this, MainActivity.class);
		startActivity(_Intent);
		//清除Activity退出和进入的动画
		overridePendingTransition(0, 0);
	}
}
