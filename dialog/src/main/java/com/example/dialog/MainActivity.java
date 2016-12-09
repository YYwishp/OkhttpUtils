package com.example.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.io.IOException;

import sun.misc.BASE64Encoder;

/**
 * 加密
 */
public class MainActivity extends AppCompatActivity {
	private String stringMD5;

	/**
	 * 解码
	 *
	 * @param str
	 * @return string
	 */
	public static byte[] decode(String str) {
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
		stringMD5 = MD5Util.getStringMD5(key);
		try {
			byte[] ciphertext = cryptor.encryptData(plaintext, stringMD5.toCharArray());
			String base64Encoder = new BASE64Encoder().encode(ciphertext);
			Log.e("加密后", base64Encoder);
			//解密
			byte[] decode = decode(base64Encoder);
			byte[] bytes = cryptor.decryptData(decode, stringMD5.toCharArray());
			String realString = new String(bytes);
			Log.e("解密后", realString);
		} catch (CryptorException e) {
			// Something went wrong
			e.printStackTrace();
		}*/
		String encode = "+86-15810623041";
		String key = "+86-15810623041relax2015";


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
			byte[] decode = decode(decodeString);
			String keyMD5 = MD5Util.getStringMD5(key+"relax2015");
			byte[] bytes = cryptor.decryptData(decode, keyMD5.toCharArray());
			String realString = new String(bytes);
			Log.e("解密后", realString);
			return realString;
		} catch (CryptorException e) {
			e.printStackTrace();
		}
		return null;
	}


}
