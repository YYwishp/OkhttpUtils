package com.example.dialog;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;

import sun.misc.BASE64Encoder;

/**
 * 加密
 */
public class MainActivity extends Activity {
	private String stringMD5;
	private CheckedTextView check;


	/**
	 * 解码
	 *
	 * @param str
	 * @return string
	 */
	public static byte[] deCodeBase64(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.setDebugMode( true );
		setContentView(R.layout.activity_main);


		/*JNCryptor cryptor = new AES256JNCryptor();
		byte[] plaintext = "+86-15810623041".getBytes();
		String key = "+86-15810623041relax2015";




		Toast.makeText(this, "加密前+86-15810623041", Toast.LENGTH_SHORT).show();
		stringMD5 = MD5Util.getStringMD5(key);
		try {
			byte[] ciphertext = cryptor.encryptData(plaintext, stringMD5.toCharArray());
			String base64Encoder = new BASE64Encoder().encode(ciphertext);
			Log.e("加密后", base64Encoder);
			Toast.makeText(this, "加密后"+base64Encoder, Toast.LENGTH_SHORT).show();
			//解密
			byte[] deCodeBase64 = deCodeBase64(base64Encoder);
			byte[] bytes = cryptor.decryptData(deCodeBase64, stringMD5.toCharArray());
			String realString = new String(bytes);
			Log.e("解密后", realString);
			//Toast.makeText(this, "加密后"+, Toast.LENGTH_SHORT).show();
			Toast.makeText(this, "解密后" + realString, Toast.LENGTH_SHORT).show();
		} catch (CryptorException e) {
			// Something went wrong
			e.printStackTrace();
		}*/
		//=====================加密==================
		String encode = "+86-15810623041";
		String key = "+86-15810623041";
		Log.e("加密前时间", System.currentTimeMillis()+"");
		String enCode = enCode(encode, key);
		Log.e("加密后时间", System.currentTimeMillis()+"");
		Toast.makeText(this, "加密后"+enCode, Toast.LENGTH_LONG).show();
		Log.e("加密后", enCode);
		String deCode = deCode(enCode, key);
		Log.e("解密后时间", System.currentTimeMillis()+"");
		//Toast.makeText(this, "解密后"+deCode, Toast.LENGTH_SHORT).show();
		Log.e("解密后", deCode);
		getDeviceInfo(this);
		Log.e("mac地址",  getMac());

	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	/**
	 * 获取手机mac地址
	 * @return
	 */
	public static String getMac() {
		String macSerial = "";
		try {
			Process pp = Runtime.getRuntime().exec(
					"cat /sys/class/net/wlan0/address");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			String line;
			while ((line = input.readLine()) != null) {
				macSerial += line.trim();
			}

			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return macSerial;
	}
	/**
	 * 加密
	 * @param encodeString
	 * @param key
	 * @return
	 */
	public String enCode(String encodeString, String key) {
		byte[] edcodeBytes = encodeString.getBytes();
		JNCryptor cryptor = new AES256JNCryptor();
		String keyMD5 = MD5Util.getStringMD5(key+"relax2015");
		try {
			byte[] ciphertext = cryptor.encryptData(edcodeBytes, keyMD5.toCharArray());
			String base64Encoder = new BASE64Encoder().encode(ciphertext);
			return base64Encoder;
		} catch (CryptorException e) {
			// Something went wrong
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param decodeString
	 * @param key
	 * @return
	 */
	public String deCode(String decodeString, String key) {
		//解密
		try {
			JNCryptor cryptor = new AES256JNCryptor();
			byte[] decode = deCodeBase64(decodeString);
			String keyMD5 = MD5Util.getStringMD5(key+"relax2015");
			byte[] bytes = cryptor.decryptData(decode, keyMD5.toCharArray());
			String realString = new String(bytes);
			//Log.e("解密后", realString);
			return realString;
		} catch (CryptorException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static boolean checkPermission(Context context, String permission) {
		boolean result = false;
		if (Build.VERSION.SDK_INT >= 23) {
			try {
				Class<?> clazz = Class.forName("android.content.Context");
				Method method = clazz.getMethod("checkSelfPermission", String.class);
				int rest = (Integer) method.invoke(context, permission);
				if (rest == PackageManager.PERMISSION_GRANTED) {
					result = true;
				} else {
					result = false;
				}
			} catch (Exception e) {
				result = false;
			}
		} else {
			PackageManager pm = context.getPackageManager();
			if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
				result = true;
			}
		}
		return result;
	}
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			String device_id = null;
			if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
				device_id = tm.getDeviceId();
			}
			String mac = null;
			FileReader fstream = null;
			try {
				fstream = new FileReader("/sys/class/net/wlan0/address");
			} catch (FileNotFoundException e) {
				fstream = new FileReader("/sys/class/net/eth0/address");
			}
			BufferedReader in = null;
			if (fstream != null) {
				try {
					in = new BufferedReader(fstream, 1024);
					mac = in.readLine();
				} catch (IOException e) {
				} finally {
					if (fstream != null) {
						try {
							fstream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			json.put("mac", mac);
			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}
			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}
			json.put("device_id", device_id);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
