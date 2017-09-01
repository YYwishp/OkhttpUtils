package com.example.custom_view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.custom_view.activity.BaseGeometricActivity;
import com.example.custom_view.activity.LineAndTextActivity;
import com.example.custom_view.activity.RangeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		//1，基本几何图形绘制
		findViewById(R.id.bt_base_geometric).setOnClickListener(this);
		//2，路径及文字
		findViewById(R.id.bt_line_and_text).setOnClickListener(this);
		//3，区域（Range）
		findViewById(R.id.bt_range).setOnClickListener(this);
		//4，canvas变换与操作
		findViewById(R.id.bt_canvas_change).setOnClickListener(this);
		//5，drawrText()详解
		findViewById(R.id.bt_draw_text_detail).setOnClickListener(this);
		//6、Path之贝赛尔曲线和手势轨迹、水波纹效果
		findViewById(R.id.bt_path).setOnClickListener(this);
		//7、《 自定义控件三部曲之绘图篇(七)——Paint之函数大汇总》
		findViewById(R.id.bt_all_paint).setOnClickListener(this);
		//8、《自定义控件三部曲之绘图篇（八）——Paint之ColorMatrix与滤镜效果》
		findViewById(R.id.bt_paint_colormatrix).setOnClickListener(this);
		//9、《自定义控件三部曲之绘图篇（九）——Paint之setColorFilter》
		findViewById(R.id.bt_paint_colorfilter).setOnClickListener(this);
		//10、《自定义控件三部曲之绘图篇（十）——Paint之setXfermode(一)》
		findViewById(R.id.bt_paint_x_fermode_1).setOnClickListener(this);
		//11、《自定义控件三部曲之绘图篇（十一）——Paint之setXfermode(二)》
		findViewById(R.id.bt_paint_x_fermode_2).setOnClickListener(this);
		//12、《自定义控件三部曲之绘图篇（十二）——Paint之setXfermode(三)》
		findViewById(R.id.bt_paint_x_fermode_3).setOnClickListener(this);
		//13、《自定义控件三部曲之绘图篇（十三）——Canvas与图层(一)》
		findViewById(R.id.bt_canvas_1).setOnClickListener(this);
		//14、《自定义控件三部曲之绘图篇（十四）——Canvas与图层（二）》
		findViewById(R.id.bt_canvas_2).setOnClickListener(this);
		//15、《自定义控件三部曲之绘图篇（十五）——QQ红点拖动删除效果实现（基本原理篇）》
		findViewById(R.id.bt_about_qq_point).setOnClickListener(this);
		//16、《自定义控件三部曲之绘图篇（十六）——给控件添加阴影效果与发光效果》
		findViewById(R.id.bt_shallow_light).setOnClickListener(this);
		//17、《自定义控件三部曲之绘图篇（十七）——为Bitmap添加阴影并封装控件》
		findViewById(R.id.bt_bitmap_shallow).setOnClickListener(this);
		//18、《自定义控件三部曲之绘图篇（十八）——BitmapShader与望远镜效果》
		findViewById(R.id.bt_bitmap_shader).setOnClickListener(this);
		//19、《自定义控件三部曲之绘图篇(十九)——LinearGradient与闪动文字效果》
		findViewById(R.id.bt_linear_gradient).setOnClickListener(this);
		//20、《自定义控件三部曲之绘图篇(二十)——RadialGradient与水波纹按钮效果》
		findViewById(R.id.bt_radialgradient).setOnClickListener(this);
		//
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_base_geometric:
				Intent intent = new Intent(this, BaseGeometricActivity.class);
				startActivity(intent);
				break;
			case R.id.bt_line_and_text:
				startActivity(new Intent(this,LineAndTextActivity.class));
				break;

			case R.id.bt_range:
				startActivity(new Intent(this,RangeActivity.class));
				break;

			case R.id.bt_canvas_change:
				break;

			case R.id.bt_draw_text_detail:
				break;

			case R.id.bt_path:
				break;

			case R.id.bt_all_paint:
				break;

			case R.id.bt_paint_colormatrix:
				break;

			case R.id.bt_paint_colorfilter:
				break;

			case R.id.bt_paint_x_fermode_1:
				break;

			case R.id.bt_paint_x_fermode_2:
				break;

			case R.id.bt_paint_x_fermode_3:
				break;

			case R.id.bt_canvas_1:
				break;

			case R.id.bt_canvas_2:
				break;

			case R.id.bt_about_qq_point:
				break;
			case R.id.bt_shallow_light:
				break;
			case R.id.bt_bitmap_shallow:
				break;
			case R.id.bt_bitmap_shader:
				break;
			case R.id.bt_linear_gradient:
				break;
			case R.id.bt_radialgradient:
				break;

		}
	}
}
