package com.example.photo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LuBanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

import me.shaohui.advancedluban.Luban;

public class MainActivity extends TakePhotoActivity {
	private TakePhoto takePhoto;
	private SimpleDraweeView ImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fresco.initialize(this);
		setContentView(R.layout.activity_main);

		ImageView = (SimpleDraweeView) findViewById(R.id.image);
		takePhoto = getTakePhoto();
	}

	/**
	 * 从相册
	 *
	 * @param view
	 */
	public void fromPhone(View view) {
		File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Uri imageUri = Uri.fromFile(file);
		//压缩图片
		configCompress(takePhoto);
		//是否使用takephopo自带的相册
		configTakePhotoOpthion(takePhoto);

		takePhoto.onPickMultipleWithCrop(1, getCropOptions());
	}

	/**
	 * 直接拍照
	 *
	 * @param view
	 */
	public void takePhoto(View view) {
		File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Uri imageUri = Uri.fromFile(file);
		//压缩图片
		configCompress(takePhoto);
		//是否使用takephopo自带的相册
		configTakePhotoOpthion(takePhoto);

		takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
	}

	//获取裁剪参数
	private CropOptions getCropOptions() {
		//剪裁
		int height = Integer.parseInt("800");
		int width = Integer.parseInt("800");
		//是否自带剪裁工具
		//boolean withWonCrop = rgCropTool.getCheckedRadioButtonId() == R.id.rbCropOwn ? true : false;
		CropOptions.Builder builder = new CropOptions.Builder();
		//检查选择尺寸
		/*if (rgCropSize.getCheckedRadioButtonId() == R.id.rbAspect) {
			builder.setAspectX(width).setAspectY(height);
		} else {
			builder.setOutputX(width).setOutputY(height);
		}*/
		builder.setAspectX(width).setAspectY(height);
		//设置自带的裁剪工具
		builder.setWithOwnCrop(true);
		return builder.create();
	}

	/**
	 * 压缩图片
	 *
	 * @param takePhoto
	 */
	private void configCompress(TakePhoto takePhoto) {
		//是否压缩
		/*if (rgCompress.getCheckedRadioButtonId() != R.id.rbCompressYes) {
			takePhoto.onEnableCompress(null, false);
			return;
		}*/
		//大小不超过
		int maxSize = Integer.parseInt("102400");
		int width = Integer.parseInt("800");
		int height = Integer.parseInt("800");
		//是否显示进度条
		boolean showProgressBar = true;
		CompressConfig config;
		//压缩工具是自带的
		if (true) {
			config = new CompressConfig.Builder()
					.setMaxSize(maxSize)
					.setMaxPixel(width >= height ? width : height)
					.create();
		} else {
			//不自带的压缩工具，使用鲁班的
			LuBanOptions option = new LuBanOptions.Builder()
					.setGear(Luban.CUSTOM_GEAR)
					.setMaxHeight(height)
					.setMaxWidth(width)
					.setMaxSize(maxSize)
					.create();
			config = CompressConfig.ofLuban(option);
		}
		//最终设置进行压缩
		takePhoto.onEnableCompress(config, showProgressBar);
	}

	/**
	 * 判断是不是使用takephoto自己的相册
	 *
	 * @param takePhoto
	 */
	private void configTakePhotoOpthion(TakePhoto takePhoto) {
		//是否使用takephoto自带相册
		if (true) {
			takePhoto.setTakePhotoOptions(new TakePhotoOptions.Builder().setWithOwnGallery(true).create());
		}
	}

	@Override
	public void takeSuccess(TResult result) {
		super.takeSuccess(result);

		TImage image = result.getImage();
		String path = image.getPath();

		File file = new File(path);
		long totalSpace = file.length();
		Log.e("字节",""+totalSpace);
		Toast.makeText(this, "文件大小:" + totalSpace, Toast.LENGTH_SHORT).show();

		Uri uri1 = Uri.fromFile(file);

		ImageView.setImageURI(uri1);


	}

}
