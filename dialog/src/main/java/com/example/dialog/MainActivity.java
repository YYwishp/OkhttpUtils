package com.example.dialog;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.io.IOException;

import sun.misc.BASE64Encoder;

/**
 * 加密
 */
public class MainActivity extends Activity {
	private String stringMD5;

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


}
