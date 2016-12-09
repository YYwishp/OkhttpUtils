package com.example.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密
 */
public class MainActivity extends AppCompatActivity {
	private String stringMD5;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		JNCryptor cryptor = new AES256JNCryptor();

		byte[] plaintext = "+86-15810623041".getBytes();
		String password = "+86-15810623041relax2015";
		stringMD5 = MD5Util.getStringMD5(password);

		try {
			byte[] ciphertext = cryptor.encryptData(plaintext, stringMD5.toCharArray());


			String base64Encoder = new BASE64Encoder().encode(ciphertext);
			Log.e("加密后",base64Encoder);

		} catch (CryptorException e) {
			// Something went wrong
			e.printStackTrace();
		}
	}
}
